/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.parentedwithprobability;

import com.ankamagames.dofus.harvey.interfaces.parented.IParentedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.withprobability.IRandomVariableWithProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IParentedRandomVariableWithProbability<Data, ParentType extends IParentingRandomVariable<Data, ?>>
	extends IParentedRandomVariable<Data, ParentType>,
	IRandomVariableWithProbability<Data>
{}