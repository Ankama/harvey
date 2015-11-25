package com.ankamagames.dofus.harvey.engine.inetrfaces.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IIEditableCompositeRandomVariable
<
	Data,
	ChildType extends IRandomVariable<Data>&IIEditableParentedRandomVariable<Data, ?>
>
{
	void addSubVariable(ChildType subVariable) throws OverOneProbabilityException;
	void addSubVariables(Collection<ChildType> subVariables) throws OverOneProbabilityException;
	void removeSubVariable(ChildType subVariable);
	void removeSubVariables(Collection<ChildType> subVariables);
	void retainSubVariables(Collection<ChildType> subVariables);
	void clear();
}
