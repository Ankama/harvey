/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.editors.ICompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.editors.IHasEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;
import com.ankamagames.dofus.harvey.engine.inetrfaces.parentedwithprobability.IEditableParentedRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.composite.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;

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
	ParentType extends IEditableCompositeRandomVariable<Data, ParentType, ?, ?>,
	HeldType extends IParentingRandomVariable<Data, ChildType>,
	ChildType extends IEditableParentedRandomVariableWithProbabilityStrategy<Data, ParentType, ?>,
	ProbabilityStrategy extends IModifiableProbabilityStrategy,
	Editor extends ICompositeRandomVariableEditor<Data, ChildType, ? extends AbstractEditableCompositeRandomVariable<Data, ParentType, HeldType, ChildType, ProbabilityStrategy, Editor>>
>
extends AbstractCompositeRandomVariable<Data, ParentType, HeldType, ChildType, ProbabilityStrategy>
implements IEditableCompositeRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>, IHasEditor<Data, Editor>
{
	public AbstractEditableCompositeRandomVariable(
			final @Nullable ParentType parent, final HeldType heldRandomVariable,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(parent, heldRandomVariable, probabilityStrategy);
	}

	public void setProbabilityOf(final @Nullable Data value, final int probability)
			throws ProbabilityOutOfBoundException
	{
		getEditor().setProbabilityOf(value, probability);
	}

	public boolean remove(final @Nullable Data value)
	{
		return getEditor().remove(value);
	}

	public void add(final @Nullable Data value, final int probability)
	{
		getEditor().add(value, probability);
	}

	public void addProbabilityTo(final @Nullable Data value, final int delta)
	{
		getEditor().addProbabilityTo(value, delta);
	}

	public void removeProbabilityTo(final @Nullable Data value, final int delta)
	{
		getEditor().removeProbabilityTo(value, delta);
	}

	public void setParent(final @Nullable ParentType parent)
	{
		_parent = parent;
	}

	@Override
	public void addSubVariable(final ChildType subVariable) throws ProbabilityOutOfBoundException
	{
		getEditor().addSubVariable(subVariable);
	}

	@Override
	public void addSubVariables(final Collection<ChildType> subVariables) throws ProbabilityOutOfBoundException
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
