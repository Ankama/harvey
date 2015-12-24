/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.inetrfaces.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IIEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.IEditableLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIEditableCompositeLongRandomVariable
<
	WrappableRandomVariableType extends IEditableLongRandomVariable,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends IIEditableCompositeRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
{
	void add(long value, int probability, ProbabilityStrategiesEnum probabilityStrategy);
}