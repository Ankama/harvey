/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite.sortedintervalset.FloatIntervalTreeSet;
import com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite.sortedintervalset.ISortedFloatIntervalSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ReadOnlyOrderedCompositeFloatRandomVariable
extends BaseOrderedCompositeFloatRandomVariable<ReadOnlyOrderedFloatRandomVariableWrapper>
{
	static FloatIntervalTreeSet<ReadOnlyOrderedFloatRandomVariableWrapper> getReadOnlyCopy(final ISortedFloatIntervalSet<? extends BaseOrderedFloatRandomVariableWrapper<?, ?, ?>> base)
	{
		final FloatIntervalTreeSet<ReadOnlyOrderedFloatRandomVariableWrapper> r = new FloatIntervalTreeSet<ReadOnlyOrderedFloatRandomVariableWrapper>();
		for(final BaseOrderedFloatRandomVariableWrapper<?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyOrderedFloatRandomVariableWrapper(elmt));
		}
		return r;
	}

	public ReadOnlyOrderedCompositeFloatRandomVariable(final BaseOrderedCompositeFloatRandomVariable<?> base)
	{
		super(getReadOnlyCopy(base.getElements()));
	}

	protected ReadOnlyOrderedCompositeFloatRandomVariable(final BaseOrderedCompositeFloatRandomVariable<?> base, final Collection<? extends ReadOnlyOrderedFloatRandomVariableWrapper> elements)
	{
		super(elements);
	}
}