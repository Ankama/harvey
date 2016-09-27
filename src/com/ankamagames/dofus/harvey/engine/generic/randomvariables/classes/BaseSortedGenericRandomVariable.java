/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.randomvariables.classes;

import java.util.ArrayList;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.classes.AbstractSortedRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.ISortedGenericElementaryEvent;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.ISortedGenericRandomVariable;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseSortedGenericRandomVariable<Data, Elements extends ISortedGenericSet<Data>>
extends AbstractSortedRandomVariable<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ISortedGenericRandomVariable<Data, ?>, ISortedGenericElementaryEvent<Data, ?>, Elements, FixedProbability>
implements ISortedGenericRandomVariable<Data, Elements>
{

	public static <Data, Elements extends ISortedGenericSet<Data>> BaseSortedGenericRandomVariable<Data, Elements> makeRandomVariable(final Elements elements, final int probability)
	{
		return new BaseSortedGenericRandomVariable<Data, Elements>(elements, new FixedProbability(probability));
	}

	private BaseSortedGenericRandomVariable(final Elements elements, final FixedProbability probability)
	{
		super(elements, probability);
	}

	@Override
	public int getProbability(final ISortedGenericSet<Data> set)
	{
		final ISortedGenericSet<Data> intersection = getElements().intersect(set);
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
	public Iterator<ISortedGenericElementaryEvent<Data, ?>> iterator()
	{
		return new Iterator<ISortedGenericElementaryEvent<Data,?>>()
			{
				int proba = (int)(1/getElements().size());
				final Iterator<Data> it = getElements().getDataIterator();

				@Override
				public boolean hasNext()
				{
					return it.hasNext();
				}

				@Override
				public ISortedGenericElementaryEvent<Data, ?> next()
				{
					return BaseSortedGenericElementaryEvent.makeElementaryEvent(it.next(), proba);
				}

				@Override
				public void remove()
				{
					throw new UnsupportedOperationException();
				}
			};
	}

	@Override
	public boolean equals(final ISortedGenericRandomVariable<Data, ?> randomVariable)
	{
		return getElements().equals(randomVariable.getElements());
	}

	@Override
	public ISortedRandomVariable<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ISortedGenericRandomVariable<Data, ?>, ?, ?> or(
			final ISortedGenericRandomVariable<Data, ?> RV)
	{
		if(isImpossible())
			return RV;
		if(RV.isImpossible())
			return this;
		if(isElementaryEvent()&& RV.isElementaryEvent() && equals(RV))
			return this;
		final ISortedGenericSet<Data> unite = getElements().unite(RV.getElements().getAsSet());
		return BaseSortedGenericRandomVariable.makeRandomVariable(unite, RandomVariableUtils.convertFromDouble(1f/unite.size()));
	}

	@Override
	public ISortedRandomVariable<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ISortedGenericRandomVariable<Data, ?>, ?, ?> and(
			final ISortedGenericRandomVariable<Data, ?> RV)
	{

		if(isImpossible())
			return this;
		if(RV.isImpossible())
			return RV;
		if(isElementaryEvent()&& RV.isElementaryEvent() && equals(RV))
			return this;
		final ISortedGenericSet<Data> intersect = getElements().intersect(RV.getElements());
		return BaseSortedGenericRandomVariable.makeRandomVariable(intersect, RandomVariableUtils.convertFromDouble(1f/intersect.size()));
	}

	@Override
	public ISortedRandomVariable<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ISortedGenericRandomVariable<Data, ?>, ?, ?> add(
			final ISortedGenericRandomVariable<Data, ?> RV)
	{

		if(isImpossible())
			return RV;
		if(RV.isImpossible())
			return this;
		final Iterator<Data> dataIterator = getElements().getDataIterator();
		final Iterator<Data> dataIteratorRV = RV.getElements().getDataIterator();
		final ArrayList<Data> stored = new ArrayList<Data>();
		while(dataIterator.hasNext())
			while(dataIteratorRV.hasNext())
				stored.add(dataIterator.next()+dataIteratorRV.next());

		// add all possible

	}

	@Override
	public ISortedRandomVariable<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ISortedGenericRandomVariable<Data, ?>, ?, ?> remove(
			final ISortedGenericRandomVariable<Data, ?> RV) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISortedRandomVariable<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ISortedGenericRandomVariable<Data, ?>, ?, ?> multiply(
			final ISortedGenericRandomVariable<Data, ?> RV) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISortedRandomVariable<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ISortedGenericRandomVariable<Data, ?>, ?, ?> divide(
			final ISortedGenericRandomVariable<Data, ?> RV) {
		// TODO Auto-generated method stub
		return null;
	}

}
