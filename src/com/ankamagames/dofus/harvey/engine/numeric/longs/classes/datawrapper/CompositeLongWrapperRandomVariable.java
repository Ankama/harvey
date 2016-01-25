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
public class CompositeLongWrapperRandomVariable
extends BaseLongWrapperRandomVariable<OneProbability>
{
	public CompositeLongWrapperRandomVariable(final long value)
	{
		super(value, OneProbability.getInstance());
	}
}