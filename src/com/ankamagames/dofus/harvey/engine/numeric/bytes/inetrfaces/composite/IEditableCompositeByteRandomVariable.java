/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.inetrfaces.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IEditableByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableCompositeByteRandomVariable
<
	WrappableRandomVariableType extends IEditableByteRandomVariable,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends IEditableByteRandomVariable,
IEditableCompositeRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>,
IIEditableCompositeByteRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
{}