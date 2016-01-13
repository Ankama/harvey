/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.sortedintervalset.ByteIntervalTreeSet;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite.sortedintervalset.ISortedByteIntervalSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ReadOnlyOrderedCompositeByteRandomVariable
extends BaseOrderedCompositeByteRandomVariable<ReadOnlyOrderedByteRandomVariableWrapper>
{
	static ByteIntervalTreeSet<ReadOnlyOrderedByteRandomVariableWrapper> getReadOnlyCopy(final ISortedByteIntervalSet<? extends BaseOrderedByteRandomVariableWrapper<?, ?, ?>> base)
	{
		final ByteIntervalTreeSet<ReadOnlyOrderedByteRandomVariableWrapper> r = new ByteIntervalTreeSet<ReadOnlyOrderedByteRandomVariableWrapper>();
		for(final BaseOrderedByteRandomVariableWrapper<?, ?, ?> elmt:base)
		{
			r.add(new ReadOnlyOrderedByteRandomVariableWrapper(elmt));
		}
		return r;
	}

	public ReadOnlyOrderedCompositeByteRandomVariable(final BaseOrderedCompositeByteRandomVariable<?> base)
	{
		super(getReadOnlyCopy(base.getDefaultElements()), getReadOnlyCopy(base.getOtherElements()));
	}

	protected ReadOnlyOrderedCompositeByteRandomVariable(final BaseOrderedCompositeByteRandomVariable<?> base,
			final Collection<? extends ReadOnlyOrderedByteRandomVariableWrapper> defaultElements,
			final Collection<? extends ReadOnlyOrderedByteRandomVariableWrapper> otherElements)
	{
		super(defaultElements, otherElements);
	}
}