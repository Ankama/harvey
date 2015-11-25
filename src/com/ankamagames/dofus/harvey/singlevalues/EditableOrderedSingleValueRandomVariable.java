/**
 *
 */
package com.ankamagames.dofus.harvey.singlevalues;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.interfaces.singlevalues.IEditableOrderedSingleValueRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.singlevalues.IOrderedSingleValueRandomVariable;
import com.ankamagames.dofus.harvey.singlevalues.OrderedSingleValueRandomVariable.OrderedSingleValueRandomVariableWithComparator;
import com.ankamagames.dofus.harvey.singlevalues.OrderedSingleValueRandomVariable.OrderedSingleValueRandomVariableWithoutComparator;

import org.eclipse.jdt.annotation.NonNullByDefault;

import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class EditableOrderedSingleValueRandomVariable<Data>
implements IEditableOrderedSingleValueRandomVariable<Data>
{
	protected static  class EditableOrderedSingleValueRandomVariableWithComparator<Data>
	extends OrderedSingleValueRandomVariableWithComparator<Data>
	implements IEditableOrderedSingleValueRandomVariable<Data>
	{
		public EditableOrderedSingleValueRandomVariableWithComparator(
				final Data value, final Comparator<Data> comparator)
		{
			super(value, comparator);
		}

		@Override
		public void setValue(@Nullable final Data value)
		{
			_value = value;
		}
	}

	protected static  class EditableOrderedSingleValueRandomVariableWithoutComparator<Data>
	extends OrderedSingleValueRandomVariableWithoutComparator<Data>
	implements IEditableOrderedSingleValueRandomVariable<Data>
	{
		public EditableOrderedSingleValueRandomVariableWithoutComparator(
				final Data value)
		{
			super(value);
		}

		@Override
		public void setValue(@Nullable final Data value)
		{
			_value = value;
		}
	}

	protected IEditableOrderedSingleValueRandomVariable<Data> _subVar;

	public EditableOrderedSingleValueRandomVariable(final Data value,
			final @Nullable Comparator<Data> comparator)
	{
		if(comparator!=null)
			_subVar = new EditableOrderedSingleValueRandomVariableWithComparator<Data>(value, comparator);
		_subVar = new EditableOrderedSingleValueRandomVariableWithoutComparator<Data>(value);
	}

	public EditableOrderedSingleValueRandomVariable(final Data value)
	{
		this(value, null);
	}

	@Override
	public @Nullable Data getValue()
	{
		return _subVar.getValue();
	}

	@Override
	public void setValue(@Nullable final Data value)
	{
		_subVar.setValue(value);
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
