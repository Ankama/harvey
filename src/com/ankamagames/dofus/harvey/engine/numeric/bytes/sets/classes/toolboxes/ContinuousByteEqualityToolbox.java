/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousByteEqualityToolbox<BridgedSet extends IContinuousByteSet>
implements IEqualityToolbox<IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, BridgedSet>
{
	BridgedSet _bridged;

	public ContinuousByteEqualityToolbox(final BridgedSet bridged)
	{
		_bridged = bridged;
	}

	@Override
	public boolean equalsValue(@Nullable final Object obj)
	{
		final IContinuousByteBound lowerBound = _bridged.getLowerBound();
		if(lowerBound!=null)
		{
			final byte value = lowerBound.getValue();
			return ((Byte)value).equals(obj);
		}
		throw new NullPointerException();
	}

	@Override
	public boolean equalsDegenerateSet(final IContinuousByteSet set)
	{
		final IContinuousByteBound lowerBound = set.getLowerBound();
		if(lowerBound != null)
			return equalsValue(lowerBound.getValue());
		throw new NullPointerException();
	}
}