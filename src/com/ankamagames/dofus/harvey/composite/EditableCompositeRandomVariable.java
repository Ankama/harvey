/**
 *
 */
package com.ankamagames.dofus.harvey.composite;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.ankamagames.dofus.harvey.engine.classes.composite.AbstractEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.classes.parenting.ParentingRandomVariable;
import com.ankamagames.dofus.harvey.engine.editors.CompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;
import com.ankamagames.dofus.harvey.engine.inetrfaces.parentedwithprobability.IEditableParentedRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IModifiableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.composite.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class EditableCompositeRandomVariable
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ParentType, ?, ?>,
	ChildType extends IEditableParentedRandomVariableWithProbabilityStrategy<Data, ParentType, ?>,
	ProbabilityStrategy extends IModifiableProbabilityStrategy
>
extends
AbstractEditableCompositeRandomVariable<
	Data,
	ParentType,
	ParentingRandomVariable<Data, ChildType>,
	ChildType,
	ProbabilityStrategy,
	CompositeRandomVariableEditor<Data, ParentType, ChildType, ? extends EditableCompositeRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>>
>
{
	protected HashSet<ChildType> _subVariables;
	protected CompositeRandomVariableEditor<Data, ParentType, ChildType, EditableCompositeRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>> _editor;

	public EditableCompositeRandomVariable(final Collection<ChildType> items,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(
				parent,
				new ParentingRandomVariable<Data, ChildType>(items),
				probabilityStrategy
		);
		_subVariables = new HashSet<ChildType>();
		_editor = new CompositeRandomVariableEditor<Data, ParentType, ChildType, EditableCompositeRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>>(this);
	}

	@Override
	public CompositeRandomVariableEditor<Data, ParentType, ChildType, ? extends EditableCompositeRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>> getEditor()
	{
		return _editor;
	}

	@Override
	public Set<ChildType> getSubVariables()
	{
		return _subVariables;
	}

	@Override
	public void setProbability(final int probability)
			throws ProbabilityOutOfBoundException
	{
		_probabilityStrategy.setProbability(probability);
	}

	@Override
	public void addProbability(final int probability)
			throws ProbabilityOutOfBoundException
	{
		_probabilityStrategy.addProbability(probability);
	}

	@Override
	public void removeProbability(final int probability)
			throws ProbabilityOutOfBoundException
	{
		_probabilityStrategy.removeProbability(probability);
	}
}