/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IOrderedDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedDoubleWrapperRandomVariable<ProbabilityStrategy extends IProbabilityStrategy>
extends BaseDoubleWrapperRandomVariable<ProbabilityStrategy>
implements IOrderedDoubleRandomVariable
{
	public BaseOrderedDoubleWrapperRandomVariable(final double value,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(value, probabilityStrategy);
	}

	@Override
	public double getLowerBound()
	{
		return getValue();
	}

	@Override
	public double getUpperBound()
	{
		return getValue();
	}

	@Override
	public boolean isBetween(final double lowerBound, final boolean lowerBoundIncluded,
			final double upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?isGreaterThanOrEqualTo(lowerBound):isGreaterThan(lowerBound))&&((upperBoundIncluded)?isLowerThanOrEqualTo(upperBound):isLowerThan(upperBound));
	}

	@Override
	public boolean hasValueLowerThan(final double value)
	{
		return isLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final double value)
	{
		return isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final double value)
	{
		return isGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final double value)
	{
		return isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final double lowerBound,
			final boolean lowerBoundIncluded, final double upperBound,
			final boolean upperBoundIncluded)
	{
		return isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public int getProbabilityForLowerThan(final double value)
	{
		if(isLowerThan(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final double value)
	{
		if(isLowerThanOrEqualTo(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThan(final double value)
	{
		if(isGreaterThan(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final double value)
	{
		if(isGreaterThanOrEqualTo(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public @Nullable IOrderedDoubleRandomVariable getLowerThan(final double value)
	{
		if(isLowerThan(value))
			return new ReadOnlyOrderedDoubleWrapperRandomVariable(BaseOrderedDoubleWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedDoubleRandomVariable getLowerThanOrEqualTo(final double value)
	{
		if(isLowerThanOrEqualTo(value))
			return new ReadOnlyOrderedDoubleWrapperRandomVariable(BaseOrderedDoubleWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedDoubleRandomVariable getGreaterThan(final double value)
	{
		if(isGreaterThan(value))
			return new ReadOnlyOrderedDoubleWrapperRandomVariable(BaseOrderedDoubleWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedDoubleRandomVariable getGreaterThanOrEqualTo(final double value)
	{
		if(isGreaterThanOrEqualTo(value))
			return new ReadOnlyOrderedDoubleWrapperRandomVariable(BaseOrderedDoubleWrapperRandomVariable.this);
		return null;
	}

	@Override
	public boolean isLowerThan(final double value)
	{
		return (_value<value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final double value)
	{
		return (_value<=value);
	}

	@Override
	public boolean isGreaterThan(final double value)
	{
		return (_value>value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final double value)
	{
		return (_value>=value);
	}
}