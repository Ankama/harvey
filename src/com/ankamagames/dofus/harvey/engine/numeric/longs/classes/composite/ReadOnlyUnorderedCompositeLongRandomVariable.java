package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ReadOnlyUnorderedCompositeLongRandomVariable
extends AbstractCompositeLongRandomVariable<ReadOnlyLongRandomVariableWrapper>
{
	static <Data> HashSet<ReadOnlyLongRandomVariableWrapper> getReadOnlyCopy(final Collection<? extends BaseLongRandomVariableWrapper<?, ?, ?>> base)
	{
		final HashSet<ReadOnlyLongRandomVariableWrapper> r = new HashSet<ReadOnlyLongRandomVariableWrapper>();
		for(final BaseLongRandomVariableWrapper<?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyLongRandomVariableWrapper(elmt));
		}
		return r;
	}

	private final HashSet<ReadOnlyLongRandomVariableWrapper> _elements;

	public ReadOnlyUnorderedCompositeLongRandomVariable(final AbstractCompositeLongRandomVariable<?> base)
	{
		_elements = getReadOnlyCopy(base.getElements());
	}

	@Override
	protected Collection<ReadOnlyLongRandomVariableWrapper> getElements()
	{
		return _elements;
	}
}