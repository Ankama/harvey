/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IOrderedIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedIntWrapperRandomVariable<ProbabilityStrategy extends IProbabilityStrategy>
extends BaseIntWrapperRandomVariable<ProbabilityStrategy>
implements IOrderedIntRandomVariable
{
	public BaseOrderedIntWrapperRandomVariable(final int value,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(value, probabilityStrategy);
	}

	@Override
	public int getLowerBound()
	{
		return getValue();
	}

	@Override
	public int getUpperBound()
	{
		return getValue();
	}

	@Override
	public boolean isBetween(final int lowerBound, final boolean lowerBoundIncluded,
			final int upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?isGreaterThanOrEqualTo(lowerBound):isGreaterThan(lowerBound))&&((upperBoundIncluded)?isLowerThanOrEqualTo(upperBound):isLowerThan(upperBound));
	}

	@Override
	public boolean hasValueLowerThan(final int value)
	{
		return isLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final int value)
	{
		return isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final int value)
	{
		return isGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final int value)
	{
		return isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final int lowerBound,
			final boolean lowerBoundIncluded, final int upperBound,
			final boolean upperBoundIncluded)
	{
		return isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public int getProbabilityForLowerThan(final int value)
	{
		if(isLowerThan(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final int value)
	{
		if(isLowerThanOrEqualTo(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThan(final int value)
	{
		if(isGreaterThan(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final int value)
	{
		if(isGreaterThanOrEqualTo(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public @Nullable IOrderedIntRandomVariable getLowerThan(final int value)
	{
		if(isLowerThan(value))
			return new ReadOnlyOrderedIntWrapperRandomVariable(BaseOrderedIntWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedIntRandomVariable getLowerThanOrEqualTo(final int value)
	{
		if(isLowerThanOrEqualTo(value))
			return new ReadOnlyOrderedIntWrapperRandomVariable(BaseOrderedIntWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedIntRandomVariable getGreaterThan(final int value)
	{
		if(isGreaterThan(value))
			return new ReadOnlyOrderedIntWrapperRandomVariable(BaseOrderedIntWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedIntRandomVariable getGreaterThanOrEqualTo(final int value)
	{
		if(isGreaterThanOrEqualTo(value))
			return new ReadOnlyOrderedIntWrapperRandomVariable(BaseOrderedIntWrapperRandomVariable.this);
		return null;
	}

	@Override
	public boolean isLowerThan(final int value)
	{
		return (_value<value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final int value)
	{
		return (_value<=value);
	}

	@Override
	public boolean isGreaterThan(final int value)
	{
		return (_value>value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final int value)
	{
		return (_value>=value);
	}
}