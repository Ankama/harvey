/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IOrderedShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedShortRandomVariableWrapper
<
	ChildType extends IOrderedShortRandomVariable,
	ParentType extends AbstractCompositeShortRandomVariable<?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends BaseShortRandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>
implements IOrderedShortRandomVariable
{
	public BaseOrderedShortRandomVariableWrapper(final ChildType element,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
	}

	@Override
	public boolean isLowerThan(final short value)
	{
		return getElement().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final short value)
	{
		return getElement().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(final short value)
	{
		return getElement().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final short value)
	{
		return getElement().isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(final short lowerBound, final boolean lowerBoundIncluded,
			final short upperBound, final boolean upperBoundIncluded)
	{
		return getElement().isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public boolean hasValueLowerThan(final short value)
	{
		return getElement().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(final short value)
	{
		return getElement().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(final short value)
	{
		return getElement().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(final short value)
	{
		return getElement().hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(final short lowerBound, final boolean lowerBoundIncluded,
			final short upperBound, final boolean upperBoundIncluded)
	{
		return getElement().hasValueIn(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public int getProbabilityForLowerThan(final short value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForLowerThan(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final short value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForLowerThanOrEqualTo(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThan(final short value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForGreaterThan(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final short value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForGreaterThanOrEqualTo(value), proba);
		return 0;
	}

	@Override
	public @Nullable ReadOnlyOrderedShortRandomVariableWrapper getLowerThan(final short value)
	{
		final IOrderedShortRandomVariable element = getElement().getLowerThan(value);
		if(element!=null)
			return new ReadOnlyOrderedShortRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedShortRandomVariableWrapper getLowerThanOrEqualTo(final short value)
	{
		final IOrderedShortRandomVariable element = getElement().getLowerThanOrEqualTo(value);
		if(element!=null)
			return new ReadOnlyOrderedShortRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedShortRandomVariableWrapper getGreaterThan(final short value)
	{
		final IOrderedShortRandomVariable element = getElement().getGreaterThan(value);
		if(element!=null)
			return new ReadOnlyOrderedShortRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedShortRandomVariableWrapper getGreaterThanOrEqualTo(final short value)
	{
		final IOrderedShortRandomVariable element = getElement().getGreaterThanOrEqualTo(value);
		if(element!=null)
			return new ReadOnlyOrderedShortRandomVariableWrapper(this, element);
		return null;
	}

	@Override
	public short getLowerBound()
	{
		return getElement().getLowerBound();
	}

	@Override
	public short getUpperBound()
	{
		return getElement().getUpperBound();
	}
}