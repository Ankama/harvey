/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.EmptyFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IDegenerateFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonDegenerateFloatSetBridge<BridgedType extends IDegenerateFloatSet>
{
	protected BridgedType _bridged;

	public CommonDegenerateFloatSetBridge(final BridgedType bridged)
	{
		_bridged = bridged;
	}


	public ArrayList<IFloatSet> split(final float[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<IFloatSet> r = new ArrayList<IFloatSet>(values.length+1);
		final EmptyFloatSet empty = EmptyFloatSet.getInstance();
		final float lValue = _bridged.getValue();
		int i;
		for(i = 0 ; i<values.length ; i++)
		{
			final float value = values[i];
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

	public ArrayList<IFloatSet> split(final float... values)
	{
		final ArrayList<IFloatSet> r = new ArrayList<IFloatSet>(values.length+1);
		final EmptyFloatSet empty = EmptyFloatSet.getInstance();
		final float lValue = _bridged.getValue();
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