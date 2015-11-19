package com.ankamagames.dofus.harvey.engine.mergestrategies;

import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IMergeStrategy<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>>
{
	/**
	 *
	 * @param other
	 * @return true if other was successfully merged, false otherwise
	 */
	boolean merge(IEditableRandomVariable<Data, ParentType> other);
}
