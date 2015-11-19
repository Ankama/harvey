/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedRandomVariableBehaviour<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>>
	extends IRandomVariableBehaviour<Data>, Comparable<Data>
{
	/**
	 * Gives the probability of this RandomVariable to take a value that is lower than the given
	 *  'value'. It takes in account the probability of this RandomVariable (relatively to the
	 *  given 'context') and all sub-variables if any.
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @param value
	 * @return
	 */
	int getProbabilityForLowerThan(Data value,
			@Nullable IRandomVariable<Data, ?> context);

	/**
	 * Gives the probability of this RandomVariable to take a value that is lower than or equal
	 *  to the given 'value'. It takes in account the probability of this RandomVariable
	 *  (relatively to the given 'context') and all sub-variables if any.
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @param value
	 * @return
	 */
	int getProbabilityForLowerThanOrEqualTo(Data value,
			@Nullable IRandomVariable<Data, ?> context);

	/**
	 * Gives the probability of this RandomVariable to take a value that is greater than the given
	 *  'value'. It takes in account the probability of this RandomVariable (relatively to the
	 *  given 'context') and all sub-variables if any.
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @param value
	 * @return
	 */
	int getProbabilityForGreaterThan(Data value,
			@Nullable IRandomVariable<Data, ?> context);

	/**
	 * Gives the probability of this RandomVariable to take a value that is greater than or equal
	 *  to the given 'value'. It takes in account the probability of this RandomVariable
	 *  (relatively to the given 'context') and all sub-variables if any.
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @param value
	 * @return
	 */
	int getProbabilityForGreaterThanOrEqualTo(Data value,
			@Nullable IRandomVariable<Data, ?> context);

	IOrderedRandomVariable<Data, ParentType> getLowerThan(Data value);
	IOrderedRandomVariable<Data, ParentType> getLowerThanOrEqualTo(Data value);
	IOrderedRandomVariable<Data, ParentType> getGreaterThan(Data value);
	IOrderedRandomVariable<Data, ParentType> getGreaterThanOrEqualTo(Data value);
}
