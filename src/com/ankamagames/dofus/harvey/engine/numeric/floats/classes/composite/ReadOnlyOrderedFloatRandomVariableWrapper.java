package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IOrderedFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class ReadOnlyOrderedFloatRandomVariableWrapper
extends BaseOrderedFloatRandomVariableWrapper<IOrderedFloatRandomVariable, AbstractCompositeFloatRandomVariable<?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyOrderedFloatRandomVariableWrapper(final BaseOrderedFloatRandomVariableWrapper<?, ?, ?> base)
	{
		super(base.getElement(), base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}

	public ReadOnlyOrderedFloatRandomVariableWrapper(
			final BaseOrderedFloatRandomVariableWrapper<?, ?, ?> base,
			final IOrderedFloatRandomVariable element)
	{
		super(element, base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}