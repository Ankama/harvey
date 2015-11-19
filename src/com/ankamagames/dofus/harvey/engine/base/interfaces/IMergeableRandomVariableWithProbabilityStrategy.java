/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IMergeableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IMergeableRandomVariableWithProbabilityStrategy<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, ProbabilityStrategy extends IProbabilityStrategy>
extends IMergeableRandomVariable<Data, ParentType>, IEditableRandomVariableWithProbabilityStrategy<Data, ParentType, ProbabilityStrategy>
{

}