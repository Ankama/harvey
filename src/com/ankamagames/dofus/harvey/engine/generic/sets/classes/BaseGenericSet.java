/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseGenericSet<Data, ChildType extends IGenericSet<Data>>
extends AbstractCompositeSet
<
	IGenericSet<Data>,
	ChildType,
	BridgedCompositeGenericSet<Data, ChildType>
>
implements ICompositeGenericSet<Data, ChildType>
{
	BridgedCompositeGenericSet<Data, ChildType> _bridgedComposite;

	public static <Data, ChildType extends IGenericSet<Data>> BaseGenericSet<Data, ChildType> makeSet()
	{
		return new BaseGenericSet<Data, ChildType>();
	}

	protected static <Data, ChildType extends IGenericSet<Data>> BaseGenericSet<Data, ChildType> makeSet(final int initialCapacity)
	{
		return new BaseGenericSet<Data, ChildType>(initialCapacity);
	}


	protected static <Data> BaseGenericSet<Data, BaseDegenerateGenericSet<Data>> makeSetFromElement(final Data value)
	{
		return makeSet(BaseDegenerateGenericSet.makeSet(value));
	}

	protected static <Data, ChildType extends IGenericSet<Data>> BaseGenericSet<Data, ChildType> makeSet(final ChildType child)
	{
		final BaseGenericSet<Data, ChildType> r = new BaseGenericSet<Data, ChildType>(1);
		r._bridgedComposite._children.add(child);
		return r;
	}
	
	public static <Data> BaseGenericSet<Data, BaseDegenerateGenericSet<Data>> makeSetFromElements(final Collection<? extends Data> elements)
	{
		final ArrayList<BaseDegenerateGenericSet<Data>> children = new ArrayList<BaseDegenerateGenericSet<Data>>(elements.size());
		for(final Data child:elements)
		{
			children.add(BaseDegenerateGenericSet.makeSet(child));
		}
		return makeSet(children);
	}

	public static <Data, ChildType extends IGenericSet<Data>>BaseGenericSet<Data, ChildType> makeSet(final Collection<? extends ChildType> children)
	{
		return new BaseGenericSet<Data, ChildType>(children);
	}
	
	private BaseGenericSet()
	{
		_bridgedComposite = new BridgedCompositeGenericSet<Data, ChildType>();
	}
	
	private BaseGenericSet(final int initialCapacity)
	{
		_bridgedComposite = new BridgedCompositeGenericSet<Data, ChildType>(initialCapacity);
	}
	
	private BaseGenericSet(final Collection<? extends ChildType> children)
	{
		_bridgedComposite = new BridgedCompositeGenericSet<Data, ChildType>(children);
	}

	@Override
	protected BridgedCompositeGenericSet<Data, ChildType> getBridgedComposite()
	{
		return _bridgedComposite;
	}

	@Override
	protected IGenericSet<Data> getThis()
	{
		return this;
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		return getBridgedComposite().contains(value);
	}

	@Override
	public BaseGenericSet<Data, ?> getMergedSet()
	{
		final HashSet<IGenericSet<Data>> map = new HashSet<IGenericSet<Data>>(getChildren().size()*4);
		for(final IGenericSet<Data> child:getChildren())
		{
			for(final IGenericSet<Data> grandChildren:child.getMergedSet().getChildren())
				map.add(grandChildren);
		}
		return BaseGenericSet.makeSet(map);
	}
	
	@Override
	public Iterator<Data> iterator()
	{
		return new Iterator<Data>()
			{
				Iterator<? extends IGenericSet<Data>> it = getMergedSet().getChildren().iterator();

				@Override
				public boolean hasNext()
				{
					return it.hasNext();
				}

				@Override
				public @Nullable Data next()
				{
					return it.next().iterator().next();
				}

				@Override
				public void remove()
				{
					throw new UnsupportedOperationException();
				}
			};
	}
	
	@Override
	public String toString()
	{
		switch(getChildren().size())
		{
		case 0:
			return "∅";
		case 1:
			return getChildren().iterator().next().toString();
		default:
			String r = "{ ";
			for(final ChildType child:getChildren())
			{
				r +=  child.toString() + " ; ";
			}
			r = r.substring(0, r.length()-2);
			r+="}";
			return r;
		}
	}
}