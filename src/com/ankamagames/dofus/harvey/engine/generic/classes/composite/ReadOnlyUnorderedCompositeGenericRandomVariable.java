package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public final class ReadOnlyUnorderedCompositeGenericRandomVariable<Data>
extends AbstractCompositeGenericRandomVariable
<
	Data,
	ReadOnlyGenericRandomVariableWrapper<Data>
>
{
	static <Data> HashSet<ReadOnlyGenericRandomVariableWrapper<Data>> getReadOnlyCopy(final Collection<? extends BaseGenericRandomVariableWrapper<Data, ?, ?, ?>> base)
	{
		final HashSet<ReadOnlyGenericRandomVariableWrapper<Data>> r = new HashSet<ReadOnlyGenericRandomVariableWrapper<Data>>();
		for(final BaseGenericRandomVariableWrapper<Data, ?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyGenericRandomVariableWrapper<Data>(elmt));
		}
		return r;
	}

	private final HashSet<ReadOnlyGenericRandomVariableWrapper<Data>> _elements;

	public ReadOnlyUnorderedCompositeGenericRandomVariable(final AbstractCompositeGenericRandomVariable<Data, ?> base)
	{
		_elements = getReadOnlyCopy(base.getElements());
	}

	@Override
	protected Collection<ReadOnlyGenericRandomVariableWrapper<Data>> getElements()
	{
		return _elements;
	}
}