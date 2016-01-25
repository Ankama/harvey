/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.OneProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OrderedCompositeShortWrapperRandomVariable
extends BaseOrderedShortWrapperRandomVariable<OneProbability>
{
	public OrderedCompositeShortWrapperRandomVariable(final byte value)
	{
		super(value, OneProbability.getInstance());
	}
}