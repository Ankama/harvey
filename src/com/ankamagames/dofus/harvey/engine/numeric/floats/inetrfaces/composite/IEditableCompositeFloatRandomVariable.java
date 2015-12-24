/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.inetrfaces.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IEditableFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableCompositeFloatRandomVariable
<
	WrappableRandomVariableType extends IEditableFloatRandomVariable,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends IEditableFloatRandomVariable,
IEditableCompositeRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>,
IIEditableCompositeFloatRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
{}