/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.datawrapper;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyOrderedDataWrapperRandomVariable<Data>
extends BaseOrderedDataWrapperRandomVariable<Data, IProbabilityStrategy>
{
	public ReadOnlyOrderedDataWrapperRandomVariable(final AbstractOrderedDataWrapperRandomVariable<Data, ?, ?> base)
	{
		super(base.getInner().getValue(), new ReadOnlyProbabilityStrategy(base.getInner().getProbabilityStrategy()));
	}

	public ReadOnlyOrderedDataWrapperRandomVariable(final AbstractOrderedDataWrapperRandomVariable<Data, ?, ?> base, final Comparator<Data> comparator)
	{
		super(base.getInner().getValue(), new ReadOnlyProbabilityStrategy(base.getInner().getProbabilityStrategy()), comparator);
	}
}