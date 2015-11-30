/**
 *
 */
package com.ankamagames.dofus.harvey.engine.editors;

import java.util.Collection;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
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
public class ParentingRandomVariableEditor
<
	Data,
	ChildType extends IRandomVariable<Data>&IIRandomVariableWithProbability,
	Bridged extends IEditableParentingRandomVariable<Data, ChildType>
>
implements IParentingRandomVariableEditor<Data, ChildType, Bridged>
{
	protected Bridged _bridged;

	public ParentingRandomVariableEditor(final Bridged bridged)
	{
		_bridged = bridged;
	}

	@Override
	public Bridged getBridged()
	{
		return _bridged;
	}

	public void check() throws ProbabilityOutOfBoundException
	{
		long sum = 0;
		for(final ChildType subVariable:_bridged.getSubVariables())
		{
			sum+=subVariable.getProbability();
		}
		if(sum>RandomVariableUtils.ONE)
			throw new ProbabilityOutOfBoundException();
	}

	public void addSubVariableNoCheck(final ChildType subVariable)
	{
		_bridged.getSubVariables().add(subVariable);
	}

	@Override
	public void addSubVariable(final ChildType subVariable)
			throws ProbabilityOutOfBoundException
	{
		addSubVariableNoCheck(subVariable);
		check();
	}

	public void addSubVariablesNoCheck(final Collection<ChildType> subVariables)
	{
		_bridged.getSubVariables().addAll(subVariables);
	}

	@Override
	public void addSubVariables(final Collection<ChildType> subVariables)
			throws ProbabilityOutOfBoundException
	{
		addSubVariablesNoCheck(subVariables);
		check();
	}

	@Override
	public void removeSubVariable(final ChildType subVariable)
	{
		_bridged.getSubVariables().remove(subVariable);
	}

	@Override
	public void removeSubVariables(final Collection<ChildType> subVariables)
	{
		_bridged.getSubVariables().removeAll(subVariables);
	}

	@Override
	public void retainSubVariables(final Collection<ChildType> subVariables)
	{
		_bridged.getSubVariables().retainAll(subVariables);
	}

	@Override
	public void clear()
	{
		_bridged.getSubVariables().clear();
	}
}
