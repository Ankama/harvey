/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.randomvariables.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.classes.AbstractElementaryEvent;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.BaseDegenerateGenericSet;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.IGenericElementaryEvent;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseGenericElementaryEvent<Data>
	extends AbstractElementaryEvent<IGenericSet<Data>, IGenericElementaryEvent<Data, ?>, BaseDegenerateGenericSet<Data>, FixedProbability>
	implements IGenericElementaryEvent<Data, BaseDegenerateGenericSet<Data>>
{
	public static <Data> BaseGenericElementaryEvent<Data> makeElementaryEvent(@Nullable final Data value, final int probability)
	{
		return new BaseGenericElementaryEvent<Data>(BaseDegenerateGenericSet.makeSet(value), new FixedProbability(probability));
	}
	
	private BaseGenericElementaryEvent(final BaseDegenerateGenericSet<Data> elements, final FixedProbability probability)
	{
		super(elements, probability);
	}

	@Override
	protected BaseGenericElementaryEvent<Data> getThisAsIterableType()
	{
		return this;
	}

	@Override
	public int getProbability(final IGenericSet<Data> set)
	{
		if(set.contains(getElements().getValue()))
			return getProbability();
		return 0;
	}

	@Override
	public int getProbabilityOf(final Data value)
	{
		if(getElements().contains(value))
			return getProbability();
		return 0;
	}
}