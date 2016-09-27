/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface IIShortBoundFactory<Bound extends IIShortBound>
{
	Bound makeBound(final boolean isLowerBound, short value);
}