/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import java.util.Collection;
import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.generic.classes.composite.sortedintervalset.ISortedGenericIntervalSet;
import com.ankamagames.dofus.harvey.engine.generic.classes.composite.sortedintervalset.GenericIntervalTreeSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ReadOnlyOrderedCompositeGenericRandomVariable<Data>
extends BaseOrderedCompositeGenericRandomVariable
<
	Data,
	ReadOnlyOrderedGenericRandomVariableWrapper<Data>
>
{
	static <Data> GenericIntervalTreeSet<ReadOnlyOrderedGenericRandomVariableWrapper<Data>, Data> getReadOnlyCopy(final ISortedGenericIntervalSet<? extends BaseOrderedGenericRandomVariableWrapper<Data, ?, ?, ?>, Data> base)
	{
		@SuppressWarnings("unchecked")
		final Comparator<? super Data> comparator = (Comparator<? super Data>) base.comparator();
		final GenericIntervalTreeSet<ReadOnlyOrderedGenericRandomVariableWrapper<Data>, Data> r = new GenericIntervalTreeSet<ReadOnlyOrderedGenericRandomVariableWrapper<Data>, Data>(comparator);
		for(final BaseOrderedGenericRandomVariableWrapper<Data, ?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyOrderedGenericRandomVariableWrapper<Data>(elmt));
		}
		return r;
	}

	public ReadOnlyOrderedCompositeGenericRandomVariable(final BaseOrderedCompositeGenericRandomVariable<Data, ?> base)
	{
		super(getReadOnlyCopy(base.getElements()), base.getComparator());
	}

	protected ReadOnlyOrderedCompositeGenericRandomVariable(final BaseOrderedCompositeGenericRandomVariable<Data, ?> base, final Collection<? extends ReadOnlyOrderedGenericRandomVariableWrapper<Data>> elements)
	{
		super(elements, base.getComparator());
	}
}