/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces.ICommonContinuousIntegerSet;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces.IIIntegerBound;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces.IIIntegerBoundFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonDegenerateContinuousIntegerSetBridge<Bound extends IContinuousBound<Bound>&IIIntegerBound, Type extends ICommonContinuousIntegerSet<Bound, ?, ?, ?>>
extends CommonDegenerateIntegerSetBridge<Bound, Type>
{
	public CommonDegenerateContinuousIntegerSetBridge(final int value, final IIIntegerBoundFactory<Bound> boundFactory, final Type emptySet, final Type bridged)
	{
		super(value, boundFactory, emptySet, bridged);
	}
}