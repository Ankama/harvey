/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

import java.util.ArrayList;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractBridgedDegenerateOrderedSetImplementation;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.EmptyFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IDegenerateFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedDegenerateFloatSetImplementation<BridgedType extends IDegenerateFloatSet>
extends AbstractBridgedDegenerateOrderedSetImplementation<IFloatSet, BridgedType>
{
	public BridgedDegenerateFloatSetImplementation(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public boolean isInRange(final IFloatSet set)
	{
		return ( ((getBridged().getValue() < set.getUpperBound())||((getBridged().getValue()==set.getUpperBound())&&(set.isUpperBoundClosed()))) &&
				((getBridged().getValue() > set.getLowerBound())||((getBridged().getValue()==set.getLowerBound())&&(set.isLowerBoundClosed()))) );
	}

	@Override
	public boolean isGreaterThan(final IFloatSet set)
	{
		return getBridged().getValue() > set.getUpperBound();
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final IFloatSet set)
	{
		return getBridged().getValue() >= set.getUpperBound();
	}

	@Override
	public boolean isLowerThan(final IFloatSet set)
	{
		return getBridged().getValue()<set.getLowerBound();
	}

	@Override
	public boolean isLowerThanOrEqualTo(final IFloatSet set)
	{
		return getBridged().getValue()<=set.getLowerBound();
	}

	public ArrayList<IFloatSet> split(final float[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IFloatSet> r = new ArrayList<IFloatSet>(values.length+1);
		final EmptyFloatSet empty = EmptyFloatSet.getInstance();
		final float lValue = getBridged().getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final float value = values[i];
			if((lValue<value)||((lValue==value)&&(!isIntervalStart[i])))
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

	public ArrayList<IFloatSet> split(final float... values)
	{
		final ArrayList<IFloatSet> r = new ArrayList<IFloatSet>(values.length+1);
		final EmptyFloatSet empty = EmptyFloatSet.getInstance();
		final float lValue = getBridged().getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			if(lValue<values[i])
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
	
	BaseFloatSet<BridgedType> getMergedSet()
	{
		return BaseFloatSet.makeSet(_bridged);
	}
}