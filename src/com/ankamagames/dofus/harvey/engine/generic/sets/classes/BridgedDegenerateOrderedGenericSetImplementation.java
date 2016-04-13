/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.ArrayList;
import java.util.Comparator;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractBridgedDegenerateOrderedSetImplementation;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.DefaultUncheckedIncrementableIncrementor;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptyOrderedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateOrderedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IOrderedGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedDegenerateOrderedGenericSetImplementation<Data>
	extends AbstractBridgedDegenerateOrderedSetImplementation<IOrderedGenericSet<Data>, IDegenerateOrderedGenericSet<Data>>
{
	public BridgedDegenerateOrderedGenericSetImplementation(final IDegenerateOrderedGenericSet<Data> bridged)
	{
		super(bridged);
	}

	@Override
	public boolean isInRange(final IOrderedGenericSet<Data> set)
	{
		return ((getBridged().getComparator().compare(getBridged().getValue(), set.getUpperBound()) <= 0)
			&& (getBridged().getComparator().compare(getBridged().getValue(), set.getLowerBound()) >= 0));
	}

	public boolean contains(@Nullable final Data value)
	{
		return getBridged().getComparator().compare(getBridged().getValue(), value)==0;
	}

	@Override
	public boolean isGreaterThan(final IOrderedGenericSet<Data> set)
	{
		return getBridged().getComparator().compare(getBridged().getValue(), set.getUpperBound()) > 0;
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final IOrderedGenericSet<Data> set)
	{
		return getBridged().getComparator().compare(getBridged().getValue(), set.getUpperBound()) >= 0;
	}

	@Override
	public boolean isLowerThan(final IOrderedGenericSet<Data> set)
	{
		return getBridged().getComparator().compare(getBridged().getValue(), set.getLowerBound()) < 0;
	}

	@Override
	public boolean isLowerThanOrEqualTo(final IOrderedGenericSet<Data> set)
	{
		return getBridged().getComparator().compare(getBridged().getValue(), set.getLowerBound()) <= 0;
	}

	public ArrayList<IOrderedGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IOrderedGenericSet<Data>> r = new ArrayList<IOrderedGenericSet<Data>>(values.length+1);
		final EmptyOrderedGenericSet<Data> empty = EmptyOrderedGenericSet.getInstance();
		final Comparator<? super Data> comparator = getBridged().getComparator();
		final Data lValue = getBridged().getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final Data value = values[i];
			final int compare = comparator.compare(lValue, value);
			if((compare<0)||((compare==0)&&(!isIntervalStart[i])))
				break;
			r.set(i, empty);
		}
		
		r.set(i, getBridged());

		for( i++ ; i<values.length ; i++)
		{
			r.set(i, empty);
		}
		return r;
	}

	public ArrayList<IOrderedGenericSet<Data>> split(final Data... values)
	{
		final ArrayList<IOrderedGenericSet<Data>> r = new ArrayList<IOrderedGenericSet<Data>>(values.length+1);
		final EmptyOrderedGenericSet<Data> empty = EmptyOrderedGenericSet.getInstance();
		final Comparator<? super Data> comparator = getBridged().getComparator();
		final Data lValue = getBridged().getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final int compare = comparator.compare(lValue, values[i]);
			if(compare<0)
				break;
			r.add(empty);
		}
		
		r.add(getBridged());

		for( i++ ; i<values.length ; i++)
		{
			r.add(empty);
		}
		return r;
	}
	
	public BaseOrderedGenericSet<Data, ? extends IDegenerateOrderedGenericSet<Data>> getMergedSet()
	{
		final DefaultUncheckedIncrementableIncrementor<Data> incrementor = DefaultUncheckedIncrementableIncrementor.getInstance();
		return BaseOrderedGenericSet.makeSet(_bridged, _bridged.getComparator(), incrementor);
	}
}