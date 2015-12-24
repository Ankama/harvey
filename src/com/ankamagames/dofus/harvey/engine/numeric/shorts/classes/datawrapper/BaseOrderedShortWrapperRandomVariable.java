/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IOrderedShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedShortWrapperRandomVariable<ProbabilityStrategy extends IProbabilityStrategy>
extends BaseShortWrapperRandomVariable<ProbabilityStrategy>
implements IOrderedShortRandomVariable
{
	public BaseOrderedShortWrapperRandomVariable(final short value,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(value, probabilityStrategy);
	}

	@Override
	public short getLowerBound()
	{
		return getValue();
	}

	@Override
	public short getUpperBound()
	{
		return getValue();
	}

	@Override
	public boolean isBetween(final short lowerBound, final boolean lowerBoundIncluded,
			final short upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?isGreaterThanOrEqualTo(lowerBound):isGreaterThan(lowerBound))&&((upperBoundIncluded)?isLowerThanOrEqualTo(upperBound):isLowerThan(upperBound));
	}

	@Override
	public boolean hasValueLowerThan(final short value)
	{
		return isLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final short value)
	{
		return isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final short value)
	{
		return isGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final short value)
	{
		return isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final short lowerBound,
			final boolean lowerBoundIncluded, final short upperBound,
			final boolean upperBoundIncluded)
	{
		return isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public int getProbabilityForLowerThan(final short value)
	{
		if(isLowerThan(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final short value)
	{
		if(isLowerThanOrEqualTo(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThan(final short value)
	{
		if(isGreaterThan(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final short value)
	{
		if(isGreaterThanOrEqualTo(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public @Nullable IOrderedShortRandomVariable getLowerThan(final short value)
	{
		if(isLowerThan(value))
			return new ReadOnlyOrderedShortWrapperRandomVariable(BaseOrderedShortWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedShortRandomVariable getLowerThanOrEqualTo(final short value)
	{
		if(isLowerThanOrEqualTo(value))
			return new ReadOnlyOrderedShortWrapperRandomVariable(BaseOrderedShortWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedShortRandomVariable getGreaterThan(final short value)
	{
		if(isGreaterThan(value))
			return new ReadOnlyOrderedShortWrapperRandomVariable(BaseOrderedShortWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedShortRandomVariable getGreaterThanOrEqualTo(final short value)
	{
		if(isGreaterThanOrEqualTo(value))
			return new ReadOnlyOrderedShortWrapperRandomVariable(BaseOrderedShortWrapperRandomVariable.this);
		return null;
	}

	@Override
	public boolean isLowerThan(final short value)
	{
		return (_value<value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final short value)
	{
		return (_value<=value);
	}

	@Override
	public boolean isGreaterThan(final short value)
	{
		return (_value>value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final short value)
	{
		return (_value>=value);
	}
}