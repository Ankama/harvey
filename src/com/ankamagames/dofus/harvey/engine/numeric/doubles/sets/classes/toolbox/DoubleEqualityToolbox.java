/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IElementaryDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class DoubleEqualityToolbox<BridgedSet extends IDoubleSet>
implements IEqualityToolbox<IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, BridgedSet>
{
	BridgedSet _bridged;

	public DoubleEqualityToolbox(final BridgedSet bridged)
	{
		_bridged = bridged;
	}

	@Override
	public boolean equalsValue(final @Nullable Object obj)
	{
		final IDoubleBound lowerBound = _bridged.getLowerBound();
		if(lowerBound!=null)
		{
			final double value = lowerBound.getValue();
			return obj !=null && obj.equals(value);
		}
		throw new NullPointerException();
	}

	@Override
	public boolean equalsDegenerateSet(final IDoubleSet set)
	{
		final IDoubleBound lowerBound = set.getLowerBound();
		if(lowerBound != null)
			return equalsValue(lowerBound.getValue());
		throw new NullPointerException();

	}

}
