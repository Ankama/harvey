/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite.sortedintervalset.ShortIntervalTreeSet;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite.sortedintervalset.ISortedShortIntervalSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ReadOnlyOrderedCompositeShortRandomVariable
extends BaseOrderedCompositeShortRandomVariable<ReadOnlyOrderedShortRandomVariableWrapper>
{
	static ShortIntervalTreeSet<ReadOnlyOrderedShortRandomVariableWrapper> getReadOnlyCopy(final ISortedShortIntervalSet<? extends BaseOrderedShortRandomVariableWrapper<?, ?, ?>> base)
	{
		final ShortIntervalTreeSet<ReadOnlyOrderedShortRandomVariableWrapper> r = new ShortIntervalTreeSet<ReadOnlyOrderedShortRandomVariableWrapper>();
		for(final BaseOrderedShortRandomVariableWrapper<?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyOrderedShortRandomVariableWrapper(elmt));
		}
		return r;
	}

	public ReadOnlyOrderedCompositeShortRandomVariable(final BaseOrderedCompositeShortRandomVariable<?> base)
	{
		super(getReadOnlyCopy(base.getDefaultElements()), getReadOnlyCopy(base.getOtherElements()));
	}

	protected ReadOnlyOrderedCompositeShortRandomVariable(final BaseOrderedCompositeShortRandomVariable<?> base,
			final Collection<? extends ReadOnlyOrderedShortRandomVariableWrapper> defaultElements,
			final Collection<? extends ReadOnlyOrderedShortRandomVariableWrapper> otherElements)
	{
		super(defaultElements, otherElements);
	}
}