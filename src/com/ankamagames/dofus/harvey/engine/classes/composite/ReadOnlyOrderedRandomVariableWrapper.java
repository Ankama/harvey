package com.ankamagames.dofus.harvey.engine.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class ReadOnlyOrderedRandomVariableWrapper<Data>
extends BaseOrderedRandomVariableWrapper<Data, IOrderedRandomVariable<Data>, AbstractCompositeRandomVariable<Data, ?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyOrderedRandomVariableWrapper(final BaseOrderedRandomVariableWrapper<Data, ?, ?, ?> base)
	{
		super(base._element, base._parent, new ReadOnlyProbabilityStrategy(base._probabilityStrategy));
	}

	public ReadOnlyOrderedRandomVariableWrapper(
			final BaseOrderedRandomVariableWrapper<Data, ?, ?, ?> base,
			final IOrderedRandomVariable<Data> element)
	{
		super(element, base._parent, new ReadOnlyProbabilityStrategy(base._probabilityStrategy));
	}
}