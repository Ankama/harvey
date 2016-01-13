/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.OneProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CompositeIntWrapperRandomVariable
extends BaseIntWrapperRandomVariable<OneProbability>
{
	public CompositeIntWrapperRandomVariable(final int value)
	{
		super(value, OneProbability.getInstance());
	}
}