package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ReadOnlyUnorderedCompositeShortRandomVariable
extends AbstractCompositeShortRandomVariable<ReadOnlyShortRandomVariableWrapper>
{
	static <Data> HashSet<ReadOnlyShortRandomVariableWrapper> getReadOnlyCopy(final Collection<? extends BaseShortRandomVariableWrapper<?, ?, ?>> base)
	{
		final HashSet<ReadOnlyShortRandomVariableWrapper> r = new HashSet<ReadOnlyShortRandomVariableWrapper>();
		for(final BaseShortRandomVariableWrapper<?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyShortRandomVariableWrapper(elmt));
		}
		return r;
	}

	private final HashSet<ReadOnlyShortRandomVariableWrapper> _elements;

	public ReadOnlyUnorderedCompositeShortRandomVariable(final AbstractCompositeShortRandomVariable<?> base)
	{
		_elements = getReadOnlyCopy(base.getElements());
	}

	@Override
	protected Collection<ReadOnlyShortRandomVariableWrapper> getElements()
	{
		return _elements;
	}
}