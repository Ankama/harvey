package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.IOrderedLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class ReadOnlyOrderedLongRandomVariableWrapper
extends BaseOrderedLongRandomVariableWrapper<IOrderedLongRandomVariable, AbstractCompositeLongRandomVariable<?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyOrderedLongRandomVariableWrapper(final BaseOrderedLongRandomVariableWrapper<?, ?, ?> base)
	{
		super(base.getElement(), base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}

	public ReadOnlyOrderedLongRandomVariableWrapper(
			final BaseOrderedLongRandomVariableWrapper<?, ?, ?> base,
			final IOrderedLongRandomVariable element)
	{
		super(element, base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}