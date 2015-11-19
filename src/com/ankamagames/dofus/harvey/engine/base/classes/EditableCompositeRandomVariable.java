package com.ankamagames.dofus.harvey.engine.base.classes;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IterableEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.behaviours.composite.ICompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.editors.CompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.editors.ICompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class EditableCompositeRandomVariable
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>,
	ChildType extends IEditableRandomVariable<Data, ?>,
	Behaviour extends ICompositeBehaviour<Data, ChildType>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends AbstractEditableRandomVariable<Data, ParentType, Behaviour, ProbabilityStrategy, ICompositeRandomVariableEditor<Data, ParentType, ChildType>>
implements IterableEditableCompositeRandomVariable<Data, ParentType, ChildType>, IEditableRandomVariableWithProbabilityStrategy<Data, ParentType, ProbabilityStrategy>
{
	ICompositeRandomVariableEditor<Data, ParentType, ChildType> _editor;

	@SuppressWarnings("null")
	public EditableCompositeRandomVariable(@Nullable final ParentType parent, final Behaviour behaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(behaviour, probabilityStrategy);
		initEditor(parent);
	}

	@SuppressWarnings("null")
	public EditableCompositeRandomVariable(final Behaviour behaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(behaviour, probabilityStrategy);
		initEditor();
	}

	@Override
	protected void initEditor(final @Nullable ParentType parent) throws OverOneProbabilityException
	{
		_editor = new CompositeRandomVariableEditor<
				Data,
				ParentType,
				ChildType,
				EditableCompositeRandomVariable<Data, ParentType, ChildType, Behaviour, ProbabilityStrategy>
		>(this, parent);
	}

	@Override
	protected void initEditor()
	{
		_editor = new CompositeRandomVariableEditor<
				Data,
				ParentType,
				ChildType,
				EditableCompositeRandomVariable<Data, ParentType, ChildType, Behaviour, ProbabilityStrategy>
		>(this);
	}

	@Override
	public void setParent(final @Nullable ParentType parent) throws OverOneProbabilityException
	{
		_editor.setParent(parent);
	}

	@Override
	public void checkConsistency() throws OverOneProbabilityException
	{
		_editor.checkConsistency();
	}

	@Override
	public void addElement(final ChildType newChild)
			throws OverOneProbabilityException
	{
		_editor.addElement(newChild);
	}

	@Override
	public void addElements(final ChildType... newChilds)
			throws OverOneProbabilityException
	{
		_editor.addElements(newChilds);
	}

	@Override
	public void addElements(final Collection<ChildType> newChilds)
			throws OverOneProbabilityException
	{
		_editor.addElements(newChilds);
	}

	@Override
	public Set<ChildType> getSubVariables()
	{
		return _behaviour.getSubVariables();
	}

	@Override
	public Iterator<ChildType> iterator()
	{
		return getSubVariables().iterator();
	}
}
