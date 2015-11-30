/**
 *
 */
package com.ankamagames.dofus.harvey.engine.editors;

import com.ankamagames.dofus.harvey.engine.inetrfaces.parenting.IIEditableParentingRandomVariable;
import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IIRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parenting.IEditableParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IParentingRandomVariableEditor
<
	Data,
	ChildType extends IRandomVariable<Data>&IIRandomVariableWithProbability,
	Bridged extends IEditableParentingRandomVariable<Data, ChildType>
>
extends IEditor<Data, Bridged>,
IIEditableParentingRandomVariable<Data, ChildType>
{}