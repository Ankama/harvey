/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.inetrfaces.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IEditableDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableCompositeDoubleRandomVariable
<
	WrappableRandomVariableType extends IEditableDoubleRandomVariable,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends IEditableDoubleRandomVariable,
IEditableCompositeRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>,
IIEditableCompositeDoubleRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
{}