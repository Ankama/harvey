package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ReadOnlyUnorderedCompositeByteRandomVariable
extends AbstractCompositeByteRandomVariable<ReadOnlyByteRandomVariableWrapper>
{
	static <Data> HashSet<ReadOnlyByteRandomVariableWrapper> getReadOnlyCopy(final Collection<? extends BaseByteRandomVariableWrapper<?, ?, ?>> elements)
	{
		final HashSet<ReadOnlyByteRandomVariableWrapper> r = new HashSet<ReadOnlyByteRandomVariableWrapper>();
		for(final BaseByteRandomVariableWrapper<?, ?, ?> elmt:elements)
		{
			r.add(new ReadOnlyByteRandomVariableWrapper(elmt));
		}
		return r;
	}

	private final HashSet<ReadOnlyByteRandomVariableWrapper> _defaultElements;
	private final HashSet<ReadOnlyByteRandomVariableWrapper> _otherElements;

	public ReadOnlyUnorderedCompositeByteRandomVariable(final AbstractCompositeByteRandomVariable<?> base)
	{
		_defaultElements = getReadOnlyCopy(base.getDefaultElements());
		_otherElements = getReadOnlyCopy(base.getOtherElements());
	}

	@Override
	protected HashSet<ReadOnlyByteRandomVariableWrapper> getDefaultElements()
	{
		return _defaultElements;
	}

	@Override
	protected HashSet<ReadOnlyByteRandomVariableWrapper> getOtherElements()
	{
		return _otherElements;
	}
}