/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.classes;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IElementaryEvent;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.SingleValueIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractElementaryEvent
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	DegenerateSet extends IDegenerateSet<Set, SimpleSet, ElementarySet, DegenerateSet>,
	RandomVariable extends IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, ?, ? extends ISet<Set, SimpleSet, ElementarySet>>,
	IterableType extends IElementaryEvent<Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, ?, ?>,
	ElementType extends IDegenerateSet<Set, SimpleSet, ElementarySet, DegenerateSet>,
	Probability extends IProbabilityStrategy
>
extends AbstractRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, IterableType, ElementType, Probability>
implements IElementaryEvent<Set, SimpleSet, ElementarySet, DegenerateSet, RandomVariable, IterableType, ElementType>
{


	public AbstractElementaryEvent(final ElementType elements, final Probability probability)
	{
		super(elements, probability);
	}

	abstract protected IterableType getAsIterableType();

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<IterableType> iterator()
	{
		return new SingleValueIterator<IterableType>(this.getAsIterableType());
	}

	@Override
	public boolean equals(final RandomVariable randomVariable)
	{
		return (randomVariable.isElementaryEvent() && randomVariable.getElements().equals(getElements()));
	}

	@Override
	public int getProbability(final Set set)
	{
		return 1;
	}

}