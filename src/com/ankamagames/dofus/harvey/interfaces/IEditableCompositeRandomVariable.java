/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableCompositeRandomVariable<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, ChildType extends IRandomVariable<Data, ?>>
	extends ICompositeRandomVariable<Data, ParentType, ChildType>, IEditableRandomVariable<Data, ParentType>
{
	void checkConsistency() throws OverOneProbabilityException;
	void addElement(ChildType newChild) throws OverOneProbabilityException;
	void addElements(ChildType... newChilds) throws OverOneProbabilityException;
	void addElements(Collection<ChildType> newChilds) throws OverOneProbabilityException;
}
