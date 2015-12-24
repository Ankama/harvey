/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IOrderedDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedDoubleRandomVariableWrapper
<
	ChildType extends IOrderedDoubleRandomVariable,
	ParentType extends AbstractCompositeDoubleRandomVariable<?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends BaseDoubleRandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>
implements IOrderedDoubleRandomVariable
{
	public BaseOrderedDoubleRandomVariableWrapper(final ChildType element,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
	}

	@Override
	public boolean isLowerThan(final double value)
	{
		return getElement().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final double value)
	{
		return getElement().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final double value)
	{
		return getElement().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final double value)
	{
		return getElement().isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(final double lowerBound, final boolean lowerBoundIncluded,
			final double upperBound, final boolean upperBoundIncluded)
	{
		return getElement().isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public boolean hasValueLowerThan(final double value)
	{
		return getElement().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final double value)
	{
		return getElement().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final double value)
	{
		return getElement().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final double value)
	{
		return getElement().hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final double lowerBound, final boolean lowerBoundIncluded,
			final double upperBound, final boolean upperBoundIncluded)
	{
		return getElement().hasValueIn(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public int getProbabilityForLowerThan(final double value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForLowerThan(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final double value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForLowerThanOrEqualTo(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThan(final double value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForGreaterThan(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final double value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForGreaterThanOrEqualTo(value), proba);
		return 0;
	}

	@Override
	public @Nullable ReadOnlyOrderedDoubleRandomVariableWrapper getLowerThan(final double value)
	{
		final IOrderedDoubleRandomVariable element = getElement().getLowerThan(value);
		if(element!=null)
			return new ReadOnlyOrderedDoubleRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedDoubleRandomVariableWrapper getLowerThanOrEqualTo(final double value)
	{
		final IOrderedDoubleRandomVariable element = getElement().getLowerThanOrEqualTo(value);
		if(element!=null)
			return new ReadOnlyOrderedDoubleRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedDoubleRandomVariableWrapper getGreaterThan(final double value)
	{
		final IOrderedDoubleRandomVariable element = getElement().getGreaterThan(value);
		if(element!=null)
			return new ReadOnlyOrderedDoubleRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedDoubleRandomVariableWrapper getGreaterThanOrEqualTo(final double value)
	{
		final IOrderedDoubleRandomVariable element = getElement().getGreaterThanOrEqualTo(value);
		if(element!=null)
			return new ReadOnlyOrderedDoubleRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public double getLowerBound()
	{
		return getElement().getLowerBound();
	}

	@Override
	public double getUpperBound()
	{
		return getElement().getUpperBound();
	}
}