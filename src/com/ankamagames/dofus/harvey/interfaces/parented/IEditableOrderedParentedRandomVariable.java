/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.parented;

import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableOrderedParentedRandomVariable<Data, ParentType extends IParentingRandomVariable<Data, ?>>
extends IEditableParentedRandomVariable<Data, ParentType>,
IOrderedParentedRandomVariable<Data, ParentType>
{}