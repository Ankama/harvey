/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface IContinuousBound<Bound extends IContinuousBound<?>>
extends IBound<Bound>, ContinuousComparable<Bound>
{
	boolean isLowerBound();
	boolean isInfinity();
	boolean isClosed();
}