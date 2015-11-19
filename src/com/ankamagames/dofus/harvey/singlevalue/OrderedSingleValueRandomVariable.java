/**
 *
 */
package com.ankamagames.dofus.harvey.singlevalue;

import com.ankamagames.dofus.harvey.engine.behaviours.IOrderedRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.engine.behaviours.singlevalue.ISingleValueBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.ISingleValueRandomVariable;
import com.ankamagames.dofus.harvey.ordered.AbstractOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OrderedSingleValueRandomVariable
<
	Data,
	ParentType extends ICompositeRandomVariable<Data, ?, ?>,
	Behaviour extends ISingleValueBehaviour<Data>&IOrderedRandomVariableBehaviour<Data, ParentType>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends	AbstractOrderedRandomVariable<Data, ParentType, Behaviour, ProbabilityStrategy>
implements IOrderedRandomVariable<Data, ParentType>, ISingleValueRandomVariable<Data, ParentType>
{
	public OrderedSingleValueRandomVariable(final @Nullable ParentType parent,
			final Behaviour behaviour, final ProbabilityStrategy probabilityStrategy)
			throws OverOneProbabilityException
	{
		super(parent, behaviour, probabilityStrategy);
	}

	public OrderedSingleValueRandomVariable(
			final Behaviour behaviour, final ProbabilityStrategy probabilityStrategy)
			throws OverOneProbabilityException
	{
		super(behaviour, probabilityStrategy);
	}

	public OrderedSingleValueRandomVariable(
			final OrderedSingleValueRandomVariable<Data, ParentType, Behaviour, ProbabilityStrategy> source) throws OverOneProbabilityException
	{
		super(source._parent, source._behaviour, source._probabilityStrategy);
	}

	public OrderedSingleValueRandomVariable(
			final OrderedSingleValueRandomVariable<Data, ?, Behaviour, ProbabilityStrategy> source, final @Nullable ParentType parent) throws OverOneProbabilityException
	{
		super(parent, source._behaviour, source._probabilityStrategy);
	}

	public OrderedSingleValueRandomVariable(
			final OrderedSingleValueRandomVariable<Data, ParentType, ?, ProbabilityStrategy> source, final Behaviour behaviour) throws OverOneProbabilityException
	{
		super(source._parent, behaviour, source._probabilityStrategy);
	}

	public OrderedSingleValueRandomVariable(
			final OrderedSingleValueRandomVariable<Data, ParentType, Behaviour, ?> source, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(source._parent, source._behaviour, probabilityStrategy);
	}

	public OrderedSingleValueRandomVariable(
			final OrderedSingleValueRandomVariable<Data, ?, ?, ProbabilityStrategy> source, final @Nullable ParentType parent, final Behaviour behaviour) throws OverOneProbabilityException
	{
		super(parent, behaviour, source._probabilityStrategy);
	}

	public OrderedSingleValueRandomVariable(
			final OrderedSingleValueRandomVariable<Data, ?, Behaviour, ?> source, final @Nullable ParentType parent, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(parent, source._behaviour, probabilityStrategy);
	}

	public OrderedSingleValueRandomVariable(
			final OrderedSingleValueRandomVariable<Data, ParentType, ?, ?> source, final Behaviour behaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(source._parent, behaviour, probabilityStrategy);
	}

	@Override
	@Nullable
	public Data getValue()
	{
		return _behaviour.getValue();
	}
}
