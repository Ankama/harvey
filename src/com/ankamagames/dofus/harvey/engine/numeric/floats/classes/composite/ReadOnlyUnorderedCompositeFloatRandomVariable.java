package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ReadOnlyUnorderedCompositeFloatRandomVariable
extends AbstractCompositeFloatRandomVariable<ReadOnlyFloatRandomVariableWrapper>
{
	static <Data> HashSet<ReadOnlyFloatRandomVariableWrapper> getReadOnlyCopy(final Collection<? extends BaseFloatRandomVariableWrapper<?, ?, ?>> base)
	{
		final HashSet<ReadOnlyFloatRandomVariableWrapper> r = new HashSet<ReadOnlyFloatRandomVariableWrapper>();
		for(final BaseFloatRandomVariableWrapper<?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyFloatRandomVariableWrapper(elmt));
		}
		return r;
	}

	private final HashSet<ReadOnlyFloatRandomVariableWrapper> _elements;

	public ReadOnlyUnorderedCompositeFloatRandomVariable(final AbstractCompositeFloatRandomVariable<?> base)
	{
		_elements = getReadOnlyCopy(base.getElements());
	}

	@Override
	protected Collection<ReadOnlyFloatRandomVariableWrapper> getElements()
	{
		return _elements;
	}
}