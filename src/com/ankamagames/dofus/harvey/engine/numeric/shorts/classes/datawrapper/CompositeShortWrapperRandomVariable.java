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
public class CompositeShortWrapperRandomVariable
extends BaseShortWrapperRandomVariable<OneProbability>
{
	public CompositeShortWrapperRandomVariable(final byte value)
	{
		super(value, OneProbability.getInstance());
	}
}