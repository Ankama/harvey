/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface IIGenericBoundFactory<Data, Bound extends IIGenericBound<Data>>
{
	Bound makeBound(final boolean isLowerBound, @Nullable Data value);
}