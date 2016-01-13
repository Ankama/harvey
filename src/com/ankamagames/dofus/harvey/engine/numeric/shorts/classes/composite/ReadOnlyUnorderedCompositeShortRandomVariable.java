package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ReadOnlyUnorderedCompositeShortRandomVariable
extends AbstractCompositeShortRandomVariable<ReadOnlyShortRandomVariableWrapper>
{
	static <Data> HashSet<ReadOnlyShortRandomVariableWrapper> getReadOnlyCopy(final Collection<? extends BaseShortRandomVariableWrapper<?, ?, ?>> elements)
	{
		final HashSet<ReadOnlyShortRandomVariableWrapper> r = new HashSet<ReadOnlyShortRandomVariableWrapper>();
		for(final BaseShortRandomVariableWrapper<?, ?, ?> elmt:elements)
		{
			r.add(new ReadOnlyShortRandomVariableWrapper(elmt));
		}
		return r;
	}

	private final HashSet<ReadOnlyShortRandomVariableWrapper> _defaultElements;
	private final HashSet<ReadOnlyShortRandomVariableWrapper> _otherElements;

	public ReadOnlyUnorderedCompositeShortRandomVariable(final AbstractCompositeShortRandomVariable<?> base)
	{
		_defaultElements = getReadOnlyCopy(base.getDefaultElements());
		_otherElements = getReadOnlyCopy(base.getOtherElements());
	}

	@Override
	protected HashSet<ReadOnlyShortRandomVariableWrapper> getDefaultElements()
	{
		return _defaultElements;
	}

	@Override
	protected HashSet<ReadOnlyShortRandomVariableWrapper> getOtherElements()
	{
		return _otherElements;
	}
}