package com.ankamagames.dofus.harvey.engine.inetrfaces.parenting;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IIEditableParentingRandomVariable
<
	Data,
	ChildType extends IRandomVariable<Data>
>
{
	void addSubVariable(ChildType subVariable) throws ProbabilityOutOfBoundException;
	void addSubVariables(Collection<ChildType> subVariables) throws ProbabilityOutOfBoundException;
	void removeSubVariable(ChildType subVariable);
	void removeSubVariables(Collection<ChildType> subVariables);
	void retainSubVariables(Collection<ChildType> subVariables);
	void clear();
}