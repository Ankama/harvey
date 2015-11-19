/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.mergestrategies.IMergeStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IMergeableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IMergeableRandomVariableWithMergeStrategy<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, MergeStrategy extends IMergeStrategy<Data, ParentType>>
	extends IMergeableRandomVariable<Data, ParentType>, IHasMergeStrategy<Data, ParentType, MergeStrategy>
{}