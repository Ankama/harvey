/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.classes;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractImpossibleRandomVariable
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	EmptySet extends IEmptySet<Set, SimpleSet, ElementarySet, EmptySet>,
	RandomVariable extends IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, ?, ?>,
	IterableType extends IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, ?, ?>
>
implements IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, IterableType, EmptySet>
{

	@Override
	public Iterator<IterableType> iterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public boolean isImpossible()
	{
		return true;
	}

	@Override
	public boolean isElementaryEvent()
	{
		return false;
	}

	@Override
	public boolean equals(final RandomVariable randomVariable)
	{
		return randomVariable.isImpossible();
	}

	@Override
	public abstract EmptySet getElements();

	@Override
	public int getProbability(final Set set)
	{
		return 0;
	}

	@Override
	public IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, ?, ?> or(final RandomVariable RandomVariable)
	{
		return RandomVariable;
	}

	@Override
	public IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, ?, ?> and(final RandomVariable RV)
	{
		return this;
	}

}
