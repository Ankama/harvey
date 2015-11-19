/**
 *
 */
package com.ankamagames.dofus.harvey.engine.editors;

import com.ankamagames.dofus.harvey.engine.base.classes.BaseRandomVariable;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class RandomVariableEditor
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>,
	BridgedType extends BaseRandomVariable<Data, ParentType, ?, ?>&IEditableRandomVariableWithEditor<Data, ParentType, ?>
>
extends AbstractRandomVariableEditor<Data, ParentType, BridgedType>
{
	public RandomVariableEditor(final BridgedType abstractEditableRandomVariable)
	{
		super(abstractEditableRandomVariable);
	}

	public RandomVariableEditor(
			final BridgedType abstractEditableRandomVariable,
			final @Nullable ParentType parent) throws OverOneProbabilityException
	{
		super(abstractEditableRandomVariable, parent);
	}

	@Override
	public void setParent(@Nullable final ParentType parent) throws OverOneProbabilityException
	{
		if((parent!=null)&&(!parent.equals(_bridged.getParent())))
		{
			_bridged.setParent(parent, getEditorTocken());
	        @SuppressWarnings( "unchecked" )
			final IEditableCompositeRandomVariable<Data, ?, IEditableRandomVariable<Data, ?>> cast = (IEditableCompositeRandomVariable<Data, ?, IEditableRandomVariable<Data, ?>>)parent;
	        cast.addElement(_bridged);
		}
		else if((parent == null)&&(parent != _bridged.getParent()))
			_bridged.setParent(parent, getEditorTocken());
		//if parent.equals(_parent)
		//do nothing
	}
}
