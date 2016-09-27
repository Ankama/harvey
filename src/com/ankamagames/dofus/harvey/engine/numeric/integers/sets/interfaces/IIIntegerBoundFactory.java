/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface IIIntegerBoundFactory<Bound extends IIIntegerBound>
{
	Bound makeBound(final boolean isLowerBound, int value);
}