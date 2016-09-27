/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.ICommonContinuousByteSet;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.IIByteBound;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.IIByteBoundFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonDegenerateContinuousByteSetBridge<Bound extends IContinuousBound<Bound>&IIByteBound, Type extends ICommonContinuousByteSet<Bound, ?, ?, ?>>
extends CommonDegenerateByteSetBridge<Bound, Type>
{
	public CommonDegenerateContinuousByteSetBridge(final byte value, final IIByteBoundFactory<Bound> boundFactory, final Type emptySet, final Type bridged)
	{
		super(value, boundFactory, emptySet, bridged);
	}
}