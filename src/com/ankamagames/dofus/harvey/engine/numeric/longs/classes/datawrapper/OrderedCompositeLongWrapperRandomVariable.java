/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.OneProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OrderedCompositeLongWrapperRandomVariable
extends BaseOrderedLongWrapperRandomVariable<OneProbability>
{
	public OrderedCompositeLongWrapperRandomVariable(final long value)
	{
		super(value, OneProbability.getInstance());
	}
}