/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.inetrfaces.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IIEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IEditableShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIEditableCompositeShortRandomVariable
<
	WrappableRandomVariableType extends IEditableShortRandomVariable,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends IIEditableCompositeRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
{
	void add(short value, int probability, ProbabilityStrategiesEnum probabilityStrategy);
}