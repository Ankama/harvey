/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.randomvariables.classes;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.classes.AbstractElementaryEvent;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.IGenericElementaryEvent;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.IGenericRandomVariable;
import com.ankamagames.dofus.harvey.generic.randomvariables.sets.GenericImpossibleRandomVariable;
import com.ankamagames.dofus.harvey.generic.sets.classes.DegenerateGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseGenericElementaryEvent<Data>
extends AbstractElementaryEvent
<
	IGenericSet<Data>,
	ISimpleGenericSet<Data>,
	IElementaryGenericSet<Data>,
	IDegenerateGenericSet<Data>,
	IGenericRandomVariable<Data, ?>,
	IGenericElementaryEvent<Data, ?>,
	IDegenerateGenericSet<Data>,
	FixedProbability
>
implements IGenericElementaryEvent<Data, IDegenerateGenericSet<Data>>
{
	private BaseGenericElementaryEvent(final DegenerateGenericSet<Data> elements, final FixedProbability probability)
	{
		super(elements, probability);
	}

	@Override
	public boolean equals(final IGenericRandomVariable<Data, ?> randomVariable)
	{
		if(!randomVariable.isElementaryEvent())
			return false;

		return randomVariable.getElements().getDataIterator().next().equals(_elements.getValue());
	}

	public static <Data> BaseGenericElementaryEvent<Data> makeElementaryEvent(@Nullable final Data value, final int probability)
	{
		return new BaseGenericElementaryEvent<Data>(DegenerateGenericSet.makeSet(value), new FixedProbability(probability));
	}

	@Override
	protected BaseGenericElementaryEvent<Data> getAsIterableType()
	{
		return this;
	}

	@Override
	public int getProbability(final IGenericSet<Data> set)
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
	public IGenericRandomVariable<Data, ?> or(final IGenericRandomVariable<Data, ?> randomVariable)
	{
		if(equals(randomVariable))
				return this;
		final IGenericSet<Data> elements = randomVariable.getElements().unite(getElements());
		return BaseGenericRandomVariable.makeRandomVariable(elements, RandomVariableUtils.convertFromFloat((float)(1f/elements.size())));
	}

	@Override
	public IGenericRandomVariable<Data, ?> and(final IGenericRandomVariable<Data, ?> randomVariable)
	{
		if(randomVariable.getElements().contains(getElements()))
			return this;
		return GenericImpossibleRandomVariable.getInstance();
	}
}