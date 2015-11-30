/**
 *
 */
package com.ankamagames.dofus.harvey.engine.editors;

import com.ankamagames.dofus.harvey.engine.inetrfaces.parentedwithprobability.IEditableParentedRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.inetrfaces.parenting.IIEditableParentingRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeRandomVariableEditor
<
	Data,
	ChildType extends IEditableParentedRandomVariableWithProbabilityStrategy<Data, ?, ?>,
	Bridged extends IEditableCompositeRandomVariable<Data, ?, ChildType, ?>
>
extends IEditor<Data, Bridged>, IRandomVariableEditor<Data>, IIEditableParentingRandomVariable<Data, ChildType>
{}