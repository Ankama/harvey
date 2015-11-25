/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.editors.CompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.editors.IHasEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.composite.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.IEditableParentedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractEditableCompositeRandomVariable
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>,
	ChildType extends IEditableParentedRandomVariable<Data, ? extends AbstractEditableCompositeRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy, Editor>>,
	ProbabilityStrategy extends IProbabilityStrategy,
	Editor extends CompositeRandomVariableEditor<Data, ParentType, ChildType, ? extends AbstractEditableCompositeRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy, Editor>>
>
extends AbstractCompositeRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>
implements IEditableCompositeRandomVariable<Data, ParentType, ChildType>, IHasEditor<Data, Editor>
{
	public AbstractEditableCompositeRandomVariable(
			final ChildType heldRandomVariable, final ParentType parent,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(heldRandomVariable, parent, probabilityStrategy);
	}

	@Override
	public void setProbabilityOf(final @Nullable Data value, final int probability)
			throws OverOneProbabilityException
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

	@Override
	public void addSubVariable(final ChildType subVariable) throws OverOneProbabilityException
	{
		getEditor().addSubVariable(subVariable);
	}

	@Override
	public void addSubVariables(final Collection<ChildType> subVariables) throws OverOneProbabilityException
	{
		getEditor().addSubVariables(subVariables);
	}

	@Override
	public void removeSubVariable(final ChildType subVariable)
	{
		getEditor().removeSubVariable(subVariable);
	}

	@Override
	public void removeSubVariables(final Collection<ChildType> subVariables)
	{
		getEditor().removeSubVariables(subVariables);
	}

	@Override
	public void retainSubVariables(final Collection<ChildType> subVariables)
	{
		getEditor().retainSubVariables(subVariables);
	}

	@Override
	public void clear()
	{
		getEditor().clear();
	}
}
