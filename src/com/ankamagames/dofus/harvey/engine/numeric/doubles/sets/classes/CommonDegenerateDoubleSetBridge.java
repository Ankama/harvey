/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.EmptyDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDegenerateDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonDegenerateDoubleSetBridge<BridgedType extends IDegenerateDoubleSet>
{
	protected BridgedType _bridged;

	public CommonDegenerateDoubleSetBridge(final BridgedType bridged)
	{
		_bridged = bridged;
	}


	public ArrayList<IDoubleSet> split(final double[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IDoubleSet> r = new ArrayList<IDoubleSet>(values.length+1);
		final EmptyDoubleSet empty = EmptyDoubleSet.getInstance();
		final double lValue = _bridged.getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final double value = values[i];
			if((lValue<value)||((lValue==value)&&(!isIntervalStart[i])))
				break;
			r.set(i, empty);
		}

		r.set(i, _bridged);

		for( i++ ; i<values.length ; i++)
		{
			r.set(i, empty);
		}
		return r;
	}

	public ArrayList<IDoubleSet> split(final double... values)
	{
		final ArrayList<IDoubleSet> r = new ArrayList<IDoubleSet>(values.length+1);
		final EmptyDoubleSet empty = EmptyDoubleSet.getInstance();
		final double lValue = _bridged.getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			if(lValue<values[i])
				break;
			r.add(empty);
		}

		r.add(_bridged);

		for( i++ ; i<values.length ; i++)
		{
			r.add(empty);
		}
		return r;
	}
}