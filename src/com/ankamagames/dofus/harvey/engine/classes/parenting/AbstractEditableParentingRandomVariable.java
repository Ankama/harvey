/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.parenting;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.editors.IHasEditor;
import com.ankamagames.dofus.harvey.engine.editors.IParentingRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;
import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IIRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parenting.IEditableParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractEditableParentingRandomVariable
<
	Data,
	ChildType extends IRandomVariable<Data>&IIRandomVariableWithProbability,
	Editor extends IParentingRandomVariableEditor<Data, ChildType, ? extends AbstractEditableParentingRandomVariable<Data, ChildType, Editor>>
>
extends AbstractParentingRandomVariable<Data, ChildType>
implements IEditableParentingRandomVariable<Data, ChildType>, IHasEditor<Data, Editor>
{
	@Override
	public void addSubVariable(final ChildType subVariable)
			throws ProbabilityOutOfBoundException
	{
		getEditor().addSubVariable(subVariable);
	}

	@Override
	public void addSubVariables(final Collection<ChildType> subVariables)
			throws ProbabilityOutOfBoundException
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