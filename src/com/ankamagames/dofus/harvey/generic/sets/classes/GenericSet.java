/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSimpleCompositeSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.CheckedSetArrayList;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.SimpleCompositeGenericSetBridge;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleCompositeGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class GenericSet<Data>
extends AbstractSimpleCompositeSet
<
	IGenericSet<Data>,
	ISimpleGenericSet<Data>,
	IElementaryGenericSet<Data>,
	ICompositeGenericSet<Data, ?>,
	ISimpleCompositeGenericSet<Data, IElementaryGenericSet<Data>>
>
implements ISimpleCompositeGenericSet<Data, IElementaryGenericSet<Data>>
{
	private static final float MAX_SAMPLE = 200.f;

	public static <Data>
	GenericSet<Data> makeSet()
	{
		return new GenericSet<Data>();
	}

	public static <Data>
	GenericSet<Data> makeSet(final IElementaryGenericSet<Data>... children)
	{
		final CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>> childrenClean = CheckedSetArrayList.makeCheckedSetArrayList(Arrays.asList(children));
		return makeSet(childrenClean);
	}

	public static <Data>
	GenericSet<Data> makeSet(final IGenericSet<Data>... children)
	{
		final CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>> childrenClean = CheckedSetArrayList.makeCheckedSetArrayList();
		childrenClean.addAllSets(Arrays.asList(children));
		return makeSet(childrenClean);
	}

	public static <Data>
	GenericSet<Data> makeSet(final Collection<IElementaryGenericSet<Data>> children)
	{
		final CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>> childrenClean = CheckedSetArrayList.makeCheckedSetArrayList(children);
		return new GenericSet<Data>(childrenClean);
	}


	public static <Data> GenericSet<Data> makeSetFromElement(final Data value)
	{
		final CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>> childrenClean = CheckedSetArrayList.makeCheckedSetArrayList(Arrays.asList(DegenerateGenericSet.makeSet(value).getAsElementarySet()));
		return makeSet(childrenClean);
	}


	public static <Data> GenericSet<Data> makeSetFromElements(final Collection<? extends Data> elements)
	{
		final CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>> children = CheckedSetArrayList.makeCheckedSetArrayList();
		for(final Data child:elements)
		{
			children.add(DegenerateGenericSet.makeSet(child));
		}
		return makeSet(children);
	}

	protected SimpleCompositeGenericSetBridge<Data, GenericSet<Data>, CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>>> _bridge;

	private GenericSet()
	{
		final CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>> checkedSetCollection = CheckedSetArrayList.makeCheckedSetArrayList();
		_bridge = new SimpleCompositeGenericSetBridge<Data, GenericSet<Data>, CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>>>(this, checkedSetCollection);
	}

	private GenericSet(final Collection<IElementaryGenericSet<Data>> children)
	{
		final CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>> checkedSetCollection = CheckedSetArrayList.makeCheckedSetArrayList(children);
		_bridge = new SimpleCompositeGenericSetBridge<Data, GenericSet<Data>, CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>>>(this, checkedSetCollection);
	}

	@Override
	protected SimpleCompositeGenericSetBridge<Data, GenericSet<Data>, CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>>> getBridge()
	{
		return _bridge;
	}

	@Override
	public ISimpleGenericSet<Data> getAsSimpleSet()
	{
		return this;
	}

	@Override
	public Iterator<Data> getDataIterator()
	{
		return new Iterator<Data>()
		{
			Iterator<? extends IElementaryGenericSet<Data>> childIt = getChildren().iterator();
			Iterator<Data> currentIterator = EmptyIterator.getInstance();

			@Override
			public boolean hasNext()
			{
				while(!currentIterator.hasNext())
				{
					if(!childIt.hasNext())
						return false;
					currentIterator = childIt.next().getDataIterator();
				}
				return true;
			}

			@Override
			public @Nullable Data next()
			{
				while(!currentIterator.hasNext())
				{
					if(!childIt.hasNext())
						throw new NoSuchElementException();
					currentIterator = childIt.next().getDataIterator();
				}
				return currentIterator.next();
			}

			@Override
			public void remove()
			{
				throw new UnsupportedOperationException();
			}
		};
	}
	@Override
	public ISimpleGenericSet<Data> getSimpleSet()
	{
		return this;
	}

	@Override
	public IGenericSet<Data> getAsSet()
	{
		return this;
	}

	@Override
	public ICompositeGenericSet<Data, ?> getAsComposite()
	{
		return this;
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		for(final IElementaryGenericSet<Data> child:getChildren())
		{
			if(child.contains(value))
				return true;
		}
		return false;
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
