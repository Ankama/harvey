/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.randomVariables.classes;

import java.util.ArrayList;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.classes.AbstractSortedRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.SingleValueIterator;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;
import com.ankamagames.dofus.harvey.numeric.bytes.randomVariables.Interfaces.IByteElementaryEvent;
import com.ankamagames.dofus.harvey.numeric.bytes.randomVariables.Interfaces.IByteRandomVariable;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.ByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.DegenerateByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IDegenerateByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.sun.xml.internal.fastinfoset.stax.events.EmptyIterator;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class ByteRandomVariable
extends	AbstractSortedRandomVariable<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IDegenerateByteSet, IByteRandomVariable, IByteElementaryEvent, IByteSet, IProbabilityStrategy>
implements IByteRandomVariable
{

	public static ByteRandomVariable makeRandomVariable(final IByteSet set, final int proba)
	{
		return new ByteRandomVariable(set, new FixedProbability(proba));
	}


	public ByteRandomVariable(final IByteSet elements, final IProbabilityStrategy probability) {
		super(elements, probability);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable#or(com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable)
	 */
	@Override
	public IByteRandomVariable or(
			final IByteRandomVariable RV)
	{
		if(isImpossible())
			return RV;
		if(RV.isImpossible())
			return this;
		if(isElementaryEvent()&& RV.isElementaryEvent() && equals(RV))
			return this;
		final IByteSet unite = getElements().unite(RV.getElements().getAsSet());
		return ByteRandomVariable.makeRandomVariable(unite, RandomVariableUtils.convertFromDouble(1f/unite.size()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable#and(com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable)
	 */
	@Override
	public IByteRandomVariable and(
			final IByteRandomVariable RV)
	{

		if(isImpossible())
			return this;
		if(RV.isImpossible())
			return RV;
		if(isElementaryEvent()&& RV.isElementaryEvent() && equals(RV))
			return this;
		final IByteSet intersect = getElements().intersect(RV.getElements());
		return ByteRandomVariable.makeRandomVariable(intersect, RandomVariableUtils.convertFromDouble(1f/intersect.size()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable#add(com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable)
	 */
	@Override
	public IByteRandomVariable add(
			final @Nullable IByteRandomVariable RV)
	{
		if(RV == null || RV.isImpossible())
			return this;
		if(isImpossible())
			return RV;
		final Iterator<Byte> dataIterator = getElements().getDataIterator();
		Iterator<Byte> dataIteratorRV;
		final ArrayList<Byte> stored = new ArrayList<Byte>();
		while(dataIterator.hasNext())
		{
			dataIteratorRV = RV.getElements().getDataIterator();
			while(dataIteratorRV.hasNext())
				stored.add((byte)(dataIterator.next()+dataIteratorRV.next()));
		}
		final ByteSet<DegenerateByteSet> makeSetFromElements = ByteSet.makeSetFromElements(stored);
		return ByteRandomVariable.makeRandomVariable(makeSetFromElements, RandomVariableUtils.convertFromDouble(1/makeSetFromElements.size()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable#remove(com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable)
	 */
	@Override
	public IByteRandomVariable remove(
			final @Nullable IByteRandomVariable RV)
	{
		if(RV == null || RV.isImpossible())
			return this;
		if(isImpossible())
			return RV;
		final Iterator<Byte> dataIterator = getElements().getDataIterator();
		Iterator<Byte> dataIteratorRV;
		final ArrayList<Byte> stored = new ArrayList<Byte>();
		while(dataIterator.hasNext())
		{
			dataIteratorRV = RV.getElements().getDataIterator();
			while(dataIteratorRV.hasNext())
				stored.add((byte)(dataIterator.next()-dataIteratorRV.next()));
		}
		final ByteSet<DegenerateByteSet> makeSetFromElements = ByteSet.makeSetFromElements(stored);
		return ByteRandomVariable.makeRandomVariable(makeSetFromElements, RandomVariableUtils.convertFromDouble(1/makeSetFromElements.size()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable#multiply(com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable)
	 */
	@Override
	public IByteRandomVariable multiply(
			final @Nullable IByteRandomVariable RV)
	{
		if(RV == null || RV.isImpossible())
			return this;
		if(isImpossible())
			return RV;
		final Iterator<Byte> dataIterator = getElements().getDataIterator();
		Iterator<Byte> dataIteratorRV;
		final ArrayList<Byte> stored = new ArrayList<Byte>();
		while(dataIterator.hasNext())
		{
			dataIteratorRV = RV.getElements().getDataIterator();
			while(dataIteratorRV.hasNext())
				stored.add((byte)(dataIterator.next()*dataIteratorRV.next()));
		}
		final ByteSet<DegenerateByteSet> makeSetFromElements = ByteSet.makeSetFromElements(stored);
		return ByteRandomVariable.makeRandomVariable(makeSetFromElements, RandomVariableUtils.convertFromDouble(1/makeSetFromElements.size()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable#divide(com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable)
	 */
	@Override
	public IByteRandomVariable divide(
			final @Nullable IByteRandomVariable RV)
	{
		if(RV == null || RV.isImpossible())
			return this;
		if(isImpossible())
			return RV;
		final Iterator<Byte> dataIterator = getElements().getDataIterator();
		Iterator<Byte> dataIteratorRV;
		final ArrayList<Byte> stored = new ArrayList<Byte>();
		while(dataIterator.hasNext())
		{
			dataIteratorRV = RV.getElements().getDataIterator();
			while(dataIteratorRV.hasNext())
				stored.add((byte)(dataIterator.next()/dataIteratorRV.next()));
		}
		final ByteSet<DegenerateByteSet> makeSetFromElements = ByteSet.makeSetFromElements(stored);
		return ByteRandomVariable.makeRandomVariable(makeSetFromElements, RandomVariableUtils.convertFromDouble(1/makeSetFromElements.size()));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<IByteElementaryEvent> iterator()
	{
		if(isImpossible())
			return EmptyIterator.getInstance();
		if(isElementaryEvent())
			return new SingleValueIterator<IByteElementaryEvent>(ByteElementaryEvent.makeElementaryEvent(getElements().getDataIterator().next()));
		return new Iterator<IByteElementaryEvent>()
		{

			protected Iterator<Byte> dataIterator = getElements().getDataIterator();

			@Override
			public boolean hasNext()
			{
				return dataIterator.hasNext();
			}

			@Override
			public IByteElementaryEvent next()
			{
				return ByteElementaryEvent.makeElementaryEvent(dataIterator.next());
			}

		};
	}

}
