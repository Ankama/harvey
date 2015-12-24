/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.inetrfaces.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.IEditableLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableCompositeLongRandomVariable
<
	WrappableRandomVariableType extends IEditableLongRandomVariable,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends IEditableLongRandomVariable,
IEditableCompositeRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>,
IIEditableCompositeLongRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
{}