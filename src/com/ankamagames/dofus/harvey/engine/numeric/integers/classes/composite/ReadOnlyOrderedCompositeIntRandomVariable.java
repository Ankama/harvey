/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite.sortedintervalset.ISortedIntIntervalSet;
import com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite.sortedintervalset.IntIntervalTreeSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ReadOnlyOrderedCompositeIntRandomVariable
extends BaseOrderedCompositeIntRandomVariable<ReadOnlyOrderedIntRandomVariableWrapper>
{
	static IntIntervalTreeSet<ReadOnlyOrderedIntRandomVariableWrapper> getReadOnlyCopy(final ISortedIntIntervalSet<? extends BaseOrderedIntRandomVariableWrapper<?, ?, ?>> base)
	{
		final IntIntervalTreeSet<ReadOnlyOrderedIntRandomVariableWrapper> r = new IntIntervalTreeSet<ReadOnlyOrderedIntRandomVariableWrapper>();
		for(final BaseOrderedIntRandomVariableWrapper<?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyOrderedIntRandomVariableWrapper(elmt));
		}
		return r;
	}

	public ReadOnlyOrderedCompositeIntRandomVariable(final BaseOrderedCompositeIntRandomVariable<?> base)
	{
		super(getReadOnlyCopy(base.getElements()));
	}

	protected ReadOnlyOrderedCompositeIntRandomVariable(final BaseOrderedCompositeIntRandomVariable<?> base, final Collection<? extends ReadOnlyOrderedIntRandomVariableWrapper> elements)
	{
		super(elements);
	}
}