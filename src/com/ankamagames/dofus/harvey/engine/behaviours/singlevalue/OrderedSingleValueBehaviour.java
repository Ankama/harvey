/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours.singlevalue;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;
import com.ankamagames.dofus.harvey.singlevalue.GenericOrderedSingleValueRandomVariableView;
import com.ankamagames.dofus.harvey.singlevalue.OrderedSingleValueRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OrderedSingleValueBehaviour
<
	Data,
	ParentType extends ICompositeRandomVariable<Data, ?, ?>,
	Behaviour extends AbstractOrderedSingleValueBehaviour<Data, ParentType, Bridged>,
	Bridged extends OrderedSingleValueRandomVariable<Data, ParentType, Behaviour, ?>
>
extends AbstractOrderedSingleValueBehaviour<Data, ParentType, Bridged>
{
	public OrderedSingleValueBehaviour(final @Nullable Data value,
			final Comparator<? super Data> dataComparator, final Bridged bridged)
	{
		super(value, dataComparator, bridged);
	}

	public OrderedSingleValueBehaviour(final@Nullable Data value, final Bridged bridged)
	{
		super(value, bridged);
	}

	public OrderedSingleValueBehaviour(final @Nullable Data value, final Comparator<? super Data> dataComparator)
	{
		super(value, dataComparator);
	}

	public OrderedSingleValueBehaviour(final @Nullable Data value)
	{
		super(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.behaviours.IOrderedRandomVariableBehaviour#getLowerThan(java.lang.Object)
	 */
	@Override
	public IOrderedRandomVariable<Data, ParentType> getLowerThan(final @Nullable Data value)
	{
		try
		{
			if(compareTo(value)<0)
				return new GenericOrderedSingleValueRandomVariableView<Data, ParentType, Behaviour>(_bridged);
			return new GenericOrderedSingleValueRandomVariableView<Data, ParentType, Behaviour>(_bridged, ReadOnlyProbabilityStrategy.ZERO_PROBABILITY);
		} catch (final OverOneProbabilityException e)
		{
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.behaviours.IOrderedRandomVariableBehaviour#getLowerThanOrEqualTo(java.lang.Object)
	 */
	@Override
	public IOrderedRandomVariable<Data, ParentType> getLowerThanOrEqualTo(
			final @Nullable Data value)
	{
		try
		{
			if(compareTo(value)<=0)
				return new GenericOrderedSingleValueRandomVariableView<Data, ParentType, Behaviour>(_bridged);
			return new GenericOrderedSingleValueRandomVariableView<Data, ParentType, Behaviour>(_bridged, ReadOnlyProbabilityStrategy.ZERO_PROBABILITY);
		} catch (final OverOneProbabilityException e)
		{
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.behaviours.IOrderedRandomVariableBehaviour#getGreaterThan(java.lang.Object)
	 */
	@Override
	public IOrderedRandomVariable<Data, ParentType> getGreaterThan(final @Nullable Data value)
	{
		try
		{
			if(compareTo(value)>0)
				return new GenericOrderedSingleValueRandomVariableView<Data, ParentType, Behaviour>(_bridged);
			return new GenericOrderedSingleValueRandomVariableView<Data, ParentType, Behaviour>(_bridged, ReadOnlyProbabilityStrategy.ZERO_PROBABILITY);
		} catch (final OverOneProbabilityException e)
		{
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.behaviours.IOrderedRandomVariableBehaviour#getGreaterThanOrEqualTo(java.lang.Object)
	 */
	@Override
	public IOrderedRandomVariable<Data, ParentType> getGreaterThanOrEqualTo(
			final @Nullable Data value)
	{
		try
		{
			if(compareTo(value)>=0)
				return new GenericOrderedSingleValueRandomVariableView<Data, ParentType, Behaviour>(_bridged);
			return new GenericOrderedSingleValueRandomVariableView<Data, ParentType, Behaviour>(_bridged, ReadOnlyProbabilityStrategy.ZERO_PROBABILITY);
		} catch (final OverOneProbabilityException e)
		{
			throw new RuntimeException(e);
		}
	}

}
