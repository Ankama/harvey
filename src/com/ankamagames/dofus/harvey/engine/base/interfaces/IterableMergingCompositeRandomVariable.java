/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.composite.IMergingCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IMergeableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IterableMergingCompositeRandomVariable<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, ChildType extends IMergeableRandomVariable<Data, ?>>
extends IMergingCompositeRandomVariable<Data, ParentType, ChildType>,
		IterableEditableCompositeRandomVariable<Data, ParentType, ChildType>
{

}
