package com.ankamagames.dofus.harvey.engine.classes.composite;

import com.ankamagames.dofus.harvey.composite.ParentedRandomVariable;
import com.ankamagames.dofus.harvey.engine.editors.IHasEditor;
import com.ankamagames.dofus.harvey.engine.editors.ParentedRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.IEditableParentedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public abstract class AbstractEditableParentedRandomVariable
<
	Data,
	ParentType extends IRandomVariable<Data>,
	ChildType extends IEditableRandomVariable<Data>,
	ProbabilityStrategy extends IModifiableProbabilityStrategy,
	Editor extends ParentedRandomVariableEditor<Data, ParentType, ? extends AbstractEditableParentedRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy, Editor>>
>
extends ParentedRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>
implements IEditableParentedRandomVariable<Data, ParentType>, IHasEditor<Data, Editor>
{
	public AbstractEditableParentedRandomVariable(
			final ChildType heldRandomVariable, final @Nullable ParentType parent,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(heldRandomVariable, parent, probabilityStrategy);
	}

	@Override
	public abstract Editor getEditor();

	@Override
	public void setProbabilityOf(final @Nullable Data value, final int probability) throws OverOneProbabilityException
	{
		getEditor().setProbabilityOf(value, probability);
	}

	@Override
	public boolean remove(final @Nullable Data value)
	{
		return getEditor().remove(value);
	}

	@Override
	public void add(final @Nullable Data value, final int probability)
	{
		getEditor().add(value, probability);
	}

	@Override
	public void addProbabilityTo(final @Nullable Data value, final int delta)
	{
		getEditor().addProbabilityTo(value, delta);
	}

	@Override
	public void removeProbabilityTo(final @Nullable Data value, final int delta)
	{
		getEditor().removeProbabilityTo(value, delta);
	}

	@Override
	public void setParent(final @Nullable ParentType parent)
	{
		_parent = parent;
	}
}
