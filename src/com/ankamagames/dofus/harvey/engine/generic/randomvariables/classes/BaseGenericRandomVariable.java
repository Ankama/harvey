/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.randomvariables.classes;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.classes.AbstractRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.IGenericElementaryEvent;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.IGenericRandomVariable;
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
public class BaseGenericRandomVariable<Data, Elements extends IGenericSet<Data>>
extends AbstractRandomVariable<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, IGenericRandomVariable<Data, ?>, IGenericElementaryEvent<Data, ?>, Elements, FixedProbability>
implements IGenericRandomVariable<Data, Elements>
{

	public static <Data, Elements extends IGenericSet<Data>> BaseGenericRandomVariable<Data, Elements> makeRandomVariable(final Elements elements, final int probability)
	{
		return new BaseGenericRandomVariable<Data, Elements>(elements, new FixedProbability(probability));
	}

	private BaseGenericRandomVariable(final Elements elements, final FixedProbability probability)
	{
		super(elements, probability);
	}

	@Override
	public int getProbability(final IGenericSet<Data> set)
	{
		final IGenericSet<Data> intersection = getElements().intersect(set);
		return (int)((intersection.size()/getElements().size()));
	}

	@Override
	public int getProbabilityOf(final @Nullable Data value)
	{
		if(getElements().contains(value))
			return (int)(1/getElements().size());
		return 0;
	}

	@Override
	public Iterator<IGenericElementaryEvent<Data, ?>> iterator()
	{
		return new Iterator<IGenericElementaryEvent<Data,?>>()
			{
				int proba = (int)(1/getElements().size());
				final Iterator<Data> it = getElements().getDataIterator();

				@Override
				public boolean hasNext()
				{
					return it.hasNext();
				}

				@Override
				public IGenericElementaryEvent<Data, ?> next()
				{
					return BaseGenericElementaryEvent.makeElementaryEvent(it.next(), proba);
				}

				@Override
				public void remove()
				{
					throw new UnsupportedOperationException();
				}
			};
	}

	@Override
	public boolean equals(final IGenericRandomVariable<Data, ?> randomVariable)
	{
		return getElements().equals(randomVariable.getElements());
	}

	@Override
	public IRandomVariable<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, IGenericRandomVariable<Data, ?>, ?, ?> or(
			final IGenericRandomVariable<Data, ?> randomVariable)
	{
		final IGenericSet<Data> unite = getElements().unite(randomVariable.getElements());
		return BaseGenericRandomVariable.makeRandomVariable(unite, RandomVariableUtils.convertFromFloat((float)(1f/unite.size())));
	}

	@Override
	public IRandomVariable<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, IGenericRandomVariable<Data, ?>, ?, ?> and(
			final IGenericRandomVariable<Data, ?> randomVariable)
	{
		final IGenericSet<Data> intersect = getElements().intersect(randomVariable.getElements());
		return BaseGenericRandomVariable.makeRandomVariable(intersect, RandomVariableUtils.convertFromFloat((float)(1f/intersect.size())));
	}

}
