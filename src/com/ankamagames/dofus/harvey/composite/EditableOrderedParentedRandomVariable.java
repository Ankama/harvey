/**
 *
 */
package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.engine.classes.composite.AbstractEditableOrderedParentedRandomVariable;
import com.ankamagames.dofus.harvey.engine.editors.ParentedRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class EditableOrderedParentedRandomVariable
<
	Data,
	ParentType extends IRandomVariable<Data>,
	ChildType extends IEditableOrderedRandomVariable<Data>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends AbstractEditableOrderedParentedRandomVariable
<
	Data,
	ParentType,
	ChildType,
	ProbabilityStrategy,
	ParentedRandomVariableEditor<Data, ParentType, ? extends EditableOrderedParentedRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>>
>
{
	protected ParentedRandomVariableEditor<Data, ParentType, ? extends EditableOrderedParentedRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>> _editor;

	public EditableOrderedParentedRandomVariable(final ChildType heldRandomVariable,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(heldRandomVariable, parent, probabilityStrategy);
		_editor = new ParentedRandomVariableEditor<Data, ParentType, EditableOrderedParentedRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>>(this);
	}

	@Override
	public ParentedRandomVariableEditor<Data, ParentType, ? extends EditableOrderedParentedRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>> getEditor()
	{
		return _editor;
	}
}
