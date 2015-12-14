/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import java.util.Collection;
import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.classes.composite.sortedintervalset.ISortedIntervalSet;
import com.ankamagames.dofus.harvey.engine.classes.composite.sortedintervalset.IntervalTreeSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ReadOnlyOrderedCompositeRandomVariable<Data>
extends BaseOrderedCompositeRandomVariable
<
	Data,
	ReadOnlyOrderedRandomVariableWrapper<Data>
>
{
	static <Data> IntervalTreeSet<ReadOnlyOrderedRandomVariableWrapper<Data>, Data> getReadOnlyCopy(final ISortedIntervalSet<? extends BaseOrderedRandomVariableWrapper<Data, ?, ?, ?>, Data> base)
	{
		@SuppressWarnings("unchecked")
		final Comparator<? super Data> comparator = (Comparator<? super Data>) base.comparator();
		final IntervalTreeSet<ReadOnlyOrderedRandomVariableWrapper<Data>, Data> r = new IntervalTreeSet<ReadOnlyOrderedRandomVariableWrapper<Data>, Data>(comparator);
		for(final BaseOrderedRandomVariableWrapper<Data, ?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyOrderedRandomVariableWrapper<Data>(elmt));
		}
		return r;
	}

	public ReadOnlyOrderedCompositeRandomVariable(final BaseOrderedCompositeRandomVariable<Data, ?> base)
	{
		super(getReadOnlyCopy(base.getElements()), base.getComparator());
	}

	protected ReadOnlyOrderedCompositeRandomVariable(final BaseOrderedCompositeRandomVariable<Data, ?> base, final Collection<? extends ReadOnlyOrderedRandomVariableWrapper<Data>> elements)
	{
		super(elements, base.getComparator());
	}
}