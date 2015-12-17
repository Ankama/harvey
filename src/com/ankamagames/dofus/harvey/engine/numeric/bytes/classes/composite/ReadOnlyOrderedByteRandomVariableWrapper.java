package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IOrderedByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class ReadOnlyOrderedByteRandomVariableWrapper
extends BaseOrderedByteRandomVariableWrapper<IOrderedByteRandomVariable, AbstractCompositeByteRandomVariable<?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyOrderedByteRandomVariableWrapper(final BaseOrderedByteRandomVariableWrapper<?, ?, ?> base)
	{
		super(base.getElement(), base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}

	public ReadOnlyOrderedByteRandomVariableWrapper(
			final BaseOrderedByteRandomVariableWrapper<?, ?, ?> base,
			final IOrderedByteRandomVariable element)
	{
		super(element, base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}