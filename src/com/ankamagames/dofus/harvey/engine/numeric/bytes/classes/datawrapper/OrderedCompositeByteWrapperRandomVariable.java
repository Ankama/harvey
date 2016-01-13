/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.OneProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OrderedCompositeByteWrapperRandomVariable
extends BaseOrderedByteWrapperRandomVariable<OneProbability>
{
	public OrderedCompositeByteWrapperRandomVariable(final byte value)
	{
		super(value, OneProbability.getInstance());
	}
}