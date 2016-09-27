/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleContinuousLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousLongEqualityToolbox<BridgedSet extends IContinuousLongSet>
implements IEqualityToolbox<IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, BridgedSet>
{
	BridgedSet _bridged;

	public ContinuousLongEqualityToolbox(final BridgedSet bridged)
	{
		_bridged = bridged;
	}

	@Override
	public boolean equalsValue(@Nullable final Object obj)
	{
		final IContinuousLongBound lowerBound = _bridged.getLowerBound();
		if(lowerBound!=null)
		{
			final long value = lowerBound.getValue();
			return ((Long)value).equals(obj);
		}
		throw new NullPointerException();
	}

	@Override
	public boolean equalsDegenerateSet(final IContinuousLongSet set)
	{
		final IContinuousLongBound lowerBound = set.getLowerBound();
		if(lowerBound != null)
			return equalsValue(lowerBound.getValue());
		throw new NullPointerException();
	}
}