/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.classes;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.classes.sets.iterators.SingleValueIterator;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IElementaryEvent;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractElementaryEvent<Set extends ISet<Set>, IterableType extends IElementaryEvent<Set, ?, ?>, ElementType extends IDegenerateSet<Set>, Probability extends IProbabilityStrategy>
extends AbstractRandomVariable<Set, IterableType, ElementType, Probability>
implements IElementaryEvent<Set, IterableType, ElementType>
{
	public AbstractElementaryEvent(final ElementType elements, final Probability probability)
	{
		super(elements, probability);
	}

	abstract protected IterableType getThisAsIterableType();
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<IterableType> iterator()
	{
		return new SingleValueIterator<IterableType>(getThisAsIterableType());
	}
}