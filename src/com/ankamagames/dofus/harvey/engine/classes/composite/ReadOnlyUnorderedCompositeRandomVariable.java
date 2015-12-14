package com.ankamagames.dofus.harvey.engine.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ReadOnlyUnorderedCompositeRandomVariable<Data>
extends AbstractCompositeRandomVariable
<
	Data,
	ReadOnlyRandomVariableWrapper<Data>
>
{
	static <Data> HashSet<ReadOnlyRandomVariableWrapper<Data>> getReadOnlyCopy(final Collection<? extends BaseRandomVariableWrapper<Data, ?, ?, ?>> base)
	{
		final HashSet<ReadOnlyRandomVariableWrapper<Data>> r = new HashSet<ReadOnlyRandomVariableWrapper<Data>>();
		for(final BaseRandomVariableWrapper<Data, ?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyRandomVariableWrapper<Data>(elmt));
		}
		return r;
	}

	private final HashSet<ReadOnlyRandomVariableWrapper<Data>> _elements;

	public ReadOnlyUnorderedCompositeRandomVariable(final AbstractCompositeRandomVariable<Data, ?> base)
	{
		_elements = getReadOnlyCopy(base.getElements());
	}

	@Override
	protected Collection<ReadOnlyRandomVariableWrapper<Data>> getElements()
	{
		return _elements;
	}
}