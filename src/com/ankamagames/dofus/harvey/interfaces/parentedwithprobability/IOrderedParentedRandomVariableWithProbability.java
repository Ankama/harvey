/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.parentedwithprobability;

import com.ankamagames.dofus.harvey.interfaces.parented.IOrderedParentedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.withprobability.IOrderedRandomVariableWithProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedParentedRandomVariableWithProbability<Data, ParentType extends IParentingRandomVariable<Data, ?>>
extends IParentedRandomVariableWithProbability<Data, ParentType>,
IOrderedParentedRandomVariable<Data, ParentType>,
IOrderedRandomVariableWithProbability<Data>
{}