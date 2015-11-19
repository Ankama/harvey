/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours.composite;

import com.ankamagames.dofus.harvey.engine.behaviours.IOrderedRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedCompositeBehaviour<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>, ChildType extends IOrderedRandomVariable<Data, ?>>
	extends IOrderedRandomVariableBehaviour<Data, ParentType>, ICompositeBehaviour<Data, ChildType>
{}
