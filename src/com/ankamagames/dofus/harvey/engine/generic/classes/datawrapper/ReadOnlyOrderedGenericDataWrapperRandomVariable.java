/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyOrderedGenericDataWrapperRandomVariable<Data>
extends BaseOrderedGenericDataWrapperRandomVariable<Data, IProbabilityStrategy>
{
	public ReadOnlyOrderedGenericDataWrapperRandomVariable(final AbstractOrderedGenericDataWrapperRandomVariable<Data, ?, ?> base)
	{
		super(base.getInner().getValue(), new ReadOnlyProbabilityStrategy(base.getInner().getProbabilityStrategy()));
	}

	public ReadOnlyOrderedGenericDataWrapperRandomVariable(final AbstractOrderedGenericDataWrapperRandomVariable<Data, ?, ?> base, final Comparator<Data> comparator)
	{
		super(base.getInner().getValue(), new ReadOnlyProbabilityStrategy(base.getInner().getProbabilityStrategy()), comparator);
	}
}