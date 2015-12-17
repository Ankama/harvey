/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper.AbstractEditableOrderedGenericDataWrapperRandomVariable.IEditableOrderedComparableDataWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedGenericDataWrapperRandomVariable<Data, ProbabilityStrategy extends IEditableProbabilityStrategy>
extends AbstractEditableOrderedGenericDataWrapperRandomVariable
<
	Data,
	ProbabilityStrategy,
	IEditableOrderedComparableDataWrapperRandomVariable<Data, ProbabilityStrategy>
>
{
	protected IEditableOrderedComparableDataWrapperRandomVariable<Data, ProbabilityStrategy> _inner;

	public BaseEditableOrderedGenericDataWrapperRandomVariable(@Nullable final Data value, final ProbabilityStrategy probabilityStrategy, @Nullable final Comparator<Data> comparator)
	{
		if(comparator!=null)
			_inner = new BaseEditableOrderedDataWrapperWithComparatorRandomVariable(value, probabilityStrategy, comparator);
		else
			_inner = new BaseEditableOrderedComparableDataWrapperRandomVariable(value, probabilityStrategy);
	}

	public BaseEditableOrderedGenericDataWrapperRandomVariable(@Nullable final Data value, final ProbabilityStrategy probabilityStrategy)
	{
		_inner = new BaseEditableOrderedComparableDataWrapperRandomVariable(value, probabilityStrategy);
	}

	@Override
	protected IEditableOrderedComparableDataWrapperRandomVariable<Data, ProbabilityStrategy> getInner()
	{
		return _inner;
	}
}