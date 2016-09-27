package com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 *
 * It can also be seen as an Event
 *
 * @author sgros
 *
 * @param <Set>
 */
@NonNullByDefault
public interface IRandomVariable
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	RandomVariable extends IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, ?, ?>,
	IterableType extends IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, ?, ?>,
	ElementsType extends ISet<Set, SimpleSet, ElementarySet>
>
extends Iterable<IterableType>
{
	/**
	 * @return true if the random variable has no outcome
	 */
	boolean isImpossible();

	/**
	 * @return true if the random variable has only one outcome
	 */
	boolean isElementaryEvent();


	/**
	 * @param randomVariable
	 * @return true if both random variables (this and the given RV) have the same outcomes
	 * with the same probabilities
	 */
	boolean equals(RandomVariable randomVariable);

	/**
	 * @return the set representing the values associated with the Random Variable
	 */
	ElementsType getElements();

	/**
	 * @param set
	 * @return the probability for this random variable to take any of the values of the given set
	 */
	int getProbability(Set set);

	/**
	 * @param RV
	 * @return a Random Variable cumulating the outcomes and probabilities of both Random Variables
	 * (this and the given RV). It is their union.
	 */
	IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, ?, ?> or(RandomVariable RandomVariable);

	/**
	 * @param RV
	 * @return a Random Variable representing the intersection of both Random Variables (×)
	 * (this and the given RV)
	 */
	IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, ?, ?> and(RandomVariable RV);
}