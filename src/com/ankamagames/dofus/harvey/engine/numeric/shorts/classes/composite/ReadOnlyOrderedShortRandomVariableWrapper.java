package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IOrderedShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class ReadOnlyOrderedShortRandomVariableWrapper
extends BaseOrderedShortRandomVariableWrapper<IOrderedShortRandomVariable, AbstractCompositeShortRandomVariable<?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyOrderedShortRandomVariableWrapper(final BaseOrderedShortRandomVariableWrapper<?, ?, ?> base)
	{
		super(base.getElement(), base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}

	public ReadOnlyOrderedShortRandomVariableWrapper(
			final BaseOrderedShortRandomVariableWrapper<?, ?, ?> base,
			final IOrderedShortRandomVariable element)
	{
		super(element, base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}