/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.classes;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithBehaviourProbabilityStrategyAndEditor;
import com.ankamagames.dofus.harvey.engine.behaviours.IRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.engine.editors.AbstractRandomVariableEditor.EditorToken;
import com.ankamagames.dofus.harvey.engine.editors.IRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractEditableRandomVariable
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>,
	Behaviour extends IRandomVariableBehaviour<Data>,
	ProbabilityStrategy extends IProbabilityStrategy,
	Editor extends IRandomVariableEditor<Data, ParentType>
>
extends BaseRandomVariable<Data, ParentType, Behaviour, ProbabilityStrategy>
implements IEditableRandomVariableWithBehaviourProbabilityStrategyAndEditor<Data, ParentType, Behaviour, ProbabilityStrategy, Editor>
{
	Editor _editor;

	/**
	 * @param probabilityStrategy
	 * @param parent
	 * @throws OverOneProbabilityException
	 */
	@SuppressWarnings("null")
	public AbstractEditableRandomVariable(@Nullable final ParentType parent, final Behaviour randomVariableBehaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(randomVariableBehaviour, probabilityStrategy);
		initEditor(parent);
	}

	@SuppressWarnings("null")
	public AbstractEditableRandomVariable(final Behaviour randomVariableBehaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(randomVariableBehaviour, probabilityStrategy);
		initEditor();
	}

	protected abstract void initEditor(@Nullable final ParentType parent) throws OverOneProbabilityException;

	protected abstract void initEditor() throws OverOneProbabilityException;

	/* (non-Javadoc)
	 *
	 * I chose here to use the unsafe cast solution, as seen in :
	 * http://stackoverflow.com/questions/3333590/generic-tree-self-bounded-generics
	 *
	 * @see com.ankamagames.dofus.harvey.IProbabilisticSet#setParent(com.ankamagames.dofus.harvey.composite.ICompositeProbabilisticSet)
	 */
	@Override
	public void setParent(@Nullable final ParentType parent) throws OverOneProbabilityException
	{
		_editor.setParent(parent);
	}

	@Override
	public Editor getEditor()
	{
		return _editor;
	}

	/**
	 * Direct modificator restricted to Editor
	 *
	 * @param parent
	 * @param token
	 */
	@Override
	public void setParent(@Nullable final ParentType parent, final EditorToken token)
	{
		_parent = parent;
	}
}
