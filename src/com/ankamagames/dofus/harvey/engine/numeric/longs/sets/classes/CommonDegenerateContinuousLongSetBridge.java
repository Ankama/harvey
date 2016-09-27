/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces.ICommonContinuousLongSet;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces.IILongBound;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces.IILongBoundFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonDegenerateContinuousLongSetBridge<Bound extends IContinuousBound<Bound>&IILongBound, Type extends ICommonContinuousLongSet<Bound, ?, ?, ?>>
extends CommonDegenerateLongSetBridge<Bound, Type>
{
	public CommonDegenerateContinuousLongSetBridge(final long value, final IILongBoundFactory<Bound> boundFactory, final Type emptySet, final Type bridged)
	{
		super(value, boundFactory, emptySet, bridged);
	}
}