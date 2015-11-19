/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IRandomVariableBehaviour<Data>
{
	boolean isEmpty();
	boolean contains(@Nullable Data value);

	/**
	 * Gives the probability of this RandomVariable to take the given 'value' taking in account
	 * the probability of this RandomVariable (relatively to the given 'context') and
	 * all sub-variables if any.
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @return
	 */
	int getProbabilityOf(@Nullable Data value,
			@Nullable IRandomVariable<Data, ?> context);
}
