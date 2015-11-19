package com.ankamagames.dofus.harvey.engine.base.classes;

import com.ankamagames.dofus.harvey.engine.behaviours.singlevalue.ISingleValueBehaviour;
import com.ankamagames.dofus.harvey.engine.editors.RandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableSingleValueRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class BaseEditableSingleValueRandomVariable
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>,
	Behaviour extends ISingleValueBehaviour<Data>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends AbstractEditableRandomVariable<Data, ParentType, Behaviour, ProbabilityStrategy, RandomVariableEditor<Data, ParentType, BaseEditableSingleValueRandomVariable<Data, ParentType, Behaviour, ProbabilityStrategy>>>
implements IEditableSingleValueRandomVariable<Data, ParentType>
{
	public BaseEditableSingleValueRandomVariable(final @Nullable ParentType parent, final Behaviour behaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(parent, behaviour, probabilityStrategy);
	}

	public BaseEditableSingleValueRandomVariable(final Behaviour behaviour, final ProbabilityStrategy strategy) throws OverOneProbabilityException
	{
		super(behaviour, strategy);
	}

	@Override
	protected void initEditor(final @Nullable ParentType parent) throws OverOneProbabilityException
	{
		_editor = new RandomVariableEditor<Data, ParentType, BaseEditableSingleValueRandomVariable<Data, ParentType, Behaviour, ProbabilityStrategy>>(this, parent);
	}

	@Override
	protected void initEditor()
	{
		_editor = new RandomVariableEditor<Data, ParentType, BaseEditableSingleValueRandomVariable<Data, ParentType, Behaviour, ProbabilityStrategy>>(this);
	}

	@Override
	public @Nullable Data getValue()
	{
		return _behaviour.getValue();
	}
}
