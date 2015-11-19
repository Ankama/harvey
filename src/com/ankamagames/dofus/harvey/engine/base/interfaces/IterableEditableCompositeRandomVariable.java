/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IterableEditableCompositeRandomVariable<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, ChildType extends IRandomVariable<Data, ?>>
	extends IEditableCompositeRandomVariable<Data, ParentType, ChildType>,
	IterableCompositeRandomVariable<Data, ParentType, ChildType>
{

}
