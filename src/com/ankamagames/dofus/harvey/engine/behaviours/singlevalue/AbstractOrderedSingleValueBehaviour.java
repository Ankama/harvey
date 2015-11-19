/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours.singlevalue;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.behaviours.IOrderedRandomVariableBehaviour;
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
public abstract class AbstractOrderedSingleValueBehaviour
<
	Data,
	ParentType extends ICompositeRandomVariable<Data, ?, ?>,
	Bridged extends IOrderedRandomVariable<Data,?>
>
extends SingleValueBehaviour<Data, Bridged>
implements IOrderedRandomVariableBehaviour<Data, ParentType>
{
	protected @Nullable Comparator<? super Data> _comparator;

	public AbstractOrderedSingleValueBehaviour(final @Nullable Data value, @Nullable final Comparator<? super Data> dataComparator, final Bridged bridged)
	{
		super(value, bridged);
		_comparator = dataComparator;
	}

	public AbstractOrderedSingleValueBehaviour(final @Nullable Data value, final Bridged bridged)
	{
		this(value, null, bridged);
	}

	public AbstractOrderedSingleValueBehaviour(final @Nullable Data value, @Nullable final Comparator<? super Data> dataComparator)
	{
		super(value);
		_comparator = dataComparator;
	}

	public AbstractOrderedSingleValueBehaviour(final @Nullable Data value)
	{
		this(value, (Comparator<? super Data>)null);
	}

	@Override
	public int compareTo(@Nullable final Data value)
	{
		final Data lValue = _value;
		if(value==null)
			if(lValue==null)
				return 0;
			else
				return 1;
		final Comparator<? super Data> comparator = _comparator;
		if(comparator==null)
		{
			if(lValue==null)
				return -1;
			@SuppressWarnings("unchecked")
			final int compareTo = ((Comparable<? super Data>)lValue).compareTo(value);
			return compareTo;
		}
		return comparator.compare(lValue, value);
	}

	@Override
	public int getProbabilityForLowerThan(final Data value,
			@Nullable final IRandomVariable<Data, ?> context)
	{
		if(compareTo(value)<0)
		{
			return _bridged.getProbability(context);
		}
		return checkContext(context);
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final Data value,
			@Nullable final IRandomVariable<Data, ?> context)
	{
		if(compareTo(value)<=0)
		{
			return _bridged.getProbability(context);
		}
		return checkContext(context);
	}

	@Override
	public int getProbabilityForGreaterThan(final Data value,
			@Nullable final IRandomVariable<Data, ?> context)
	{
		if(compareTo(value)>0)
		{
			return _bridged.getProbability(context);
		}
		return checkContext(context);
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final Data value,
			@Nullable final IRandomVariable<Data, ?> context)
	{
		if(compareTo(value)>=0)
		{
			return _bridged.getProbability(context);
		}
		return checkContext(context);
	}
}
