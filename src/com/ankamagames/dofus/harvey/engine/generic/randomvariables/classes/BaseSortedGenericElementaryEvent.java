/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.randomvariables.classes;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.classes.AbstractElementaryEvent;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.ISortedGenericElementaryEvent;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.ISortedGenericRandomVariable;
import com.ankamagames.dofus.harvey.generic.sets.classes.DegenerateSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseSortedGenericElementaryEvent<Data>
extends AbstractElementaryEvent
<
	ISortedGenericSet<Data>,
	ISimpleSortedGenericSet<Data>,
	IElementarySortedGenericSet<Data>,
	IDegenerateSortedGenericSet<Data>,
	ISortedGenericRandomVariable<Data, ?>,
	ISortedGenericElementaryEvent<Data, ?>,
	IDegenerateSortedGenericSet<Data>,
	FixedProbability
>
implements ISortedGenericElementaryEvent<Data, IDegenerateSortedGenericSet<Data>>
{
	private BaseSortedGenericElementaryEvent(final DegenerateSortedGenericSet<Data> elements, final FixedProbability probability)
	{
		super(elements, probability);
	}

	@Override
	public boolean equals(final ISortedGenericRandomVariable<Data, ?> randomVariable)
	{
		if(!randomVariable.isElementaryEvent())
			return false;

		return randomVariable.getElements().getDataIterator().next().equals(_elements.getValue());
	}

	public static <Data> BaseSortedGenericElementaryEvent<Data> makeElementaryEvent(@Nullable final Data value, final int probability)
	{
		return new BaseSortedGenericElementaryEvent<Data>(DegenerateSortedGenericSet.makeSet(value), new FixedProbability(probability));
	}

	@Override
	protected BaseSortedGenericElementaryEvent<Data> getAsIterableType()
	{
		return this;
	}

	@Override
	public int getProbability(final ISortedGenericSet<Data> set)
	{
		if(set.contains(getElements().getValue()))
			return 1;
		return 0;
	}

	@Override
	public int getProbabilityOf(final @Nullable Data value)
	{
		if(getElements().contains(value))
			return 1;
		return 0;
	}

	@Override
	public ISortedGenericRandomVariable<Data, ?> or(final ISortedGenericRandomVariable<Data, ?> randomVariable)
	{
		if(equals(randomVariable))
				return this;
		final ISortedGenericSet<Data> elements = randomVariable.getElements().unite(getElements());
		return BaseSortedGenericRandomVariable.makeRandomVariable(elements, RandomVariableUtils.convertFromFloat((float)(1f/elements.size())));
	}

	@Override
	public ISortedGenericRandomVariable<Data, ?> and(final ISortedGenericRandomVariable<Data, ?> randomVariable)
	{
		if(randomVariable.getElements().contains(getElements()))
			return this;
		return SortedGenericImpossibleRandomVariable.getInstance();
	}
}