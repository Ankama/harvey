/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface IBound<Bound extends IBound<?>>
extends Comparable<Bound>
{
	boolean isPreceding(Bound bound);
	boolean isSucceeding(Bound bound);
}