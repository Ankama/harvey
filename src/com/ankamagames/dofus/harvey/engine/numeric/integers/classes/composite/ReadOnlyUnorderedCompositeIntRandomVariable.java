package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ReadOnlyUnorderedCompositeIntRandomVariable
extends AbstractCompositeIntRandomVariable<ReadOnlyIntRandomVariableWrapper>
{
	static <Data> HashSet<ReadOnlyIntRandomVariableWrapper> getReadOnlyCopy(final Collection<? extends BaseIntRandomVariableWrapper<?, ?, ?>> base)
	{
		final HashSet<ReadOnlyIntRandomVariableWrapper> r = new HashSet<ReadOnlyIntRandomVariableWrapper>();
		for(final BaseIntRandomVariableWrapper<?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyIntRandomVariableWrapper(elmt));
		}
		return r;
	}

	private final HashSet<ReadOnlyIntRandomVariableWrapper> _elements;

	public ReadOnlyUnorderedCompositeIntRandomVariable(final AbstractCompositeIntRandomVariable<?> base)
	{
		_elements = getReadOnlyCopy(base.getElements());
	}

	@Override
	protected Collection<ReadOnlyIntRandomVariableWrapper> getElements()
	{
		return _elements;
	}
}