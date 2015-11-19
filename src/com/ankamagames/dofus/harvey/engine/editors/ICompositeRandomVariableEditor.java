package com.ankamagames.dofus.harvey.engine.editors;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface ICompositeRandomVariableEditor<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, ChildType extends IEditableRandomVariable<Data, ?>>
	extends IRandomVariableEditor<Data, ParentType>
{

	void checkConsistency() throws OverOneProbabilityException;

	void addElement(ChildType newChild) throws OverOneProbabilityException;

	void addElements(ChildType... newChilds) throws OverOneProbabilityException;

	void addElements(Collection<ChildType> newChilds)
			throws OverOneProbabilityException;

}
