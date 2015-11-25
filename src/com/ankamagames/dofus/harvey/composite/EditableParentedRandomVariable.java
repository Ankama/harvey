/**
 *
 */
package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.engine.classes.composite.AbstractEditableParentedRandomVariable;
import com.ankamagames.dofus.harvey.engine.editors.ParentedRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class EditableParentedRandomVariable
<
	Data,
	ParentType extends IRandomVariable<Data>,
	ChildType extends IEditableRandomVariable<Data>,
	ProbabilityStrategy extends IModifiableProbabilityStrategy
>
extends AbstractEditableParentedRandomVariable
<
	Data,
	ParentType,
	ChildType,
	ProbabilityStrategy,
	ParentedRandomVariableEditor<Data, ParentType, ? extends EditableParentedRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>>
>
{
	protected ParentedRandomVariableEditor<Data, ParentType, ? extends EditableParentedRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>> _editor;

	public EditableParentedRandomVariable(final ChildType heldRandomVariable,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(heldRandomVariable, parent, probabilityStrategy);
		_editor = new ParentedRandomVariableEditor<Data, ParentType, EditableParentedRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>>(this);
	}

	@Override
	public ParentedRandomVariableEditor<Data, ParentType, ? extends EditableParentedRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>> getEditor()
	{
		return _editor;
	}
}