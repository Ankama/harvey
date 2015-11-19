/**
 *
 */
package com.ankamagames.dofus.harvey.ordered;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IRandomVariableWithBehaviourAndReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.behaviours.composite.IOrderedCompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.behaviours.composite.OrderedReadOnlyCompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class GenericOrderedCompositeRandomVariableView<
	Data,
	ParentType extends ICompositeRandomVariable<Data, ?, ?>,
	ChildType extends IOrderedRandomVariable<Data, ?>
>
extends	OrderedCompositeRandomVariable<Data, ParentType, ChildType, OrderedReadOnlyCompositeBehaviour<Data, ParentType, ChildType, IOrderedCompositeBehaviour<Data, ParentType,ChildType>, GenericOrderedCompositeRandomVariableView<Data, ParentType, ChildType>>, ReadOnlyProbabilityStrategy>
implements IRandomVariableWithBehaviourAndReadOnlyProbabilityStrategy<Data, ParentType, OrderedReadOnlyCompositeBehaviour<Data, ParentType, ChildType, IOrderedCompositeBehaviour<Data, ParentType,ChildType>, GenericOrderedCompositeRandomVariableView<Data, ParentType, ChildType>>>
{
	public GenericOrderedCompositeRandomVariableView(@Nullable final ParentType parent,
			final IOrderedCompositeBehaviour<Data, ParentType,ChildType> behaviour, final IProbabilityStrategy probabilityStrategy)
			throws OverOneProbabilityException
	{
		super(parent,
				new OrderedReadOnlyCompositeBehaviour<Data, ParentType, ChildType, IOrderedCompositeBehaviour<Data, ParentType, ChildType>, GenericOrderedCompositeRandomVariableView<Data, ParentType, ChildType>>(behaviour),
				new ReadOnlyProbabilityStrategy(probabilityStrategy)
		);
		_behaviour.init(this);
	}

	public GenericOrderedCompositeRandomVariableView(
			final IOrderedCompositeBehaviour<Data, ParentType,ChildType> behaviour, final IProbabilityStrategy probabilityStrategy)
			throws OverOneProbabilityException
	{
		super(
				new OrderedReadOnlyCompositeBehaviour<Data, ParentType, ChildType, IOrderedCompositeBehaviour<Data, ParentType, ChildType>, GenericOrderedCompositeRandomVariableView<Data, ParentType, ChildType>>(behaviour),
				new ReadOnlyProbabilityStrategy(probabilityStrategy)
		);
		_behaviour.init(this);
	}

	public GenericOrderedCompositeRandomVariableView(final OrderedCompositeRandomVariable<Data, ParentType, ?, ? extends IOrderedCompositeBehaviour<Data, ParentType, ChildType>, ?> source) throws OverOneProbabilityException
	{
		super(
				source,
				new OrderedReadOnlyCompositeBehaviour<Data, ParentType, ChildType, IOrderedCompositeBehaviour<Data, ParentType, ChildType>, GenericOrderedCompositeRandomVariableView<Data, ParentType, ChildType>>(source.getBehaviour()),
				new ReadOnlyProbabilityStrategy(source.getProbabilityStrategy())
		);
	}

	public GenericOrderedCompositeRandomVariableView(final OrderedCompositeRandomVariable<Data, ParentType, ?, ? extends IOrderedCompositeBehaviour<Data, ParentType, ChildType>, ?> source,
			final @Nullable ParentType parent) throws OverOneProbabilityException
	{
		super(
				parent,
				new OrderedReadOnlyCompositeBehaviour<Data, ParentType, ChildType, IOrderedCompositeBehaviour<Data, ParentType, ChildType>, GenericOrderedCompositeRandomVariableView<Data, ParentType, ChildType>>(source.getBehaviour()),
				new ReadOnlyProbabilityStrategy(source.getProbabilityStrategy())
		);
	}

	public GenericOrderedCompositeRandomVariableView(final OrderedCompositeRandomVariable<Data, ParentType, ?, ? extends IOrderedCompositeBehaviour<Data, ParentType, ChildType>, ?> source,
			final IOrderedCompositeBehaviour<Data, ParentType, ChildType> behaviour) throws OverOneProbabilityException
	{
		super(
				new OrderedReadOnlyCompositeBehaviour<Data, ParentType, ChildType, IOrderedCompositeBehaviour<Data, ParentType, ChildType>, GenericOrderedCompositeRandomVariableView<Data, ParentType, ChildType>>(behaviour),
				new ReadOnlyProbabilityStrategy(source.getProbabilityStrategy())
		);
	}

	public GenericOrderedCompositeRandomVariableView(final OrderedCompositeRandomVariable<Data, ParentType, ?, ? extends IOrderedCompositeBehaviour<Data, ParentType, ChildType>, ?> source,
			final ReadOnlyProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(
				new OrderedReadOnlyCompositeBehaviour<Data, ParentType, ChildType, IOrderedCompositeBehaviour<Data, ParentType, ChildType>, GenericOrderedCompositeRandomVariableView<Data, ParentType, ChildType>>(source.getBehaviour()),
				probabilityStrategy
		);
	}

	public GenericOrderedCompositeRandomVariableView(final OrderedCompositeRandomVariable<Data, ParentType, ?, ? extends IOrderedCompositeBehaviour<Data, ParentType, ChildType>, ?> source,
			final IProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(
				new OrderedReadOnlyCompositeBehaviour<Data, ParentType, ChildType, IOrderedCompositeBehaviour<Data, ParentType, ChildType>, GenericOrderedCompositeRandomVariableView<Data, ParentType, ChildType>>(source.getBehaviour()),
				new ReadOnlyProbabilityStrategy(probabilityStrategy)
		);
	}
}
