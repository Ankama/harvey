package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ReadOnlyUnorderedCompositeFloatRandomVariable
extends AbstractCompositeFloatRandomVariable<ReadOnlyFloatRandomVariableWrapper>
{
	static <Data> HashSet<ReadOnlyFloatRandomVariableWrapper> getReadOnlyCopy(final Collection<? extends BaseFloatRandomVariableWrapper<?, ?, ?>> elements)
	{
		final HashSet<ReadOnlyFloatRandomVariableWrapper> r = new HashSet<ReadOnlyFloatRandomVariableWrapper>();
		for(final BaseFloatRandomVariableWrapper<?, ?, ?> elmt:elements)
		{
			r.add(new ReadOnlyFloatRandomVariableWrapper(elmt));
		}
		return r;
	}

	private final HashSet<ReadOnlyFloatRandomVariableWrapper> _defaultElements;
	private final HashSet<ReadOnlyFloatRandomVariableWrapper> _otherElements;

	public ReadOnlyUnorderedCompositeFloatRandomVariable(final AbstractCompositeFloatRandomVariable<?> base)
	{
		_defaultElements = getReadOnlyCopy(base.getDefaultElements());
		_otherElements = getReadOnlyCopy(base.getOtherElements());
	}

	@Override
	protected HashSet<ReadOnlyFloatRandomVariableWrapper> getDefaultElements()
	{
		return _defaultElements;
	}

	@Override
	protected HashSet<ReadOnlyFloatRandomVariableWrapper> getOtherElements()
	{
		return _otherElements;
	}
}