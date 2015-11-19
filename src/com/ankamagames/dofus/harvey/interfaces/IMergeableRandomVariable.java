/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces;

import com.ankamagames.dofus.harvey.engine.mergestrategies.IMergeStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IMergeableRandomVariable<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>>
	extends IEditableRandomVariable<Data, ParentType>, IMergeStrategy<Data, ParentType>
{}
