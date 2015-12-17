package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.generic.interfaces.IOrderedGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class ReadOnlyOrderedGenericRandomVariableWrapper<Data>
extends BaseOrderedGenericRandomVariableWrapper<Data, IOrderedGenericRandomVariable<Data>, AbstractCompositeGenericRandomVariable<Data, ?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyOrderedGenericRandomVariableWrapper(final BaseOrderedGenericRandomVariableWrapper<Data, ?, ?, ?> base)
	{
		super(base.getElement(), base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}

	public ReadOnlyOrderedGenericRandomVariableWrapper(
			final BaseOrderedGenericRandomVariableWrapper<Data, ?, ?, ?> base,
			final IOrderedGenericRandomVariable<Data> element)
	{
		super(element, base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}