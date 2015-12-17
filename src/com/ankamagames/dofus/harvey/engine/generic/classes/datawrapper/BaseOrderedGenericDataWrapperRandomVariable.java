/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper.AbstractOrderedGenericDataWrapperRandomVariable.IOrderedInnerTypeDataWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedGenericDataWrapperRandomVariable<Data, ProbabilityStrategy extends IProbabilityStrategy>
extends AbstractOrderedGenericDataWrapperRandomVariable
<
	Data,
	ProbabilityStrategy,
	IOrderedInnerTypeDataWrapperRandomVariable<Data, ProbabilityStrategy>
>
{
	protected IOrderedInnerTypeDataWrapperRandomVariable<Data, ProbabilityStrategy> _inner;

	public BaseOrderedGenericDataWrapperRandomVariable(@Nullable final Data value, final ProbabilityStrategy probabilityStrategy, @Nullable final Comparator<Data> comparator)
	{
		if(comparator!=null)
			_inner = new BaseOrderedDataWrapperWithComparatorRandomVariable(value, probabilityStrategy, comparator);
		else
			_inner = new BaseOrderedComparableDataWrapperRandomVariable(value, probabilityStrategy);
	}

	public BaseOrderedGenericDataWrapperRandomVariable(@Nullable final Data value, final ProbabilityStrategy probabilityStrategy)
	{
		_inner = new BaseOrderedComparableDataWrapperRandomVariable(value, probabilityStrategy);
	}

	@Override
	protected IOrderedInnerTypeDataWrapperRandomVariable<Data, ProbabilityStrategy> getInner()
	{
		return _inner;
	}
}