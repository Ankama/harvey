/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.common.classes.AbstractRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;
import com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper.AbstractOrderedGenericDataWrapperRandomVariable.IOrderedInnerTypeDataWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.generic.interfaces.IOrderedGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractOrderedGenericDataWrapperRandomVariable
<
	Data,
	ProbabilityStrategy extends IProbabilityStrategy,
	InnerType extends IOrderedInnerTypeDataWrapperRandomVariable<Data, ProbabilityStrategy>
>
extends AbstractRandomVariable
implements IOrderedGenericRandomVariable<Data>
{
	protected interface IOrderedInnerTypeDataWrapperRandomVariable<Data, ProbabilityStrategy extends IProbabilityStrategy>
	extends IOrderedGenericRandomVariable<Data>
	{
		@Nullable Data getValue();
		ProbabilityStrategy getProbabilityStrategy();
	}

	protected abstract class BaseOrderedInnerTypeDataWrapperRandomVariable
	extends BaseGenericDataWrapperRandomVariable<Data, ProbabilityStrategy>
	implements IOrderedInnerTypeDataWrapperRandomVariable<Data, ProbabilityStrategy>
	{
		public BaseOrderedInnerTypeDataWrapperRandomVariable(final @Nullable Data value,
				final ProbabilityStrategy probabilityStrategy)
		{
			super(value, probabilityStrategy);
		}

		@Override
		public ProbabilityStrategy getProbabilityStrategy()
		{
			return _probabilityStrategy;
		}

		@Override
		public @Nullable Data getLowerBound()
		{
			return getValue();
		}

		@Override
		public @Nullable Data getUpperBound()
		{
			return getValue();
		}

		@Override
		public boolean isBetween(@Nullable final Data lowerBound, final boolean lowerBoundIncluded,
				@Nullable final Data upperBound, final boolean upperBoundIncluded)
		{
			return ((lowerBoundIncluded)?isGreaterThanOrEqualTo(lowerBound):isGreaterThan(lowerBound))&&((upperBoundIncluded)?isLowerThanOrEqualTo(upperBound):isLowerThan(upperBound));
		}

		@Override
		public boolean hasValueLowerThan(@Nullable final Data value)
		{
			return isLowerThan(value);
		}

		@Override
		public boolean hasValueLowerThanOrEqualTo(@Nullable final Data value)
		{
			return isLowerThanOrEqualTo(value);
		}

		@Override
		public boolean hasValueGreaterThan(@Nullable final Data value)
		{
			return isGreaterThan(value);
		}

		@Override
		public boolean hasValueGreaterThanOrEqualTo(@Nullable final Data value)
		{
			return isGreaterThanOrEqualTo(value);
		}

		@Override
		public boolean hasValueIn(@Nullable final Data lowerBound,
				final boolean lowerBoundIncluded, @Nullable final Data upperBound,
				final boolean upperBoundIncluded)
		{
			return isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
		}

		@Override
		public int getProbabilityForLowerThan(@Nullable final Data value)
		{
			if(isLowerThan(value))
				return _probabilityStrategy.getProbability();
			return 0;
		}

		@Override
		public int getProbabilityForLowerThanOrEqualTo(@Nullable final Data value)
		{
			if(isLowerThanOrEqualTo(value))
				return _probabilityStrategy.getProbability();
			return 0;
		}

		@Override
		public int getProbabilityForGreaterThan(@Nullable final Data value)
		{
			if(isGreaterThan(value))
				return _probabilityStrategy.getProbability();
			return 0;
		}

		@Override
		public int getProbabilityForGreaterThanOrEqualTo(@Nullable final Data value)
		{
			if(isGreaterThanOrEqualTo(value))
				return _probabilityStrategy.getProbability();
			return 0;
		}

		@Override
		public @Nullable IOrderedGenericRandomVariable<Data> getLowerThan(@Nullable final Data value)
		{
			if(isLowerThan(value))
				return new ReadOnlyOrderedGenericDataWrapperRandomVariable<Data>(AbstractOrderedGenericDataWrapperRandomVariable.this);
			return null;
		}

		@Override
		public @Nullable IOrderedGenericRandomVariable<Data> getLowerThanOrEqualTo(@Nullable final Data value)
		{
			if(isLowerThanOrEqualTo(value))
				return new ReadOnlyOrderedGenericDataWrapperRandomVariable<Data>(AbstractOrderedGenericDataWrapperRandomVariable.this);
			return null;
		}

		@Override
		public @Nullable IOrderedGenericRandomVariable<Data> getGreaterThan(@Nullable final Data value)
		{
			if(isGreaterThan(value))
				return new ReadOnlyOrderedGenericDataWrapperRandomVariable<Data>(AbstractOrderedGenericDataWrapperRandomVariable.this);
			return null;
		}

		@Override
		public @Nullable IOrderedGenericRandomVariable<Data> getGreaterThanOrEqualTo(@Nullable final Data value)
		{
			if(isGreaterThanOrEqualTo(value))
				return new ReadOnlyOrderedGenericDataWrapperRandomVariable<Data>(AbstractOrderedGenericDataWrapperRandomVariable.this);
			return null;
		}
	}

	protected class BaseOrderedComparableDataWrapperRandomVariable
	extends BaseOrderedInnerTypeDataWrapperRandomVariable
	{
		@Nullable Comparable<Data> _comparableData;

		@SuppressWarnings("unchecked")
		public BaseOrderedComparableDataWrapperRandomVariable(final @Nullable Data value,
				final ProbabilityStrategy probabilityStrategy)
		{
			super(value, probabilityStrategy);
			_comparableData = (Comparable<Data>) value;
		}

		@Override
		public boolean isLowerThan(final @Nullable Data value)
		{
			final Comparable<Data> lComparableData = _comparableData;
			if(lComparableData!=null)
			{
				return lComparableData.compareTo(value)<0;
			}
			if(value==null)
				return false;

			@SuppressWarnings("unchecked")
			final Comparable<Data> comparableValue = (Comparable<Data>)value;
			return (comparableValue.compareTo(null)>0);
		}

		@Override
		public boolean isLowerThanOrEqualTo(@Nullable final Data value)
		{
			final Comparable<Data> lComparableData = _comparableData;
			if(lComparableData!=null)
			{
				return lComparableData.compareTo(value)<=0;
			}
			if(value==null)
				return true;

			@SuppressWarnings("unchecked")
			final Comparable<Data> comparableValue = (Comparable<Data>)value;
			return (comparableValue.compareTo(null)>=0);
		}

		@Override
		public boolean isGreaterThan(@Nullable final Data value)
		{
			final Comparable<Data> lComparableData = _comparableData;
			if(lComparableData!=null)
			{
				return lComparableData.compareTo(value)>0;
			}
			if(value==null)
				return false;

			@SuppressWarnings("unchecked")
			final Comparable<Data> comparableValue = (Comparable<Data>)value;
			return (comparableValue.compareTo(null)<0);
		}

		@Override
		public boolean isGreaterThanOrEqualTo(@Nullable final Data value)
		{
			final Comparable<Data> lComparableData = _comparableData;
			if(lComparableData!=null)
			{
				return lComparableData.compareTo(value)>=0;
			}
			if(value==null)
				return true;

			@SuppressWarnings("unchecked")
			final Comparable<Data> comparableValue = (Comparable<Data>)value;
			return (comparableValue.compareTo(null)<=0);
		}

		@Override
		public @Nullable Comparator<Data> getComparator()
		{
			return null;
		}
	}


	protected class BaseOrderedDataWrapperWithComparatorRandomVariable
	extends BaseOrderedInnerTypeDataWrapperRandomVariable
	{
		Comparator<Data> _comparator;

		public BaseOrderedDataWrapperWithComparatorRandomVariable(final @Nullable Data value,
				final ProbabilityStrategy probabilityStrategy, final Comparator<Data> comparator)
		{
			super(value, probabilityStrategy);
			_comparator = comparator;
		}

		@Override
		public boolean isLowerThan(final @Nullable Data value)
		{
			return _comparator.compare(_value, value)<0;
		}

		@Override
		public boolean isLowerThanOrEqualTo(@Nullable final Data value)
		{
			return _comparator.compare(_value, value)<=0;
		}

		@Override
		public boolean isGreaterThan(@Nullable final Data value)
		{
			return _comparator.compare(_value, value)>0;
		}

		@Override
		public boolean isGreaterThanOrEqualTo(@Nullable final Data value)
		{
			return _comparator.compare(_value, value)>=0;
		}

		@Override
		public Comparator<Data> getComparator()
		{
			return _comparator;
		}
	}

	protected abstract InnerType getInner();

	@Override
	public int getProbabilityOf(@Nullable final Data value)
	{
		return getInner().getProbabilityOf(value);
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		return getInner().contains(value);
	}

	@Override
	public boolean isUnknown()
	{
		return getInner().isUnknown();
	}

	@Override
	public boolean isLowerThan(@Nullable final Data value)
	{
		return getInner().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(@Nullable final Data value)
	{
		return getInner().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(@Nullable final Data value)
	{
		return getInner().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return getInner().isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(@Nullable final Data lowerBound, final boolean lowerBoundIncluded, @Nullable final Data upperBound, final boolean upperBoundIncluded)
	{
		return getInner().isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public boolean hasValueLowerThan(@Nullable final Data value)
	{
		return getInner().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(@Nullable final Data value)
	{
		return getInner().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(@Nullable final Data value)
	{
		return getInner().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return getInner().hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(@Nullable final Data lowerBound, final boolean lowerBoundIncluded, @Nullable final Data upperBound, final boolean upperBoundIncluded)
	{
		return getInner().hasValueIn(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public int getProbabilityForLowerThan(@Nullable final Data value)
	{
		return getInner().getProbabilityForLowerThan(value);
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(@Nullable final Data value)
	{
		return getInner().getProbabilityForLowerThanOrEqualTo(value);
	}

	@Override
	public int getProbabilityForGreaterThan(@Nullable final Data value)
	{
		return getInner().getProbabilityForGreaterThan(value);
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return getInner().getProbabilityForGreaterThanOrEqualTo(value);
	}

	@Override
	public @Nullable IOrderedGenericRandomVariable<Data> getLowerThan(@Nullable final Data value)
	{
		return getInner().getLowerThan(value);
	}

	@Override
	public @Nullable IOrderedGenericRandomVariable<Data> getLowerThanOrEqualTo(@Nullable final Data value)
	{
		return getInner().getLowerThanOrEqualTo(value);
	}

	@Override
	public @Nullable IOrderedGenericRandomVariable<Data> getGreaterThan(@Nullable final Data value)
	{
		return getInner().getGreaterThan(value);
	}

	@Override
	public @Nullable IOrderedGenericRandomVariable<Data> getGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return getInner().getGreaterThanOrEqualTo(value);
	}

	@Override
	public @Nullable Comparator<? super Data> getComparator()
	{
		return getInner().getComparator();
	}

	@Override
	public @Nullable Data getLowerBound()
	{
		return getInner().getLowerBound();
	}

	@Override
	public @Nullable Data getUpperBound()
	{
		return getInner().getUpperBound();
	}

	@Override
	@Nullable
	public Data getOnlyValue() throws MultipleValuesException
	{
		return getInner().getOnlyValue();
	}

	@Override
	public boolean hasOnlyOneValue()
	{
		return getInner().hasOnlyOneValue();
	}

	@Override
	public boolean containsOnly(@Nullable final Data value)
	{
		return getInner().containsOnly(value);
	}

	@Override
	public int getKnownProbability()
	{
		return getInner().getKnownProbability();
	}

	@Override
	protected String toStringValues()
	{
		return getInner().toString();
	}
}