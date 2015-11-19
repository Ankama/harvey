/**
 *
 */
package com.ankamagames.dofus.harvey.singlevalue;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IRandomVariableWithBehaviourAndReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.behaviours.IOrderedRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.engine.behaviours.singlevalue.ISingleValueBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class GenericOrderedSingleValueRandomVariableView<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>, Behaviour extends ISingleValueBehaviour<Data>&IOrderedRandomVariableBehaviour<Data, ParentType>>
extends OrderedSingleValueRandomVariable<Data, ParentType, Behaviour, ReadOnlyProbabilityStrategy>
implements IRandomVariableWithBehaviourAndReadOnlyProbabilityStrategy<Data, ParentType, Behaviour>
{
	public GenericOrderedSingleValueRandomVariableView(final @Nullable ParentType parent, final Behaviour behaviour, final IProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(parent, behaviour, new ReadOnlyProbabilityStrategy(probabilityStrategy));
	}

	public GenericOrderedSingleValueRandomVariableView(final Behaviour behaviour, final IProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(behaviour, new ReadOnlyProbabilityStrategy(probabilityStrategy));
	}

	public GenericOrderedSingleValueRandomVariableView(final OrderedSingleValueRandomVariable<Data, ParentType, Behaviour, ?> source) throws OverOneProbabilityException
	{
		super(source, new ReadOnlyProbabilityStrategy(source.getProbabilityStrategy()));
	}

	public GenericOrderedSingleValueRandomVariableView(final OrderedSingleValueRandomVariable<Data, ?, Behaviour, ?> source, final @Nullable ParentType parent) throws OverOneProbabilityException
	{
		super(source, parent, new ReadOnlyProbabilityStrategy(source.getProbabilityStrategy()));
	}

	public GenericOrderedSingleValueRandomVariableView(final OrderedSingleValueRandomVariable<Data, ParentType, ?, ?> source, final Behaviour behaviour) throws OverOneProbabilityException
	{
		super(source, behaviour, new ReadOnlyProbabilityStrategy(source.getProbabilityStrategy()));
	}

	public GenericOrderedSingleValueRandomVariableView(final OrderedSingleValueRandomVariable<Data, ParentType, Behaviour, ?> source, final ReadOnlyProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(source, probabilityStrategy);
	}

	public GenericOrderedSingleValueRandomVariableView(final OrderedSingleValueRandomVariable<Data, ParentType, Behaviour, ?> source, final IProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(source, new ReadOnlyProbabilityStrategy(probabilityStrategy));
	}
}