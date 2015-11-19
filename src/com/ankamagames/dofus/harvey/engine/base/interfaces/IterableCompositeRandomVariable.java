/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IterableCompositeRandomVariable<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>, ChildType extends IRandomVariable<Data, ?>>
	extends ICompositeRandomVariable<Data, ParentType, ChildType>,
	Iterable<ChildType>
{

}
