package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ReadOnlyUnorderedCompositeLongRandomVariable
extends AbstractCompositeLongRandomVariable<ReadOnlyLongRandomVariableWrapper>
{
	static <Data> HashSet<ReadOnlyLongRandomVariableWrapper> getReadOnlyCopy(final Collection<? extends BaseLongRandomVariableWrapper<?, ?, ?>> elements)
	{
		final HashSet<ReadOnlyLongRandomVariableWrapper> r = new HashSet<ReadOnlyLongRandomVariableWrapper>();
		for(final BaseLongRandomVariableWrapper<?, ?, ?> elmt:elements)
		{
			r.add(new ReadOnlyLongRandomVariableWrapper(elmt));
		}
		return r;
	}

	private final HashSet<ReadOnlyLongRandomVariableWrapper> _defaultElements;
	private final HashSet<ReadOnlyLongRandomVariableWrapper> _otherElements;

	public ReadOnlyUnorderedCompositeLongRandomVariable(final AbstractCompositeLongRandomVariable<?> base)
	{
		_defaultElements = getReadOnlyCopy(base.getDefaultElements());
		_otherElements = getReadOnlyCopy(base.getOtherElements());
	}

	@Override
	protected HashSet<ReadOnlyLongRandomVariableWrapper> getDefaultElements()
	{
		return _defaultElements;
	}

	@Override
	protected HashSet<ReadOnlyLongRandomVariableWrapper> getOtherElements()
	{
		return _otherElements;
	}
}