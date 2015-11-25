/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.composite;

import com.ankamagames.dofus.harvey.engine.inetrfaces.composite.IIEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableCompositeRandomVariable
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>,
	ChildType extends IParentedRandomVariable<Data, ?>
>
extends IEditableParentedRandomVariable<Data, ParentType>,
ICompositeRandomVariable<Data, ParentType, ChildType>,
IIEditableCompositeRandomVariable<Data, ChildType>
{}