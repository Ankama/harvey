/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.ArrayList;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractBridgedDegenerateContinuousSetImplementation;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptyContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateContinuousGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedDegenerateContinuousGenericSetImplementation<Data, BridgedType extends IDegenerateContinuousGenericSet<Data>>
extends AbstractBridgedDegenerateContinuousSetImplementation<IContinuousGenericSet<Data>, BridgedType>

{
	public BridgedDegenerateContinuousGenericSetImplementation(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public boolean isInRange(final IContinuousGenericSet<Data> set)
	{
		final double compare1 = getBridged().getComparator().compare(getBridged().getValue(), set.getUpperBound());
		final double compare2 = getBridged().getComparator().compare(getBridged().getValue(), set.getLowerBound());
		return (((compare1 < 0) || ((compare1 == 0) && (set.isUpperBoundClosed()))) &&
				((compare2 > 0) || ((compare2 == 0) && (set.isLowerBoundClosed()))));
	}

	public boolean contains(@Nullable final Data value)
	{
		return getBridged().getComparator().compare(getBridged().getValue(), value)==0;
	}

	@Override
	public boolean isGreaterThan(final IContinuousGenericSet<Data> set)
	{
		return getBridged().getComparator().compare(getBridged().getValue(), set.getUpperBound()) > 0;
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final IContinuousGenericSet<Data> set)
	{
		return getBridged().getComparator().compare(getBridged().getValue(), set.getUpperBound()) >= 0;
	}

	@Override
	public boolean isLowerThan(final IContinuousGenericSet<Data> set)
	{
		return getBridged().getComparator().compare(getBridged().getValue(), set.getLowerBound()) < 0;
	}

	@Override
	public boolean isLowerThanOrEqualTo(final IContinuousGenericSet<Data> set)
	{
		return getBridged().getComparator().compare(getBridged().getValue(), set.getLowerBound()) <= 0;
	}

	public ArrayList<IContinuousGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IContinuousGenericSet<Data>> r = new ArrayList<IContinuousGenericSet<Data>>(values.length+1);
		final EmptyContinuousGenericSet<Data> empty = EmptyContinuousGenericSet.getInstance();
		final ContinuousComparator<? super Data> comparator = getBridged().getComparator();
		final Data lValue = getBridged().getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final Data value = values[i];
			final double compare = comparator.compare(lValue, value);
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

	public ArrayList<IContinuousGenericSet<Data>> split(final Data... values)
	{
		final ArrayList<IContinuousGenericSet<Data>> r = new ArrayList<IContinuousGenericSet<Data>>(values.length+1);
		final EmptyContinuousGenericSet<Data> empty = EmptyContinuousGenericSet.getInstance();
		final ContinuousComparator<? super Data> comparator = getBridged().getComparator();
		final Data lValue = getBridged().getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final double compare = comparator.compare(lValue, values[i]);
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
	
	public BaseContinuousGenericSet<Data, BridgedType> getMergedSet()
	{
		return BaseContinuousGenericSet.makeSet(_bridged, _bridged.getComparator());
	}
}