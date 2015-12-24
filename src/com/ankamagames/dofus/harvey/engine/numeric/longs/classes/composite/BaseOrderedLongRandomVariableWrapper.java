/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.IOrderedLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedLongRandomVariableWrapper
<
	ChildType extends IOrderedLongRandomVariable,
	ParentType extends AbstractCompositeLongRandomVariable<?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends BaseLongRandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>
implements IOrderedLongRandomVariable
{
	public BaseOrderedLongRandomVariableWrapper(final ChildType element,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
	}

	@Override
	public boolean isLowerThan(final long value)
	{
		return getElement().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final long value)
	{
		return getElement().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final long value)
	{
		return getElement().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final long value)
	{
		return getElement().isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(final long lowerBound, final boolean lowerBoundIncluded,
			final long upperBound, final boolean upperBoundIncluded)
	{
		return getElement().isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public boolean hasValueLowerThan(final long value)
	{
		return getElement().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final long value)
	{
		return getElement().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final long value)
	{
		return getElement().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final long value)
	{
		return getElement().hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final long lowerBound, final boolean lowerBoundIncluded,
			final long upperBound, final boolean upperBoundIncluded)
	{
		return getElement().hasValueIn(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public int getProbabilityForLowerThan(final long value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForLowerThan(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final long value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForLowerThanOrEqualTo(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThan(final long value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForGreaterThan(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final long value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForGreaterThanOrEqualTo(value), proba);
		return 0;
	}

	@Override
	public @Nullable ReadOnlyOrderedLongRandomVariableWrapper getLowerThan(final long value)
	{
		final IOrderedLongRandomVariable element = getElement().getLowerThan(value);
		if(element!=null)
			return new ReadOnlyOrderedLongRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedLongRandomVariableWrapper getLowerThanOrEqualTo(final long value)
	{
		final IOrderedLongRandomVariable element = getElement().getLowerThanOrEqualTo(value);
		if(element!=null)
			return new ReadOnlyOrderedLongRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedLongRandomVariableWrapper getGreaterThan(final long value)
	{
		final IOrderedLongRandomVariable element = getElement().getGreaterThan(value);
		if(element!=null)
			return new ReadOnlyOrderedLongRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedLongRandomVariableWrapper getGreaterThanOrEqualTo(final long value)
	{
		final IOrderedLongRandomVariable element = getElement().getGreaterThanOrEqualTo(value);
		if(element!=null)
			return new ReadOnlyOrderedLongRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public long getLowerBound()
	{
		return getElement().getLowerBound();
	}

	@Override
	public long getUpperBound()
	{
		return getElement().getUpperBound();
	}
}