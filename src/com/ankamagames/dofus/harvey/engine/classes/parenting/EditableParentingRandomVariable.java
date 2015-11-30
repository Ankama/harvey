/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.parenting;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.ankamagames.dofus.harvey.engine.editors.ParentingRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;
import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IIRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parenting.IEditableParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class EditableParentingRandomVariable
<
	Data,
	ChildType extends IRandomVariable<Data>&IIRandomVariableWithProbability
>
extends AbstractEditableParentingRandomVariable<Data, ChildType, ParentingRandomVariableEditor<Data, ChildType, EditableParentingRandomVariable<Data,ChildType>>>
implements IEditableParentingRandomVariable<Data, ChildType>
{
	protected HashSet<ChildType> _subVariables;
	protected ParentingRandomVariableEditor<Data, ChildType, EditableParentingRandomVariable<Data, ChildType>> _editor;

	public EditableParentingRandomVariable(final Collection<ChildType> items)
	{
		_subVariables = new HashSet<ChildType>(items);
		_editor = new ParentingRandomVariableEditor<Data, ChildType, EditableParentingRandomVariable<Data,ChildType>>(this);
	}

	@Override
	public Set<ChildType> getSubVariables()
	{
		return Collections.unmodifiableSet(_subVariables);
	}

	protected void check() throws ProbabilityOutOfBoundException
	{
		_editor.check();
	}

	protected void addSubVariableNoCheck(final ChildType subVariable)
	{
		_editor.addSubVariableNoCheck(subVariable);
	}

	protected void addSubVariablesNoCheck(final Collection<ChildType> subVariables)
	{
		_editor.addSubVariablesNoCheck(subVariables);
	}

	@Override
	public ParentingRandomVariableEditor<Data, ChildType, EditableParentingRandomVariable<Data, ChildType>> getEditor()
	{
		return _editor;
	}
}
