package com.ankamagames.dofus.harvey.interfaces;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.behaviours.IRandomVariableBehaviour;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public interface IRandomVariable<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>>
extends IRandomVariableBehaviour<Data>
{

	@Nullable
	ParentType getParent();
	/**
	 * Gives the probability of this RandomVariable (taking in account the whole chain of parents.
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @return
	 */
	int getProbability();

	/**
	 * Gives the probability of this RandomVariable (taking in account the chain of parents up to
	 * the given 'context' parent.
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @return
	 */
	int getProbability(@Nullable IRandomVariable<Data, ?> context);

	/**
	 * Gives the probability of this RandomVariable to take the given 'value'.
	 * It takes in account the probability of this RandomVariable (relatively to its parent) and
	 * all sub-variables if any. It is similar to getProbabilityOf(value, this.getParent());
	 *
	 * The probability is given as an int representing a fixed point decimal (to avoid precision
	 * loss). To get it as a float, use {@link RandomVariableUtils.convertToFloat}
	 *
	 * @return
	 */
	int getProbabilityOf(@Nullable Data value);
}