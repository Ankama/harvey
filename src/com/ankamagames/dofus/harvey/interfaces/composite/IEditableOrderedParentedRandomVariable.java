/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.composite;

import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableOrderedParentedRandomVariable<Data, ParentType extends IRandomVariable<Data>>
	extends IEditableParentedRandomVariable<Data, ParentType>,
	IOrderedParentedRandomVariable<Data, ParentType>
{}