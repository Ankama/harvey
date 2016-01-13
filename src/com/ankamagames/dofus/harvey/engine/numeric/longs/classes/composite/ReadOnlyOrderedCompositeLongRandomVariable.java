/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite.sortedintervalset.LongIntervalTreeSet;
import com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite.sortedintervalset.ISortedLongIntervalSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ReadOnlyOrderedCompositeLongRandomVariable
extends BaseOrderedCompositeLongRandomVariable<ReadOnlyOrderedLongRandomVariableWrapper>
{
	static LongIntervalTreeSet<ReadOnlyOrderedLongRandomVariableWrapper> getReadOnlyCopy(final ISortedLongIntervalSet<? extends BaseOrderedLongRandomVariableWrapper<?, ?, ?>> base)
	{
		final LongIntervalTreeSet<ReadOnlyOrderedLongRandomVariableWrapper> r = new LongIntervalTreeSet<ReadOnlyOrderedLongRandomVariableWrapper>();
		for(final BaseOrderedLongRandomVariableWrapper<?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyOrderedLongRandomVariableWrapper(elmt));
		}
		return r;
	}

	public ReadOnlyOrderedCompositeLongRandomVariable(final BaseOrderedCompositeLongRandomVariable<?> base)
	{
		super(getReadOnlyCopy(base.getDefaultElements()), getReadOnlyCopy(base.getOtherElements()));
	}

	protected ReadOnlyOrderedCompositeLongRandomVariable(final BaseOrderedCompositeLongRandomVariable<?> base,
			final Collection<? extends ReadOnlyOrderedLongRandomVariableWrapper> defaultElements,
			final Collection<? extends ReadOnlyOrderedLongRandomVariableWrapper> otherElements)
	{
		super(defaultElements, otherElements);
	}
}