/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleContinuousShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousShortEqualityToolbox<BridgedSet extends IContinuousShortSet>
implements IEqualityToolbox<IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, BridgedSet>
{
	BridgedSet _bridged;

	public ContinuousShortEqualityToolbox(final BridgedSet bridged)
	{
		_bridged = bridged;
	}

	@Override
	public boolean equalsValue(@Nullable final Object obj)
	{
		final IContinuousShortBound lowerBound = _bridged.getLowerBound();
		if(lowerBound!=null)
		{
			final short value = lowerBound.getValue();
			return ((Short)value).equals(obj);
		}
		throw new NullPointerException();
	}

	@Override
	public boolean equalsDegenerateSet(final IContinuousShortSet set)
	{
		final IContinuousShortBound lowerBound = set.getLowerBound();
		if(lowerBound != null)
			return equalsValue(lowerBound.getValue());
		throw new NullPointerException();
	}
}