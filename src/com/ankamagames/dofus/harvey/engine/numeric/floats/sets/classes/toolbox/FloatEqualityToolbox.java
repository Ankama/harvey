/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IElementaryFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ISimpleFloatSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class FloatEqualityToolbox<BridgedSet extends IFloatSet>
implements IEqualityToolbox<IFloatSet, ISimpleFloatSet, IElementaryFloatSet, BridgedSet>
{
	BridgedSet _bridged;

	public FloatEqualityToolbox(final BridgedSet bridged)
	{
		_bridged = bridged;
	}

	@Override
	public boolean equalsValue(final @Nullable Object obj)
	{
		final IFloatBound lowerBound = _bridged.getLowerBound();
		if(lowerBound!=null)
		{
			final float value = lowerBound.getValue();
			return obj !=null && obj.equals(value);
		}
		throw new NullPointerException();
	}

	@Override
	public boolean equalsDegenerateSet(final IFloatSet set)
	{
		final IFloatBound lowerBound = set.getLowerBound();
		if(lowerBound != null)
			return equalsValue(lowerBound.getValue());
		throw new NullPointerException();

	}

}
