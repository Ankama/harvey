package com.ankamagames.dofus.harvey.interfaces.composite;

import com.ankamagames.dofus.harvey.engine.inetrfaces.composite.IICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parentedwithprobability.IParentedRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface ICompositeRandomVariable
<
	Data,
	ParentType extends IParentingRandomVariable<Data, ?>,
	ChildType extends IParentedRandomVariableWithProbability<Data, ?>
>
extends IParentedRandomVariableWithProbability<Data, ParentType>,
IParentingRandomVariable<Data, ChildType>,
IICompositeRandomVariable<Data>
{}