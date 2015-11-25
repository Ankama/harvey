/**
 *
 */
package com.ankamagames.dofus.harvey.singlevalues;

import com.ankamagames.dofus.harvey.interfaces.singlevalues.IEditableSingleValueRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class EditableSingleValueRandomVariable<Data>
	extends SingleValueRandomVariable<Data>
	implements IEditableSingleValueRandomVariable<Data>
{
	public EditableSingleValueRandomVariable(final Data value)
	{
		super(value);
	}

	public EditableSingleValueRandomVariable(final EditableSingleValueRandomVariable<Data> value)
	{
		super(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.singlevalues.IIEditableSingleValueRandomVariable#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(@Nullable final Data value)
	{
		_value = value;
	}
}
