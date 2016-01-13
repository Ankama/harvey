/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.OneProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CompositeFloatWrapperRandomVariable
extends BaseFloatWrapperRandomVariable<OneProbability>
{
	public CompositeFloatWrapperRandomVariable(final float value)
	{
		super(value, OneProbability.getInstance());
	}
}