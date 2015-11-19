/**
 *
 */
package com.ankamagames.dofus.harvey.ordered;

import java.util.Iterator;
import java.util.Set;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IterableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.behaviours.composite.IOrderedCompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OrderedCompositeRandomVariable
<
	Data,
	ParentType extends ICompositeRandomVariable<Data, ?, ?>,
	ChildType extends IOrderedRandomVariable<Data, ?>,
	Behaviour extends IOrderedCompositeBehaviour<Data, ParentType, ChildType>,
	ProbabilityStrategy extends IProbabilityStrategy
>
	extends AbstractOrderedRandomVariable<Data, ParentType, Behaviour, ProbabilityStrategy>
	implements IterableCompositeRandomVariable<Data, ParentType, ChildType>
{
	public OrderedCompositeRandomVariable
	(
			final @Nullable ParentType parent,
			final Behaviour behaviour,
			final ProbabilityStrategy probabilityStrategy
	) throws OverOneProbabilityException
	{
		super(parent, behaviour, probabilityStrategy);
	}

	public OrderedCompositeRandomVariable
	(
			final Behaviour behaviour,
			final ProbabilityStrategy probabilityStrategy
	) throws OverOneProbabilityException
	{
		super(behaviour, probabilityStrategy);
	}

	public OrderedCompositeRandomVariable(
			final OrderedCompositeRandomVariable<Data, ParentType, ?, Behaviour, ProbabilityStrategy> source) throws OverOneProbabilityException
	{
		super(source._parent, source._behaviour, source._probabilityStrategy);
	}

	public OrderedCompositeRandomVariable(
			final OrderedCompositeRandomVariable<Data, ParentType, ?, Behaviour, ProbabilityStrategy> source, final @Nullable ParentType parent) throws OverOneProbabilityException
	{
		super(parent, source._behaviour, source._probabilityStrategy);
	}

	public OrderedCompositeRandomVariable(
			final OrderedCompositeRandomVariable<Data, ParentType, ?, ?, ProbabilityStrategy> source, final Behaviour behaviour) throws OverOneProbabilityException
	{
		super(source._parent, behaviour, source._probabilityStrategy);
	}

	public OrderedCompositeRandomVariable(
			final OrderedCompositeRandomVariable<Data, ParentType, ?, Behaviour, ?> source, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(source._parent, source._behaviour, probabilityStrategy);
	}

	public OrderedCompositeRandomVariable(
			final OrderedCompositeRandomVariable<Data, ?, ?, ?, ProbabilityStrategy> source, final @Nullable ParentType parent, final Behaviour behaviour) throws OverOneProbabilityException
	{
		super(parent, behaviour, source._probabilityStrategy);
	}

	public OrderedCompositeRandomVariable(
			final OrderedCompositeRandomVariable<Data, ?, ?, Behaviour, ?> source, final @Nullable ParentType parent, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(parent, source._behaviour, probabilityStrategy);
	}

	public OrderedCompositeRandomVariable(
			final OrderedCompositeRandomVariable<Data, ParentType, ?, ?, ?> source, final Behaviour behaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(source._parent, behaviour, probabilityStrategy);
	}

	@Override
	public Set<ChildType> getSubVariables()
	{
		return _behaviour.getSubVariables();
	}

	@Override
	public Iterator<ChildType> iterator()
	{
		return getSubVariables().iterator();
	}
}
