/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.classes;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IterableMergingCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.behaviours.composite.ICompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.editors.MergingCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IMergeableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class MergingCompositeRandomVariable
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>,
	ChildType extends IMergeableRandomVariable<Data, ?>,
	Behaviour extends ICompositeBehaviour<Data, ChildType>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends	EditableCompositeRandomVariable<Data, ParentType, ChildType, Behaviour, ProbabilityStrategy>
implements IterableMergingCompositeRandomVariable<Data, ParentType, ChildType>
{
	public MergingCompositeRandomVariable(@Nullable final ParentType parent, final Behaviour behaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(parent, behaviour, probabilityStrategy);
	}

	public MergingCompositeRandomVariable(final Behaviour behaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(behaviour, probabilityStrategy);
	}

	@Override
	protected void initEditor(@Nullable final ParentType parent)
			throws OverOneProbabilityException
	{
		_editor = new MergingCompositeRandomVariableEditor<
				Data,
				ParentType,
				ChildType,
				MergingCompositeRandomVariable<Data, ParentType, ChildType, Behaviour, ProbabilityStrategy>
		>(this, parent);
	}

	@Override
	protected void initEditor()
	{
		_editor = new MergingCompositeRandomVariableEditor<
				Data,
				ParentType,
				ChildType,
				MergingCompositeRandomVariable<Data, ParentType, ChildType, Behaviour, ProbabilityStrategy>
		>(this);
	}
}
