/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IOrderedByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedByteWrapperRandomVariable<ProbabilityStrategy extends IProbabilityStrategy>
extends BaseByteWrapperRandomVariable<ProbabilityStrategy>
implements IOrderedByteRandomVariable
{
	public BaseOrderedByteWrapperRandomVariable(final byte value,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(value, probabilityStrategy);
	}

	@Override
	public byte getLowerBound()
	{
		return getValue();
	}

	@Override
	public byte getUpperBound()
	{
		return getValue();
	}

	@Override
	public boolean isBetween(final byte lowerBound, final boolean lowerBoundIncluded,
			final byte upperBound, final boolean upperBoundIncluded)
	{
		return ((lowerBoundIncluded)?isGreaterThanOrEqualTo(lowerBound):isGreaterThan(lowerBound))&&((upperBoundIncluded)?isLowerThanOrEqualTo(upperBound):isLowerThan(upperBound));
	}

	@Override
	public boolean hasValueLowerThan(final byte value)
	{
		return isLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final byte value)
	{
		return isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final byte value)
	{
		return isGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final byte value)
	{
		return isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final byte lowerBound,
			final boolean lowerBoundIncluded, final byte upperBound,
			final boolean upperBoundIncluded)
	{
		return isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public int getProbabilityForLowerThan(final byte value)
	{
		if(isLowerThan(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final byte value)
	{
		if(isLowerThanOrEqualTo(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThan(final byte value)
	{
		if(isGreaterThan(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final byte value)
	{
		if(isGreaterThanOrEqualTo(value))
			return _probabilityStrategy.getProbability();
		return 0;
	}

	@Override
	public @Nullable IOrderedByteRandomVariable getLowerThan(final byte value)
	{
		if(isLowerThan(value))
			return new ReadOnlyOrderedByteWrapperRandomVariable(BaseOrderedByteWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedByteRandomVariable getLowerThanOrEqualTo(final byte value)
	{
		if(isLowerThanOrEqualTo(value))
			return new ReadOnlyOrderedByteWrapperRandomVariable(BaseOrderedByteWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedByteRandomVariable getGreaterThan(final byte value)
	{
		if(isGreaterThan(value))
			return new ReadOnlyOrderedByteWrapperRandomVariable(BaseOrderedByteWrapperRandomVariable.this);
		return null;
	}

	@Override
	public @Nullable IOrderedByteRandomVariable getGreaterThanOrEqualTo(final byte value)
	{
		if(isGreaterThanOrEqualTo(value))
			return new ReadOnlyOrderedByteWrapperRandomVariable(BaseOrderedByteWrapperRandomVariable.this);
		return null;
	}

	@Override
	public boolean isLowerThan(final byte value)
	{
		return (_value<value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final byte value)
	{
		return (_value<=value);
	}

	@Override
	public boolean isGreaterThan(final byte value)
	{
		return (_value>value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final byte value)
	{
		return (_value>=value);
	}
}