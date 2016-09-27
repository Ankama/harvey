/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseCompositeGenericSet<Data, ChildType extends IGenericSet<Data>>
extends AbstractCompositeSet
<
	IGenericSet<Data>,
	ISimpleGenericSet<Data>,
	IElementaryGenericSet<Data>,
	ICompositeGenericSet<Data, ?>,
	ChildType
>
implements ICompositeGenericSet<Data, ChildType>
{

	private static final float MAX_SAMPLE = 200.f;

	protected CompositeGenericSetBridge<Data, ChildType, BaseCompositeGenericSet<Data, ChildType>> _bridge;

	protected BaseCompositeGenericSet()
	{
		_bridge = new CompositeGenericSetBridge<Data, ChildType, BaseCompositeGenericSet<Data, ChildType>>(this, new ArrayList<ChildType>(0));
	}

	protected BaseCompositeGenericSet(final int initialCapacity)
	{
		_bridge = new CompositeGenericSetBridge<Data, ChildType, BaseCompositeGenericSet<Data, ChildType>>(this, new ArrayList<ChildType>(initialCapacity));
	}

	protected BaseCompositeGenericSet(final Collection<ChildType> children)
	{
		_bridge = new CompositeGenericSetBridge<Data, ChildType, BaseCompositeGenericSet<Data, ChildType>>(this, children);
	}

	@Override
	protected CompositeGenericSetBridge<Data, ChildType, BaseCompositeGenericSet<Data, ChildType>> getBridge()
	{
		return _bridge;
	}

	@Override
	public IGenericSet<Data> getAsSet()
	{
		return this;
	}

	@Override
	public ICompositeGenericSet<Data, ?> getAsComposite() {
		return this;
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		for(final ChildType child:getChildren())
		{
			if(child.contains(value))
				return true;
		}
		return false;
	}

	protected class GenericSetIterator
	implements Iterator<Data>
	{
		HashSet<Data> _alreadyGivenData = new HashSet<Data>();
		@Nullable Data _nextValue;
		boolean _hasNext;
		Iterator<? extends ChildType> _childIt;
		Iterator<Data> _currentDataIterator;

		public GenericSetIterator()
		{
			if(getChildrenCount()<=0)
			{
				_hasNext = false;
			}
			_childIt = getChildren().iterator();
			_currentDataIterator = EmptyIterator.getInstance();
			_hasNext = updateNextValue();
		}

		protected boolean _getNextValidDataIterator()
		{
			_currentDataIterator = _childIt.next().getDataIterator();;
			while(!_currentDataIterator.hasNext())
			{
				if(!_childIt.hasNext())
				{
					return false;
				}
				_currentDataIterator = _childIt.next().getDataIterator();
			}

			return true;
		}

		protected boolean updateNextValue()
		{
			while(_currentDataIterator.hasNext() || (_childIt.hasNext() && (_getNextValidDataIterator())))
			{
				if(_alreadyGivenData.add(_nextValue = _currentDataIterator.next()))
					return true;
			}
			return false;
		}

		@Override
		public boolean hasNext()
		{
			return _hasNext;
		}

		@Override
		public @Nullable Data next()
		{
			if(!hasNext())
				throw new NoSuchElementException();
			final Data value = _nextValue;

			_hasNext = updateNextValue();

			return value;
		}
	}

	@Override
	public Iterator<Data> getDataIterator()
	{
		return new GenericSetIterator();
	}

	@Override
	public ISimpleGenericSet<Data> getSimpleSet()
	{
		return super.getSimpleSet().getAsSimpleSet();
	}

	@Override
	public List<Data> sample(int numberOfSample)
	{
		if(numberOfSample<=0)
			return sample();
		numberOfSample = (int) Math.min(numberOfSample, size());
		final int chunk = (int) (size()/numberOfSample);
		final List<Data> ret = new ArrayList<Data>(numberOfSample);
		final Iterator<Data> it = getDataIterator();
		for(int i = 0;i<numberOfSample;i++)
		{
			for(int j = 0;j<chunk-1;j++)
				it.next();
			ret.add(it.next());

		}
		return ret;
	}

	@Override
	public List<Data> sample()
	{
		// the formula of the number of samples
		// I wanted it to grow quickly for little sets and as they goes bigger and bigger the number of samples will be stabilized at MAX_SAMPLE
		// I've stated from the Sigmoïde formula and then stretched it as I wanted. --> http://fooplot.com/plot/gn0f2ulnmz
 		final int numberOfSample = (int) Math.min((1.f/(1.f+(Math.exp(-size()/MAX_SAMPLE)))*MAX_SAMPLE*2.f-MAX_SAMPLE+1.f), MAX_SAMPLE);
		return sample(numberOfSample);
	}
}