/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseOrderedRandomVariableWrapper
<
	Data,
	ChildType extends IOrderedRandomVariable<Data>,
	ParentType extends AbstractCompositeRandomVariable<Data, ?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends BaseRandomVariableWrapper<Data, ChildType, ParentType, ProbabilityStrategy>
implements IOrderedRandomVariable<Data>
{
	public BaseOrderedRandomVariableWrapper(final ChildType element,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
	}

	@Override
	public boolean isLowerThan(@Nullable final Data value)
	{
		return _element.isLowerThan(value);
	}

	@Override
	public boolean isLowerThanOrEqualTo(@Nullable final Data value)
	{
		return _element.isLowerThanOrEqualTo(value);
	}

	@Override
	public boolean isGreaterThan(@Nullable final Data value)
	{
		return _element.isGreaterThan(value);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return _element.isGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean isBetween(@Nullable final Data lowerBound, final boolean lowerBoundIncluded,
			@Nullable final Data upperBound, final boolean upperBoundIncluded)
	{
		return _element.isBetween(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public boolean hasValueLowerThan(@Nullable final Data value)
	{
		return _element.hasValueLowerThan(value);
	}

	@Override
	public boolean hasValueLowerThanOrEqualTo(@Nullable final Data value)
	{
		return _element.hasValueLowerThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueGreaterThan(@Nullable final Data value)
	{
		return _element.hasValueGreaterThan(value);
	}

	@Override
	public boolean hasValueGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return _element.hasValueGreaterThanOrEqualTo(value);
	}

	@Override
	public boolean hasValueIn(@Nullable final Data lowerBound, final boolean lowerBoundIncluded,
			@Nullable final Data upperBound, final boolean upperBoundIncluded)
	{
		return _element.hasValueIn(lowerBound, lowerBoundIncluded, upperBound, upperBoundIncluded);
	}

	@Override
	public int getProbabilityForLowerThan(@Nullable final Data value)
	{
		final int proba = _probabilityStrategy.getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(_element.getProbabilityForLowerThan(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(@Nullable final Data value)
	{
		final int proba = _probabilityStrategy.getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(_element.getProbabilityForLowerThanOrEqualTo(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThan(@Nullable final Data value)
	{
		final int proba = _probabilityStrategy.getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(_element.getProbabilityForGreaterThan(value), proba);
		return 0;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(@Nullable final Data value)
	{
		final int proba = _probabilityStrategy.getProbability();
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(_element.getProbabilityForGreaterThanOrEqualTo(value), proba);
		return 0;
	}

	@Override
	public @Nullable ReadOnlyOrderedRandomVariableWrapper<Data> getLowerThan(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> element = _element.getLowerThan(value);
		if(element!=null)
			return new ReadOnlyOrderedRandomVariableWrapper<Data>(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedRandomVariableWrapper<Data> getLowerThanOrEqualTo(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> element = _element.getLowerThanOrEqualTo(value);
		if(element!=null)
			return new ReadOnlyOrderedRandomVariableWrapper<Data>(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedRandomVariableWrapper<Data> getGreaterThan(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> element = _element.getGreaterThan(value);
		if(element!=null)
			return new ReadOnlyOrderedRandomVariableWrapper<Data>(this, element);
		return null;
	}

	@Override
	public @Nullable ReadOnlyOrderedRandomVariableWrapper<Data> getGreaterThanOrEqualTo(@Nullable final Data value)
	{
		final IOrderedRandomVariable<Data> element = _element.getGreaterThanOrEqualTo(value);
		if(element!=null)
			return new ReadOnlyOrderedRandomVariableWrapper<Data>(this, element);
		return null;
	}

	@Override
	public @Nullable Comparator<? super Data> getComparator()
	{
		return _element.getComparator();
	}

	@Override
	@Nullable
	public Data getLowerBound()
	{
		return _element.getLowerBound();
	}

	@Override
	@Nullable
	public Data getUpperBound()
	{
		return _element.getUpperBound();
	}
}