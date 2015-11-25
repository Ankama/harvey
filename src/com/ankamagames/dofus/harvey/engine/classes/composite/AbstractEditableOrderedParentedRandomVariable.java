package com.ankamagames.dofus.harvey.engine.classes.composite;

import com.ankamagames.dofus.harvey.composite.OrderedParentedRandomVariable;
import com.ankamagames.dofus.harvey.engine.editors.IHasEditor;
import com.ankamagames.dofus.harvey.engine.editors.ParentedRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.IEditableOrderedParentedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public abstract class AbstractEditableOrderedParentedRandomVariable
<
	Data,
	ParentType extends IRandomVariable<Data>,
	ChildType extends IOrderedRandomVariable<Data>,
	ProbabilityStrategy extends IProbabilityStrategy,
	Editor extends ParentedRandomVariableEditor<Data, ParentType, ? extends AbstractEditableOrderedParentedRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy, Editor>>
>
extends OrderedParentedRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>
implements IEditableOrderedParentedRandomVariable<Data, ParentType>, IHasEditor<Data, Editor>
{
	public AbstractEditableOrderedParentedRandomVariable(final ChildType heldRandomVariable,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
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
