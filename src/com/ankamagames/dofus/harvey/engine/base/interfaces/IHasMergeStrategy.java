/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.mergestrategies.IMergeStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IHasMergeStrategy<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, MergeStrategy extends IMergeStrategy<Data, ParentType>>
{
	MergeStrategy getMergeStrategy();
}
