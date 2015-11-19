package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.behaviours.IRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IRandomVariableWithBehaviour<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>, Behaviour extends IRandomVariableBehaviour<Data>>
extends IRandomVariable<Data, ParentType>, IHasBehaviour<Data, Behaviour>
{}