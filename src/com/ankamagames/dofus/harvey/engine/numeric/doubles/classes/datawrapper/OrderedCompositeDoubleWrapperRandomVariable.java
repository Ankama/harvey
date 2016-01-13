/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.OneProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OrderedCompositeDoubleWrapperRandomVariable
extends BaseOrderedDoubleWrapperRandomVariable<OneProbability>
{
	public OrderedCompositeDoubleWrapperRandomVariable(final double value)
	{
		super(value, OneProbability.getInstance());
	}
}