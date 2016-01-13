/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.inetrfaces.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IIEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIEditableCompositeDoubleRandomVariable
<
	WrappableRandomVariableType extends IDoubleRandomVariable,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends IIEditableCompositeRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
{
	void add(double value, int probability, ProbabilityStrategiesEnum probabilityStrategy);
}