/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.inetrfaces.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.generic.interfaces.IEditableGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableCompositeGenericRandomVariable
<
	Data,
	WrappableRandomVariableType extends IEditableGenericRandomVariable<Data>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends IEditableGenericRandomVariable<Data>,
IEditableCompositeRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>,
IIEditableCompositeGenericRandomVariable<Data, WrappableRandomVariableType, ProbabilityStrategiesEnum>
{}