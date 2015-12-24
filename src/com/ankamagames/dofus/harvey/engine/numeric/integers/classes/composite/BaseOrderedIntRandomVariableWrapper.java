/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IOrderedIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedIntRandomVariableWrapper
<
	ChildType extends IOrderedIntRandomVariable,
	ParentType extends AbstractCompositeIntRandomVariable<?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends BaseIntRandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>
implements IOrderedIntRandomVariable
{
	public BaseOrderedIntRandomVariableWrapper(final ChildType element,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
	}

	@Override
	public boolean isLowerThan(final int value)
	{
		return getElement().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final int value)
	{
		return getElement().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final int value)
	{
		return getElement().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final int value)
	{
		return getElement().isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(final int lowerBound, final boolean lowerBoundIncluded,
			final int upperBound, final boolean upperBoundIncluded)
	{
		return getElement().isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public boolean hasValueLowerThan(final int value)
	{
		return getElement().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final int value)
	{
		return getElement().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final int value)
	{
		return getElement().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final int value)
	{
		return getElement().hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final int lowerBound, final boolean lowerBoundIncluded,
			final int upperBound, final boolean upperBoundIncluded)
	{
		return getElement().hasValueIn(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public int getProbabilityForLowerThan(final int value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForLowerThan(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final int value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForLowerThanOrEqualTo(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThan(final int value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForGreaterThan(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final int value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForGreaterThanOrEqualTo(value), proba);
		return 0;
	}

	@Override
	public @Nullable ReadOnlyOrderedIntRandomVariableWrapper getLowerThan(final int value)
	{
		final IOrderedIntRandomVariable element = getElement().getLowerThan(value);
		if(element!=null)
			return new ReadOnlyOrderedIntRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedIntRandomVariableWrapper getLowerThanOrEqualTo(final int value)
	{
		final IOrderedIntRandomVariable element = getElement().getLowerThanOrEqualTo(value);
		if(element!=null)
			return new ReadOnlyOrderedIntRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedIntRandomVariableWrapper getGreaterThan(final int value)
	{
		final IOrderedIntRandomVariable element = getElement().getGreaterThan(value);
		if(element!=null)
			return new ReadOnlyOrderedIntRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedIntRandomVariableWrapper getGreaterThanOrEqualTo(final int value)
	{
		final IOrderedIntRandomVariable element = getElement().getGreaterThanOrEqualTo(value);
		if(element!=null)
			return new ReadOnlyOrderedIntRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public int getLowerBound()
	{
		return getElement().getLowerBound();
	}

	@Override
	public int getUpperBound()
	{
		return getElement().getUpperBound();
	}
}