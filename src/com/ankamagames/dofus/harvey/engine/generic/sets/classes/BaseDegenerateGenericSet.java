/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.classes.sets.iterators.SingleValueIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseDegenerateGenericSet<Data>
	extends AbstractDegenerateSet<IGenericSet<Data>>
	implements IDegenerateGenericSet<Data>
{
	@Nullable Data _value;
	
	public static <Data> BaseDegenerateGenericSet<Data> makeSet(@Nullable final Data value)
	{
		return new BaseDegenerateGenericSet<Data>(value);
	}
	
	private BaseDegenerateGenericSet(@Nullable final Data value)
	{
		_value = value;
	}

	@Override
	protected IGenericSet<Data> getThis()
	{
		return this;
	}
	
	@Override
	public boolean equals(@Nullable final Object obj)
	{
		final Data value = getValue();
		if(obj instanceof IGenericSet)
		{
			final IGenericSet<?> set = (IGenericSet<?>)obj;
			if(set.isDegenerate())
			{
				if(set instanceof IDegenerateGenericSet)
				{
					if(value!=null)
						return value.equals(((IDegenerateGenericSet<?>)set).getValue());
					return ((IDegenerateGenericSet<?>)set).getValue()==null;
				}
				return equals(set.getMergedSet().getChildren().iterator().next());
			}
		}
		if(value==null)
			return obj==null;
		
		return value.equals(obj);
	}
	
	@Override
	public int hashCode()
	{
		Data value = getValue();
		if(value!=null)
			return value.hashCode();
		return 0;
	}

	@Override
	public @Nullable Data getValue()
	{
		return _value;
	}
	
	@Override
	public boolean contains(@Nullable final Data value)
	{
		if(value!=null)
			return value.equals(getValue());
		return getValue()==null;
	}
	
	@Override
	public String toString()
	{
		final Data value = getValue();
		if(value==null)
			return "null";
		return value.toString();
	}

	@Override
	public BaseGenericSet<Data, BaseDegenerateGenericSet<Data>> getMergedSet()
	{
		return BaseGenericSet.makeSet(this);
	}

	@Override
	public Iterator<Data> iterator()
	{
		return new SingleValueIterator<Data>(getValue());
	}
}