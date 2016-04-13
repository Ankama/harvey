package com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;

/**
 * 
 * It can also be seen as an Event
 * 
 * @author sgros
 *
 * @param <Set>
 */
@NonNullByDefault
public interface IRandomVariable<Set extends ISet<Set>, IterableType extends IRandomVariable<Set, ?, ?>, ElementsType extends ISet<Set>>
extends Iterable<IterableType>
{
	boolean isElementaryEvent();
	
	/**
	 * @return the set representing the values associated with the Random Variable
	 */
	ElementsType getElements();
	
	/**
	 * @return the probabilities of the RandomVariable to take any of the values held by
	 * the set that can be accessed using getElements(). It is the integral of the RV.
	 */
	int getProbability();
	
	/**
	 * @param set
	 * @return the probability for this random variable to take any of the values of the given set
	 */
	int getProbability(Set set);
}