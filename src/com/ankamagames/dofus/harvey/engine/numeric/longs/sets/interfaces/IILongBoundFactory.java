/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface IILongBoundFactory<Bound extends IILongBound>
{
	Bound makeBound(final boolean isLowerBound, long value);
}