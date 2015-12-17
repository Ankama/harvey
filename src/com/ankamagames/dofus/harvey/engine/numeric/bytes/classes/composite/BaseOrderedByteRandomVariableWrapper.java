/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IOrderedByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedByteRandomVariableWrapper
<
	ChildType extends IOrderedByteRandomVariable,
	ParentType extends AbstractCompositeByteRandomVariable<?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends BaseByteRandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>
implements IOrderedByteRandomVariable
{
	public BaseOrderedByteRandomVariableWrapper(final ChildType element,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
	}

	@Override
	public boolean isLowerThan(final byte value)
	{
		return getElement().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final byte value)
	{
		return getElement().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final byte value)
	{
		return getElement().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final byte value)
	{
		return getElement().isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(final byte lowerBound, final boolean lowerBoundIncluded,
			final byte upperBound, final boolean upperBoundIncluded)
	{
		return getElement().isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public boolean hasValueLowerThan(final byte value)
	{
		return getElement().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final byte value)
	{
		return getElement().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final byte value)
	{
		return getElement().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final byte value)
	{
		return getElement().hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final byte lowerBound, final boolean lowerBoundIncluded,
			final byte upperBound, final boolean upperBoundIncluded)
	{
		return getElement().hasValueIn(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public int getProbabilityForLowerThan(final byte value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForLowerThan(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final byte value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForLowerThanOrEqualTo(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThan(final byte value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForGreaterThan(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final byte value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForGreaterThanOrEqualTo(value), proba);
		return 0;
	}

	@Override
	public @Nullable ReadOnlyOrderedByteRandomVariableWrapper getLowerThan(final byte value)
	{
		final IOrderedByteRandomVariable element = getElement().getLowerThan(value);
		if(element!=null)
			return new ReadOnlyOrderedByteRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedByteRandomVariableWrapper getLowerThanOrEqualTo(final byte value)
	{
		final IOrderedByteRandomVariable element = getElement().getLowerThanOrEqualTo(value);
		if(element!=null)
			return new ReadOnlyOrderedByteRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedByteRandomVariableWrapper getGreaterThan(final byte value)
	{
		final IOrderedByteRandomVariable element = getElement().getGreaterThan(value);
		if(element!=null)
			return new ReadOnlyOrderedByteRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedByteRandomVariableWrapper getGreaterThanOrEqualTo(final byte value)
	{
		final IOrderedByteRandomVariable element = getElement().getGreaterThanOrEqualTo(value);
		if(element!=null)
			return new ReadOnlyOrderedByteRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public byte getLowerBound()
	{
		return getElement().getLowerBound();
	}

	@Override
	public byte getUpperBound()
	{
		return getElement().getUpperBound();
	}
}