/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.randomVariables.classes;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.classes.AbstractSortedElementaryEvent;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;
import com.ankamagames.dofus.harvey.numeric.bytes.randomVariables.Interfaces.IByteElementaryEvent;
import com.ankamagames.dofus.harvey.numeric.bytes.randomVariables.Interfaces.IByteRandomVariable;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.DegenerateByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IDegenerateByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class ByteElementaryEvent
extends AbstractSortedElementaryEvent<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IDegenerateByteSet, IByteRandomVariable, IByteElementaryEvent, IDegenerateByteSet, FixedProbability>
implements IByteElementaryEvent
{

	public static ByteElementaryEvent makeElementaryEvent(final byte value)
	{
		return new ByteElementaryEvent(DegenerateByteSet.makeSet(value));
	}

	public ByteElementaryEvent(final IDegenerateByteSet elements)
	{
		super(elements, new FixedProbability());
	}

	@Override
	public IRandomVariable<IByteSet, ISimpleByteSet, IElementaryByteSet, IByteRandomVariable, ?, ?> or(
			final IByteRandomVariable RV)
	{
		if(RV.isImpossible())
			return this;
		if(RV.getElements().equals(getElements()))
			return this;
		final IByteSet unite = RV.getElements().unite(getElements());
		return ByteRandomVariable.makeRandomVariable(unite, RandomVariableUtils.convertFromDouble(1/unite.size()));
	}

	@Override
	public IRandomVariable<IByteSet, ISimpleByteSet, IElementaryByteSet, IByteRandomVariable, ?, ?> and(
			final IByteRandomVariable RV)
	{
		if(RV.isImpossible())
			return RV;
		if(RV.getElements().contains(getElements()))
			return this;

		return ByteImpossibleRandomVariable.getInstance();
	}

	@Override
	protected IByteElementaryEvent getAsIterableType()
	{
		return this;
	}


}
