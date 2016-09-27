/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.ICommonContinuousShortSet;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.IIShortBound;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.IIShortBoundFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonDegenerateContinuousShortSetBridge<Bound extends IContinuousBound<Bound>&IIShortBound, Type extends ICommonContinuousShortSet<Bound, ?, ?, ?>>
extends CommonDegenerateShortSetBridge<Bound, Type>
{
	public CommonDegenerateContinuousShortSetBridge(final short value, final IIShortBoundFactory<Bound> boundFactory, final Type emptySet, final Type bridged)
	{
		super(value, boundFactory, emptySet, bridged);
	}
}