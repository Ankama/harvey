package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ReadOnlyUnorderedCompositeDoubleRandomVariable
extends AbstractCompositeDoubleRandomVariable<ReadOnlyDoubleRandomVariableWrapper>
{
	static <Data> HashSet<ReadOnlyDoubleRandomVariableWrapper> getReadOnlyCopy(final Collection<? extends BaseDoubleRandomVariableWrapper<?, ?, ?>> base)
	{
		final HashSet<ReadOnlyDoubleRandomVariableWrapper> r = new HashSet<ReadOnlyDoubleRandomVariableWrapper>();
		for(final BaseDoubleRandomVariableWrapper<?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyDoubleRandomVariableWrapper(elmt));
		}
		return r;
	}

	private final HashSet<ReadOnlyDoubleRandomVariableWrapper> _elements;

	public ReadOnlyUnorderedCompositeDoubleRandomVariable(final AbstractCompositeDoubleRandomVariable<?> base)
	{
		_elements = getReadOnlyCopy(base.getElements());
	}

	@Override
	protected Collection<ReadOnlyDoubleRandomVariableWrapper> getElements()
	{
		return _elements;
	}
}