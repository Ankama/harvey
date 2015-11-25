/**
 *
 */
package com.ankamagames.dofus.harvey.singlevalues;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.interfaces.singlevalues.IOrderedSingleValueRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OrderedSingleValueRandomVariable<Data>
implements IOrderedSingleValueRandomVariable<Data>
{
	protected static class OrderedSingleValueRandomVariableWithComparator<Data>
	extends SingleValueRandomVariable<Data>
	implements IOrderedSingleValueRandomVariable<Data>, Comparable<Data>
	{
		protected Comparator<Data> _comparator;

		public OrderedSingleValueRandomVariableWithComparator(final Data value, final Comparator<Data> comparator)
		{
			super(value);
			_comparator = comparator;
		}

		public OrderedSingleValueRandomVariableWithComparator(
				final OrderedSingleValueRandomVariableWithComparator<Data> orderedSingleValueRandomVariableWithComparator)
		{
			super(orderedSingleValueRandomVariableWithComparator);
			_comparator = orderedSingleValueRandomVariableWithComparator._comparator;
		}

		@Override
		public int compareTo(@Nullable final Data value)
		{
			return _comparator.compare(_value, value);
		}

		@Override
		public int getProbabilityForLowerThan(final @Nullable Data value)
		{
			if(_comparator.compare(_value, value)<0)
				return RandomVariableUtils.ONE;
			return 0;
		}

		@Override
		public int getProbabilityForLowerThanOrEqualTo(final @Nullable Data value)
		{
			if(_comparator.compare(_value, value)<=0)
				return RandomVariableUtils.ONE;
			return 0;
		}

		@Override
		public int getProbabilityForGreaterThan(final @Nullable Data value)
		{
			if(_comparator.compare(_value, value)>0)
				return RandomVariableUtils.ONE;
			return 0;
		}

		@Override
		public int getProbabilityForGreaterThanOrEqualTo(final @Nullable Data value)
		{
			if(_comparator.compare(_value, value)>=0)
				return RandomVariableUtils.ONE;
			return 0;
		}

		@Override
		public @Nullable OrderedSingleValueRandomVariableWithComparator<Data> getLowerThan(final @Nullable Data value)
		{
			if(_comparator.compare(_value, value)<0)
				return new OrderedSingleValueRandomVariableWithComparator<Data>(this);
			return null;
		}

		@Override
		public @Nullable OrderedSingleValueRandomVariableWithComparator<Data> getLowerThanOrEqualTo(final @Nullable Data value)
		{
			if(_comparator.compare(_value, value)<=0)
				return new OrderedSingleValueRandomVariableWithComparator<Data>(this);
			return null;
		}

		@Override
		public @Nullable OrderedSingleValueRandomVariableWithComparator<Data> getGreaterThan(final @Nullable Data value)
		{
			if(_comparator.compare(_value, value)>0)
				return new OrderedSingleValueRandomVariableWithComparator<Data>(this);
			return null;
		}

		@Override
		public @Nullable OrderedSingleValueRandomVariableWithComparator<Data> getGreaterThanOrEqualTo(final @Nullable Data value)
		{
			if(_comparator.compare(_value, value)>=0)
				return new OrderedSingleValueRandomVariableWithComparator<Data>(this);
			return null;
		}
	}

	protected static class OrderedSingleValueRandomVariableWithoutComparator<Data>
	extends SingleValueRandomVariable<Data>
	implements IOrderedSingleValueRandomVariable<Data>, Comparable<Data>
	{
		public OrderedSingleValueRandomVariableWithoutComparator(final Data value)
		{
			super(value);
		}

		public OrderedSingleValueRandomVariableWithoutComparator(
				final OrderedSingleValueRandomVariableWithoutComparator<Data> orderedSingleValueRandomVariableWithoutComparator)
		{
			super(orderedSingleValueRandomVariableWithoutComparator);
		}

		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(@Nullable final Data value)
		{
			if(value!=null)
				return -((Comparable<Data>)value).compareTo(_value);
			else
			{
				final Comparable<Data> lvalue = (Comparable<Data>) _value;
				if(lvalue==null)
					return 0;
				return lvalue.compareTo(value);
			}
		}

		@Override
		public int getProbabilityForLowerThan(final @Nullable Data value)
		{
			return (compareTo(value)<0)?RandomVariableUtils.ONE:0;
		}

		@Override
		public int getProbabilityForLowerThanOrEqualTo(final @Nullable Data value)
		{
			return (compareTo(value)<=0)?RandomVariableUtils.ONE:0;
		}

		@Override
		public int getProbabilityForGreaterThan(final @Nullable Data value)
		{
			return (compareTo(value)>0)?RandomVariableUtils.ONE:0;
		}

		@Override
		public int getProbabilityForGreaterThanOrEqualTo(final @Nullable Data value)
		{
			return (compareTo(value)>=0)?RandomVariableUtils.ONE:0;
		}

		@Override
		public @Nullable OrderedSingleValueRandomVariableWithoutComparator<Data> getLowerThan(final @Nullable Data value)
		{
			if(compareTo(value)<0)
				return new OrderedSingleValueRandomVariableWithoutComparator<Data>(this);
			return null;
		}

		@Override
		public @Nullable OrderedSingleValueRandomVariableWithoutComparator<Data> getLowerThanOrEqualTo(final @Nullable Data value)
		{
			if(compareTo(value)<=0)
				return new OrderedSingleValueRandomVariableWithoutComparator<Data>(this);
			return null;
		}

		@Override
		public @Nullable OrderedSingleValueRandomVariableWithoutComparator<Data> getGreaterThan(final @Nullable Data value)
		{
			if(compareTo(value)>0)
				return new OrderedSingleValueRandomVariableWithoutComparator<Data>(this);
			return null;
		}

		@Override
		public @Nullable OrderedSingleValueRandomVariableWithoutComparator<Data> getGreaterThanOrEqualTo(final @Nullable Data value)
		{
			if(compareTo(value)>=0)
				return new OrderedSingleValueRandomVariableWithoutComparator<Data>(this);
			return null;
		}
	}

	protected IOrderedSingleValueRandomVariable<Data> _subVar;

	public OrderedSingleValueRandomVariable(final Data value, final @Nullable Comparator<Data> comparator)
	{
		if(comparator!=null)
			_subVar = new OrderedSingleValueRandomVariableWithComparator<Data>(value, comparator);
		_subVar = new OrderedSingleValueRandomVariableWithoutComparator<Data>(value);
	}

	public OrderedSingleValueRandomVariable(final Data value)
	{
		this(value, null);
	}

	@Override
	public @Nullable Data getValue()
	{
		return _subVar.getValue();
	}

	@Override
	public int getProbabilityOf(final @Nullable Data value)
	{
		return _subVar.getProbabilityOf(value);
	}

	@Override
	public boolean contains(final @Nullable Data value)
	{
		return _subVar.contains(value);
	}

	@Override
	public boolean isEmpty()
	{
		return _subVar.isEmpty();
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIOrderedRandomVariable#getProbabilityForLowerThan(java.lang.Object)
	 */
	@Override
	public int getProbabilityForLowerThan(final @Nullable Data value)
	{
		return _subVar.getProbabilityForLowerThan(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIOrderedRandomVariable#getProbabilityForLowerThanOrEqualTo(java.lang.Object)
	 */
	@Override
	public int getProbabilityForLowerThanOrEqualTo(final @Nullable Data value)
	{
		return _subVar.getProbabilityForLowerThanOrEqualTo(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIOrderedRandomVariable#getProbabilityForGreaterThan(java.lang.Object)
	 */
	@Override
	public int getProbabilityForGreaterThan(final @Nullable Data value)
	{
		return _subVar.getProbabilityForGreaterThan(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIOrderedRandomVariable#getProbabilityForGreaterThanOrEqualTo(java.lang.Object)
	 */
	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final @Nullable Data value)
	{
		return _subVar.getProbabilityForGreaterThanOrEqualTo(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIOrderedRandomVariable#getLowerThan(java.lang.Object)
	 */
	@Override
	public @Nullable IOrderedSingleValueRandomVariable<Data> getLowerThan(final @Nullable Data value)
	{
		return _subVar.getLowerThan(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIOrderedRandomVariable#getLowerThanOrEqualTo(java.lang.Object)
	 */
	@Override
	public @Nullable IOrderedSingleValueRandomVariable<Data> getLowerThanOrEqualTo(final @Nullable Data value)
	{
		return _subVar.getLowerThanOrEqualTo(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIOrderedRandomVariable#getGreaterThan(java.lang.Object)
	 */
	@Override
	public @Nullable IOrderedSingleValueRandomVariable<Data> getGreaterThan(final @Nullable Data value)
	{
		return _subVar.getGreaterThan(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIOrderedRandomVariable#getGreaterThanOrEqualTo(java.lang.Object)
	 */
	@Override
	public @Nullable IOrderedSingleValueRandomVariable<Data> getGreaterThanOrEqualTo(final @Nullable Data value)
	{
		return _subVar.getGreaterThanOrEqualTo(value);
	}
}
