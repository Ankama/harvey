/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.inetrfaces.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IIEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.generic.interfaces.IEditableGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIEditableCompositeGenericRandomVariable
<
	Data,
	WrappableRandomVariableType extends IEditableGenericRandomVariable<Data>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends IIEditableCompositeRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
{
	void add(@Nullable Data value, int probability, ProbabilityStrategiesEnum probabilityStrategy);
}