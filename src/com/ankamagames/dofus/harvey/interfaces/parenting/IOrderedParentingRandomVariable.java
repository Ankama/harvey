/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.parenting;

import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IIRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedParentingRandomVariable
<
	Data,
	ChildType extends IRandomVariable<Data>&IIRandomVariableWithProbability
>
extends IParentingRandomVariable<Data, ChildType>,
IOrderedRandomVariable<Data>
{}