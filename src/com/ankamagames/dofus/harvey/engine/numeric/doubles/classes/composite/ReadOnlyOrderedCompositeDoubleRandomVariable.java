/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite.sortedintervalset.DoubleIntervalTreeSet;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite.sortedintervalset.ISortedDoubleIntervalSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ReadOnlyOrderedCompositeDoubleRandomVariable
extends BaseOrderedCompositeDoubleRandomVariable<ReadOnlyOrderedDoubleRandomVariableWrapper>
{
	static DoubleIntervalTreeSet<ReadOnlyOrderedDoubleRandomVariableWrapper> getReadOnlyCopy(final ISortedDoubleIntervalSet<? extends BaseOrderedDoubleRandomVariableWrapper<?, ?, ?>> base)
	{
		final DoubleIntervalTreeSet<ReadOnlyOrderedDoubleRandomVariableWrapper> r = new DoubleIntervalTreeSet<ReadOnlyOrderedDoubleRandomVariableWrapper>();
		for(final BaseOrderedDoubleRandomVariableWrapper<?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyOrderedDoubleRandomVariableWrapper(elmt));
		}
		return r;
	}

	public ReadOnlyOrderedCompositeDoubleRandomVariable(final BaseOrderedCompositeDoubleRandomVariable<?> base)
	{
		super(getReadOnlyCopy(base.getDefaultElements()), getReadOnlyCopy(base.getOtherElements()));
	}

	protected ReadOnlyOrderedCompositeDoubleRandomVariable(final BaseOrderedCompositeDoubleRandomVariable<?> base,
			final Collection<? extends ReadOnlyOrderedDoubleRandomVariableWrapper> defaultElements,
			final Collection<? extends ReadOnlyOrderedDoubleRandomVariableWrapper> otherElements)
	{
		super(defaultElements, otherElements);
	}
}