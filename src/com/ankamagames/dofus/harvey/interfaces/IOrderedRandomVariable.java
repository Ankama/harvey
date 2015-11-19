/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.behaviours.IOrderedRandomVariableBehaviour;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedRandomVariable<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>>
	extends IRandomVariable<Data, ParentType>, IOrderedRandomVariableBehaviour<Data, ParentType>
{
	/**
	 * Gives the probability of this RandomVariable to take a value that is lower than the given
	 *  'value'. It takes in account the probability of this RandomVariable (relatively to its
	 *  parent) and all sub-variables if any. It is similar to
	 *  getProbabilityForLowerThan(value, this.getParent());
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @param value
	 * @return
	 */
	int getProbabilityForLowerThan(Data value);

	/**
	 * Gives the probability of this RandomVariable to take a value that is lower than or equal
	 *  to the given 'value'. It takes in account the probability of this RandomVariable
	 *  (relatively to its parent) and all sub-variables if any. It is similar to
	 *  getProbabilityForLowerThanOrEqualTo(value, this.getParent());
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @param value
	 * @return
	 */
	int getProbabilityForLowerThanOrEqualTo(Data value);

	/**
	 * Gives the probability of this RandomVariable to take a value that is greater than the given
	 *  'value'. It takes in account the probability of this RandomVariable (relatively to its
	 *  parent) and all sub-variables if any. It is similar to
	 *  getProbabilityForGreaterThan(value, this.getParent());
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @param value
	 * @return
	 */
	int getProbabilityForGreaterThan(Data value);

	/**
	 * Gives the probability of this RandomVariable to take a value that is greater than or equal
	 *  to the given 'value'. It takes in account the probability of this RandomVariable
	 *  (relatively to its parent) and all sub-variables if any. It is similar to
	 *  getProbabilityForGreaterThanOrEqualTo(value, this.getParent());
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @param value
	 * @return
	 */
	int getProbabilityForGreaterThanOrEqualTo(Data value);
}
