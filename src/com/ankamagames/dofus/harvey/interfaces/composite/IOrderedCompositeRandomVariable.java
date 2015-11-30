/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.composite;

import com.ankamagames.dofus.harvey.engine.inetrfaces.composite.IIOrderedCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parentedwithprobability.IOrderedParentedRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.parenting.IOrderedParentingRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.withprobability.IOrderedRandomVariableWithProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedCompositeRandomVariable
<
	Data,
	ParentType extends IParentingRandomVariable<Data, ?>,
	ChildType extends IOrderedParentedRandomVariableWithProbability<Data, ?>
>
extends ICompositeRandomVariable<Data, ParentType, ChildType>,
IOrderedRandomVariableWithProbability<Data>,
IOrderedParentingRandomVariable<Data, ChildType>,
IIOrderedCompositeRandomVariable<Data, ParentType, ChildType>
{}