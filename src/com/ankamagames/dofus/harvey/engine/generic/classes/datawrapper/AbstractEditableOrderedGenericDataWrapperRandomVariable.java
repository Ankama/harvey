/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper.AbstractEditableOrderedGenericDataWrapperRandomVariable.IEditableOrderedComparableDataWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.generic.interfaces.IEditableOrderedGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractEditableOrderedGenericDataWrapperRandomVariable
<
	Data,
	ProbabilityStrategy extends IEditableProbabilityStrategy,
	InnerType extends IEditableOrderedComparableDataWrapperRandomVariable<Data, ProbabilityStrategy>
>
extends AbstractOrderedGenericDataWrapperRandomVariable<Data, ProbabilityStrategy, InnerType>
implements IEditableOrderedGenericRandomVariable<Data>
{
	protected interface IEditableOrderedComparableDataWrapperRandomVariable<Data, ProbabilityStrategy extends IEditableProbabilityStrategy>
	extends IOrderedInnerTypeDataWrapperRandomVariable<Data, ProbabilityStrategy>, IEditableOrderedGenericRandomVariable<Data>
	{ }

	protected class BaseEditableOrderedComparableDataWrapperRandomVariable
	extends AbstractOrderedGenericDataWrapperRandomVariable<Data, ProbabilityStrategy, ?>.BaseOrderedComparableDataWrapperRandomVariable
	implements IEditableOrderedComparableDataWrapperRandomVariable<Data, ProbabilityStrategy>
	{
		BridgedGenericDataWrapperRandomVariableEditor<Data, ?> _editor;

		public BaseEditableOrderedComparableDataWrapperRandomVariable(
				final @Nullable Data value, final ProbabilityStrategy probabilityStrategy)
		{
			super(value, probabilityStrategy);
			_editor = new BridgedGenericDataWrapperRandomVariableEditor<Data, AbstractEditableOrderedGenericDataWrapperRandomVariable<Data, ProbabilityStrategy, ?>.BaseEditableOrderedComparableDataWrapperRandomVariable>(this);
		}

		@Override
		public boolean setProbabilityOf(@Nullable final Data value, final int probability)
		{
			return _editor.setProbabilityOf(value, probability);
		}

		@Override
		public boolean remove(@Nullable final Data value)
		{
			return _editor.remove(value);
		}

		@Override
		public boolean addProbabilityTo(@Nullable final Data value, final int delta)
		{
			return _editor.addProbabilityTo(value, delta);
		}

		@Override
		public boolean removeProbabilityTo(@Nullable final Data value, final int delta)
		{
			return _editor.removeProbabilityTo(value, delta);
		}

		@Override
		public void clear()
		{
			_editor.clear();
		}
	}

	protected class BaseEditableOrderedDataWrapperWithComparatorRandomVariable
	extends AbstractOrderedGenericDataWrapperRandomVariable<Data, ProbabilityStrategy, ?>.BaseOrderedDataWrapperWithComparatorRandomVariable
	implements IEditableOrderedComparableDataWrapperRandomVariable<Data, ProbabilityStrategy>
	{
		BridgedGenericDataWrapperRandomVariableEditor<Data, ?> _editor;

		public BaseEditableOrderedDataWrapperWithComparatorRandomVariable(final @Nullable Data value,
				final ProbabilityStrategy probabilityStrategy, final Comparator<Data> comparator)
		{
			super(value, probabilityStrategy, comparator);
			_editor = new BridgedGenericDataWrapperRandomVariableEditor<Data, AbstractEditableOrderedGenericDataWrapperRandomVariable<Data, ProbabilityStrategy, ?>.BaseEditableOrderedDataWrapperWithComparatorRandomVariable>(this);
		}

		@Override
		public boolean setProbabilityOf(@Nullable final Data value, final int probability)
		{
			return _editor.setProbabilityOf(value, probability);
		}

		@Override
		public boolean remove(@Nullable final Data value)
		{
			return _editor.remove(value);
		}

		@Override
		public boolean addProbabilityTo(@Nullable final Data value, final int delta)
		{
			return _editor.addProbabilityTo(value, delta);
		}

		@Override
		public boolean removeProbabilityTo(@Nullable final Data value, final int delta)
		{
			return _editor.removeProbabilityTo(value, delta);
		}

		@Override
		public void clear()
		{
			_editor.clear();
		}
	}

	@Override
	public boolean containsOnly(final @Nullable Data value)
	{
		return getInner().containsOnly(value);
	}

	@Override
	public boolean setProbabilityOf(final @Nullable Data value, final int probability)
	{
		return getInner().setProbabilityOf(value, probability);
	}

	@Override
	public boolean remove(final @Nullable Data value)
	{
		return getInner().remove(value);
	}

	@Override
	public boolean addProbabilityTo(final @Nullable Data value, final int delta)
	{
		return getInner().addProbabilityTo(value, delta);
	}

	@Override
	public boolean removeProbabilityTo(final @Nullable Data value, final int delta)
	{
		return getInner().removeProbabilityTo(value, delta);
	}

	@Override
	public void clear()
	{
		getInner().clear();
	}
}