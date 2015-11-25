/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.composite;

import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedParentedRandomVariable<Data, ParentType extends IRandomVariable<Data>>
	extends IParentedRandomVariable<Data, ParentType>,
	IOrderedRandomVariable<Data>
{}