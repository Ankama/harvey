/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces.parenting;

import java.util.Set;

import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IIRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIParentingRandomVariable
<
	Data,
	ChildType extends IRandomVariable<Data>&IIRandomVariableWithProbability
>
extends Iterable<ChildType>
{
	Set<ChildType> getSubVariables();
	int size();
}
