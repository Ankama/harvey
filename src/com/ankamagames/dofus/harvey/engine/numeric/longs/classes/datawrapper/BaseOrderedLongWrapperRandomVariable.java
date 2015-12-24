/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.IOrderedLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedLongWrapperRandomVariable<ProbabilityStrategy extends IProbabilityStrategy>
extends BaseLongWrapperRandomVariable<ProbabilityStrategy>
implements IOrderedLongRandomVariable
{
	public BaseOrderedLongWrapperRandomVariable(final long value,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(value, probabilityStrategy);
	}

	@Override
	public long getLowerBound()
	{
		return getValue();
	}

	@Override
	public long getUpperBound()
	{
		return getValue();
	}

	@Override
	public boolean isBetween(final long lowerBound, final boolean lowerBoundIncluded,
			final long upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?isGreaterThanOrEqualTo(lowerBound):isGreaterThan(lowerBound))&&((upperBoundIncluded)?isLowerThanOrEqualTo(upperBound):isLowerThan(upperBound));
	}

	@Override
	public boolean hasValueLowerThan(final long value)
	{
		return isLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final long value)
	{
		return isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final long value)
	{
		return isGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final long value)
	{
		return isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final long lowerBound,
			final boolean lowerBoundIncluded, final long upperBound,
			final boolean upperBoundIncluded)
	{
		return isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public int getProbabilityForLowerThan(final long value)
	{
		if(isLowerThan(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final long value)
	{
		if(isLowerThanOrEqualTo(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThan(final long value)
	{
		if(isGreaterThan(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final long value)
	{
		if(isGreaterThanOrEqualTo(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public @Nullable IOrderedLongRandomVariable getLowerThan(final long value)
	{
		if(isLowerThan(value))
			return new ReadOnlyOrderedLongWrapperRandomVariable(BaseOrderedLongWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedLongRandomVariable getLowerThanOrEqualTo(final long value)
	{
		if(isLowerThanOrEqualTo(value))
			return new ReadOnlyOrderedLongWrapperRandomVariable(BaseOrderedLongWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedLongRandomVariable getGreaterThan(final long value)
	{
		if(isGreaterThan(value))
			return new ReadOnlyOrderedLongWrapperRandomVariable(BaseOrderedLongWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedLongRandomVariable getGreaterThanOrEqualTo(final long value)
	{
		if(isGreaterThanOrEqualTo(value))
			return new ReadOnlyOrderedLongWrapperRandomVariable(BaseOrderedLongWrapperRandomVariable.this);
		return null;
	}

	@Override
	public boolean isLowerThan(final long value)
	{
		return (_value<value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final long value)
	{
		return (_value<=value);
	}

	@Override
	public boolean isGreaterThan(final long value)
	{
		return (_value>value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final long value)
	{
		return (_value>=value);
	}
}