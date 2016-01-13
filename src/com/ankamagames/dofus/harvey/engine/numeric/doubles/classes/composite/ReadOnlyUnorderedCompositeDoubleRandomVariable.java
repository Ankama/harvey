package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ReadOnlyUnorderedCompositeDoubleRandomVariable
extends AbstractCompositeDoubleRandomVariable<ReadOnlyDoubleRandomVariableWrapper>
{
	static <Data> HashSet<ReadOnlyDoubleRandomVariableWrapper> getReadOnlyCopy(final Collection<? extends BaseDoubleRandomVariableWrapper<?, ?, ?>> elements)
	{
		final HashSet<ReadOnlyDoubleRandomVariableWrapper> r = new HashSet<ReadOnlyDoubleRandomVariableWrapper>();
		for(final BaseDoubleRandomVariableWrapper<?, ?, ?> elmt:elements)
		{
			r.add(new ReadOnlyDoubleRandomVariableWrapper(elmt));
		}
		return r;
	}

	private final HashSet<ReadOnlyDoubleRandomVariableWrapper> _defaultElements;
	private final HashSet<ReadOnlyDoubleRandomVariableWrapper> _otherElements;

	public ReadOnlyUnorderedCompositeDoubleRandomVariable(final AbstractCompositeDoubleRandomVariable<?> base)
	{
		_defaultElements = getReadOnlyCopy(base.getDefaultElements());
		_otherElements = getReadOnlyCopy(base.getOtherElements());
	}

	@Override
	protected HashSet<ReadOnlyDoubleRandomVariableWrapper> getDefaultElements()
	{
		return _defaultElements;
	}

	@Override
	protected HashSet<ReadOnlyDoubleRandomVariableWrapper> getOtherElements()
	{
		return _otherElements;
	}
}