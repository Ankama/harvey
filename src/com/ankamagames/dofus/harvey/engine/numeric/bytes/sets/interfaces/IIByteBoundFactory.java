/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface IIByteBoundFactory<Bound extends IIByteBound>
{
	Bound makeBound(final boolean isLowerBound, byte value);
}