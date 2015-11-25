/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IHasProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.IParentedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IParentedRandomVariableWithProbabilityStrategy<Data, ParentType extends IRandomVariable<Data>, ProbabilityStrategy extends IProbabilityStrategy>
	extends IParentedRandomVariable<Data, ParentType>,
	IHasProbabilityStrategy<ProbabilityStrategy>
{

}
