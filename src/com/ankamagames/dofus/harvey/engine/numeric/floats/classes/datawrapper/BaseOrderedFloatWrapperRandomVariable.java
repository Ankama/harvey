/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IOrderedFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedFloatWrapperRandomVariable<ProbabilityStrategy extends IProbabilityStrategy>
extends BaseFloatWrapperRandomVariable<ProbabilityStrategy>
implements IOrderedFloatRandomVariable
{
	public BaseOrderedFloatWrapperRandomVariable(final float value,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(value, probabilityStrategy);
	}

	@Override
	public float getLowerBound()
	{
		return getValue();
	}

	@Override
	public float getUpperBound()
	{
		return getValue();
	}

	@Override
	public boolean isBetween(final float lowerBound, final boolean lowerBoundIncluded,
			final float upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?isGreaterThanOrEqualTo(lowerBound):isGreaterThan(lowerBound))&&((upperBoundIncluded)?isLowerThanOrEqualTo(upperBound):isLowerThan(upperBound));
	}

	@Override
	public boolean hasValueLowerThan(final float value)
	{
		return isLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final float value)
	{
		return isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final float value)
	{
		return isGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final float value)
	{
		return isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final float lowerBound,
			final boolean lowerBoundIncluded, final float upperBound,
			final boolean upperBoundIncluded)
	{
		return isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public int getProbabilityForLowerThan(final float value)
	{
		if(isLowerThan(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final float value)
	{
		if(isLowerThanOrEqualTo(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThan(final float value)
	{
		if(isGreaterThan(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final float value)
	{
		if(isGreaterThanOrEqualTo(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public @Nullable IOrderedFloatRandomVariable getLowerThan(final float value)
	{
		if(isLowerThan(value))
			return new ReadOnlyOrderedFloatWrapperRandomVariable(BaseOrderedFloatWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedFloatRandomVariable getLowerThanOrEqualTo(final float value)
	{
		if(isLowerThanOrEqualTo(value))
			return new ReadOnlyOrderedFloatWrapperRandomVariable(BaseOrderedFloatWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedFloatRandomVariable getGreaterThan(final float value)
	{
		if(isGreaterThan(value))
			return new ReadOnlyOrderedFloatWrapperRandomVariable(BaseOrderedFloatWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedFloatRandomVariable getGreaterThanOrEqualTo(final float value)
	{
		if(isGreaterThanOrEqualTo(value))
			return new ReadOnlyOrderedFloatWrapperRandomVariable(BaseOrderedFloatWrapperRandomVariable.this);
		return null;
	}

	@Override
	public boolean isLowerThan(final float value)
	{
		return (_value<value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final float value)
	{
		return (_value<=value);
	}

	@Override
	public boolean isGreaterThan(final float value)
	{
		return (_value>value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final float value)
	{
		return (_value>=value);
	}
}