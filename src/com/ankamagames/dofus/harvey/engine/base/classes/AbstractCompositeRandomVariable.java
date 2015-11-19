package com.ankamagames.dofus.harvey.engine.base.classes;



import java.util.Iterator;
import java.util.Set;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IterableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.behaviours.composite.AbstractCompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public abstract  class AbstractCompositeRandomVariable
<
	Data,
	ParentType extends ICompositeRandomVariable<Data, ?, ?>,
	ChildType extends IRandomVariable<Data, ?>,
	Behaviour extends AbstractCompositeBehaviour<Data, ChildType, ? extends IRandomVariable<Data, ParentType>>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends BaseRandomVariable<Data, ParentType, Behaviour, ProbabilityStrategy>
implements IterableCompositeRandomVariable<Data, ParentType, ChildType>
{

	public AbstractCompositeRandomVariable(
			final @Nullable ParentType parent,
			final Behaviour randomVariableBehaviour,
			final ProbabilityStrategy probabilityStrategy)
			throws OverOneProbabilityException
	{
		super(parent, randomVariableBehaviour, probabilityStrategy);
	}

	public AbstractCompositeRandomVariable(
			final Behaviour randomVariableBehaviour,
			final ProbabilityStrategy probabilityStrategy)
			throws OverOneProbabilityException
	{
		super(randomVariableBehaviour, probabilityStrategy);
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