package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IOrderedIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class ReadOnlyOrderedIntRandomVariableWrapper
extends BaseOrderedIntRandomVariableWrapper<IOrderedIntRandomVariable, AbstractCompositeIntRandomVariable<?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyOrderedIntRandomVariableWrapper(final BaseOrderedIntRandomVariableWrapper<?, ?, ?> base)
	{
		super(base.getElement(), base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}

	public ReadOnlyOrderedIntRandomVariableWrapper(
			final BaseOrderedIntRandomVariableWrapper<?, ?, ?> base,
			final IOrderedIntRandomVariable element)
	{
		super(element, base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}