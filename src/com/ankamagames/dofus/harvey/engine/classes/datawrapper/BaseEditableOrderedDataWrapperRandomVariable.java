/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.datawrapper;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.classes.datawrapper.AbstractEditableOrderedDataWrapperRandomVariable.IEditableOrderedComparableDataWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedDataWrapperRandomVariable<Data, ProbabilityStrategy extends IEditableProbabilityStrategy>
extends AbstractEditableOrderedDataWrapperRandomVariable
<
	Data,
	ProbabilityStrategy,
	IEditableOrderedComparableDataWrapperRandomVariable<Data, ProbabilityStrategy>
>
{
	protected IEditableOrderedComparableDataWrapperRandomVariable<Data, ProbabilityStrategy> _inner;

	public BaseEditableOrderedDataWrapperRandomVariable(@Nullable final Data value, final ProbabilityStrategy probabilityStrategy, @Nullable final Comparator<Data> comparator)
	{
		if(comparator!=null)
			_inner = new BaseEditableOrderedDataWrapperWithComparatorRandomVariable(value, probabilityStrategy, comparator);
		else
			_inner = new BaseEditableOrderedComparableDataWrapperRandomVariable(value, probabilityStrategy);
	}

	public BaseEditableOrderedDataWrapperRandomVariable(@Nullable final Data value, final ProbabilityStrategy probabilityStrategy)
	{
		_inner = new BaseEditableOrderedComparableDataWrapperRandomVariable(value, probabilityStrategy);
	}

	@Override
	protected IEditableOrderedComparableDataWrapperRandomVariable<Data, ProbabilityStrategy> getInner()
	{
		return _inner;
	}
}