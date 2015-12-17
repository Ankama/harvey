package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ReadOnlyUnorderedCompositeByteRandomVariable
extends AbstractCompositeByteRandomVariable<ReadOnlyByteRandomVariableWrapper>
{
	static <Data> HashSet<ReadOnlyByteRandomVariableWrapper> getReadOnlyCopy(final Collection<? extends BaseByteRandomVariableWrapper<?, ?, ?>> base)
	{
		final HashSet<ReadOnlyByteRandomVariableWrapper> r = new HashSet<ReadOnlyByteRandomVariableWrapper>();
		for(final BaseByteRandomVariableWrapper<?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyByteRandomVariableWrapper(elmt));
		}
		return r;
	}

	private final HashSet<ReadOnlyByteRandomVariableWrapper> _elements;

	public ReadOnlyUnorderedCompositeByteRandomVariable(final AbstractCompositeByteRandomVariable<?> base)
	{
		_elements = getReadOnlyCopy(base.getElements());
	}

	@Override
	protected Collection<ReadOnlyByteRandomVariableWrapper> getElements()
	{
		return _elements;
	}
}