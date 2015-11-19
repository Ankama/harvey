/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours.composite;

import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OrderedReadOnlyCompositeBehaviour
<
	Data,
	ParentType extends ICompositeRandomVariable<Data, ?, ?>,
	ChildType extends IOrderedRandomVariable<Data, ?>,
	Behaviour extends IOrderedCompositeBehaviour<Data, ParentType, ChildType>,
	Bridged extends IOrderedRandomVariable<Data, ?>
>
extends ReadOnlyCompositeBehaviour<Data, ChildType, Behaviour, Bridged>
implements IOrderedCompositeBehaviour<Data, ParentType, ChildType>
{
	public OrderedReadOnlyCompositeBehaviour(
			final Behaviour behaviour, final Bridged bridged)
	{
		super(behaviour, bridged);
	}

	public OrderedReadOnlyCompositeBehaviour(
			final Behaviour behaviour)
	{
		super(behaviour);
	}

	@Override
	public int compareTo(@Nullable final Data o)
	{
		return _behaviour.compareTo(o);
	}

	@Override
	public int getProbabilityForLowerThan(final Data value,
			@Nullable final IRandomVariable<Data, ?> context)
	{
		return _behaviour.getProbabilityForLowerThan(value, context);
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final Data value,
			@Nullable final IRandomVariable<Data, ?> context)
	{
		return _behaviour.getProbabilityForLowerThanOrEqualTo(value, context);
	}

	@Override
	public int getProbabilityForGreaterThan(final Data value,
			@Nullable final IRandomVariable<Data, ?> context)
	{
		return _behaviour.getProbabilityForGreaterThan(value, context);
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final Data value,
			@Nullable final IRandomVariable<Data, ?> context)
	{
		return _behaviour.getProbabilityForGreaterThanOrEqualTo(value, context);
	}

	@Override
	public IOrderedRandomVariable<Data, ParentType> getLowerThan(final Data value)
	{
		return _behaviour.getLowerThan(value);
	}

	@Override
	public IOrderedRandomVariable<Data, ParentType> getLowerThanOrEqualTo(
			final Data value)
	{
		return _behaviour.getLowerThanOrEqualTo(value);
	}

	@Override
	public IOrderedRandomVariable<Data, ParentType> getGreaterThan(final Data value)
	{
		return _behaviour.getGreaterThan(value);
	}

	@Override
	public IOrderedRandomVariable<Data, ParentType> getGreaterThanOrEqualTo(
			final Data value)
	{
		return _behaviour.getGreaterThanOrEqualTo(value);
	}
}
