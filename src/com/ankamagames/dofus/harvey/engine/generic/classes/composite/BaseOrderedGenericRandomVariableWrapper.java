/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.generic.interfaces.IOrderedGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedGenericRandomVariableWrapper
<
	Data,
	ChildType extends IOrderedGenericRandomVariable<Data>,
	ParentType extends AbstractCompositeGenericRandomVariable<Data, ?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends BaseGenericRandomVariableWrapper<Data, ChildType, ParentType, ProbabilityStrategy>
implements IOrderedGenericRandomVariable<Data>
{
	public BaseOrderedGenericRandomVariableWrapper(final ChildType element,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
	}

	@Override
	public boolean isLowerThan(@Nullable final Data value)
	{
		return getElement().isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(@Nullable final Data value)
	{
		return getElement().isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(@Nullable final Data value)
	{
		return getElement().isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return getElement().isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(@Nullable final Data lowerBound, final boolean lowerBoundIncluded,
			@Nullable final Data upperBound, final boolean upperBoundIncluded)
	{
		return getElement().isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public boolean hasValueLowerThan(@Nullable final Data value)
	{
		return getElement().hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(@Nullable final Data value)
	{
		return getElement().hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(@Nullable final Data value)
	{
		return getElement().hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return getElement().hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(@Nullable final Data lowerBound, final boolean lowerBoundIncluded,
			@Nullable final Data upperBound, final boolean upperBoundIncluded)
	{
		return getElement().hasValueIn(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public int getProbabilityForLowerThan(@Nullable final Data value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForLowerThan(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(@Nullable final Data value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForLowerThanOrEqualTo(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThan(@Nullable final Data value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForGreaterThan(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(@Nullable final Data value)
	{
		final int proba = getProbabilityStrategy().getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(getElement().getProbabilityForGreaterThanOrEqualTo(value), proba);
		return 0;
	}

	@Override
	public @Nullable ReadOnlyOrderedGenericRandomVariableWrapper<Data> getLowerThan(@Nullable final Data value)
	{
		final IOrderedGenericRandomVariable<Data> element = getElement().getLowerThan(value);
		if(element!=null)
			return new ReadOnlyOrderedGenericRandomVariableWrapper<Data>(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedGenericRandomVariableWrapper<Data> getLowerThanOrEqualTo(@Nullable final Data value)
	{
		final IOrderedGenericRandomVariable<Data> element = getElement().getLowerThanOrEqualTo(value);
		if(element!=null)
			return new ReadOnlyOrderedGenericRandomVariableWrapper<Data>(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedGenericRandomVariableWrapper<Data> getGreaterThan(@Nullable final Data value)
	{
		final IOrderedGenericRandomVariable<Data> element = getElement().getGreaterThan(value);
		if(element!=null)
			return new ReadOnlyOrderedGenericRandomVariableWrapper<Data>(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedGenericRandomVariableWrapper<Data> getGreaterThanOrEqualTo(@Nullable final Data value)
	{
		final IOrderedGenericRandomVariable<Data> element = getElement().getGreaterThanOrEqualTo(value);
		if(element!=null)
			return new ReadOnlyOrderedGenericRandomVariableWrapper<Data>(this, element);
		return null;
	}

	@Override
	public @Nullable Comparator<? super Data> getComparator()
	{
		return getElement().getComparator();
	}

	@Override
	@Nullable
	public Data getLowerBound()
	{
		return getElement().getLowerBound();
	}

	@Override
	@Nullable
	public Data getUpperBound()
	{
		return getElement().getUpperBound();
	}
}