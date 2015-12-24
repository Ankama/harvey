package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IOrderedDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class ReadOnlyOrderedDoubleRandomVariableWrapper
extends BaseOrderedDoubleRandomVariableWrapper<IOrderedDoubleRandomVariable, AbstractCompositeDoubleRandomVariable<?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyOrderedDoubleRandomVariableWrapper(final BaseOrderedDoubleRandomVariableWrapper<?, ?, ?> base)
	{
		super(base.getElement(), base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}

	public ReadOnlyOrderedDoubleRandomVariableWrapper(
			final BaseOrderedDoubleRandomVariableWrapper<?, ?, ?> base,
			final IOrderedDoubleRandomVariable element)
	{
		super(element, base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}