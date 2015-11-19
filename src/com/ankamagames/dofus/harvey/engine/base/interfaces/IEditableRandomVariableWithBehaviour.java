package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.behaviours.IRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IEditableRandomVariableWithBehaviour<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, Behaviour extends IRandomVariableBehaviour<Data>>
	extends IEditableRandomVariable<Data, ParentType>, IRandomVariableWithBehaviour<Data, ParentType, Behaviour>
{
}
