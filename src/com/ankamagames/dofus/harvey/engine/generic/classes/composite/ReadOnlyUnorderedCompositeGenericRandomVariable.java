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

	private final HashSet<ReadOnlyGenericRandomVariableWrapper<Data>> _defaultElements;
	private final HashSet<ReadOnlyGenericRandomVariableWrapper<Data>> _otherElements;

	public ReadOnlyUnorderedCompositeGenericRandomVariable(final AbstractCompositeGenericRandomVariable<Data, ?> base)
	{
		_defaultElements = getReadOnlyCopy(base.getDefaultElements());
		_otherElements = getReadOnlyCopy(base.getOtherElements());
	}

	@Override
	protected HashSet<ReadOnlyGenericRandomVariableWrapper<Data>> getDefaultElements()
	{
		return _defaultElements;
	}

	@Override
	protected HashSet<ReadOnlyGenericRandomVariableWrapper<Data>> getOtherElements()
	{
		return _otherElements;
	}
}