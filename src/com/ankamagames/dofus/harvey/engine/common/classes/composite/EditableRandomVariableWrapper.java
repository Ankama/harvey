/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class EditableRandomVariableWrapper
<
	ChildType extends IRandomVariable,
	ParentType extends AbstractCompositeRandomVariable<?>&IEditableRandomVariable,
	ProbabilityStrategy extends IEditableProbabilityStrategy
>
extends RandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>
implements IEditableRandomVariable
{
	BridgedRandomVariableWrapperEditor<?> _editor;

	public EditableRandomVariableWrapper(final ChildType element, final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
		_editor = new BridgedRandomVariableWrapperEditor<EditableRandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>>(this);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#clear()
	 */
	@Override
	public void clear()
	{
		_editor.clear();
	}
}