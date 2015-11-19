package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IEditableRandomVariableWithProbabilityStrategy<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, ProbabilityStrategy extends IProbabilityStrategy>
	extends IEditableRandomVariable<Data, ParentType>, IRandomVariableWithProbabilityStrategy<Data, ParentType, ProbabilityStrategy>
{
}
