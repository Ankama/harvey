/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousIntegerEqualityToolbox<BridgedSet extends IContinuousIntegerSet>
implements IEqualityToolbox<IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, BridgedSet>
{
	BridgedSet _bridged;

	public ContinuousIntegerEqualityToolbox(final BridgedSet bridged)
	{
		_bridged = bridged;
	}

	@Override
	public boolean equalsValue(@Nullable final Object obj)
	{
		final IContinuousIntegerBound lowerBound = _bridged.getLowerBound();
		if(lowerBound!=null)
		{
			final int value = lowerBound.getValue();
			return ((Integer)value).equals(obj);
		}
		throw new NullPointerException();
	}

	@Override
	public boolean equalsDegenerateSet(final IContinuousIntegerSet set)
	{
		final IContinuousIntegerBound lowerBound = set.getLowerBound();
		if(lowerBound != null)
			return equalsValue(lowerBound.getValue());
		throw new NullPointerException();
	}
}