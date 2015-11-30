/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.parented;

import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedParentedRandomVariable<Data, ParentType extends IParentingRandomVariable<Data, ?>>
	extends IOrderedRandomVariable<Data>,
	IParentedRandomVariable<Data, ParentType>
{}