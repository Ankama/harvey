package com.ankamagames.dofus.harvey.engine.editors;

import com.ankamagames.dofus.harvey.composite.IMergingCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.base.classes.BaseRandomVariable;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IMergeableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class MergingCompositeRandomVariableEditor
<
Data,
ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>,
ChildType extends IMergeableRandomVariable<Data, ?>,
BridgedType extends BaseRandomVariable<Data, ParentType, ?, ?>&IMergingCompositeRandomVariable<Data, ParentType, ChildType>&IEditableRandomVariableWithEditor<Data, ParentType, ?>
>
extends	CompositeRandomVariableEditor<Data, ParentType, ChildType, BridgedType>
{
	public MergingCompositeRandomVariableEditor(
			final BridgedType editableMergingCompositeRandomVariable)
	{
		super(editableMergingCompositeRandomVariable);
	}

	public MergingCompositeRandomVariableEditor(
			final BridgedType editableMergingCompositeRandomVariable,
			@Nullable final ParentType parent) throws OverOneProbabilityException
	{
		super(editableMergingCompositeRandomVariable, parent);
	}

	@SuppressWarnings("unchecked")
	protected <ChildParentType extends IEditableCompositeRandomVariable<Data, ?, ?>> boolean _deboxingMerge(final ChildType mergeable, final ChildType merged)
	{
		final IMergeableRandomVariable<Data, ChildParentType> _mergeable = (IMergeableRandomVariable<Data, ChildParentType>) mergeable;
		final IMergeableRandomVariable<Data, ChildParentType> _merged = (IMergeableRandomVariable<Data, ChildParentType>) merged;
		return _mergeable.merge(_merged);
	}

	@Override
	public void addElement(final ChildType newChild)
			throws OverOneProbabilityException
	{
		for(final ChildType subVariable:_bridged.getSubVariables())
		{
			if(_deboxingMerge(subVariable, newChild))
				return;
		}
		super.addElement(newChild);
	}
}
