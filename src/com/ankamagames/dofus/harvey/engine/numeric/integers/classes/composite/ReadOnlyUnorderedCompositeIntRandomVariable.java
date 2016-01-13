package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ReadOnlyUnorderedCompositeIntRandomVariable
extends AbstractCompositeIntRandomVariable<ReadOnlyIntRandomVariableWrapper>
{
	static <Data> HashSet<ReadOnlyIntRandomVariableWrapper> getReadOnlyCopy(final Collection<? extends BaseIntRandomVariableWrapper<?, ?, ?>> elements)
	{
		final HashSet<ReadOnlyIntRandomVariableWrapper> r = new HashSet<ReadOnlyIntRandomVariableWrapper>();
		for(final BaseIntRandomVariableWrapper<?, ?, ?> elmt:elements)
		{
			r.add(new ReadOnlyIntRandomVariableWrapper(elmt));
		}
		return r;
	}

	private final HashSet<ReadOnlyIntRandomVariableWrapper> _defaultElements;
	private final HashSet<ReadOnlyIntRandomVariableWrapper> _otherElements;

	public ReadOnlyUnorderedCompositeIntRandomVariable(final AbstractCompositeIntRandomVariable<?> base)
	{
		_defaultElements = getReadOnlyCopy(base.getDefaultElements());
		_otherElements = getReadOnlyCopy(base.getOtherElements());
	}

	@Override
	protected HashSet<ReadOnlyIntRandomVariableWrapper> getDefaultElements()
	{
		return _defaultElements;
	}

	@Override
	protected HashSet<ReadOnlyIntRandomVariableWrapper> getOtherElements()
	{
		return _otherElements;
	}
}