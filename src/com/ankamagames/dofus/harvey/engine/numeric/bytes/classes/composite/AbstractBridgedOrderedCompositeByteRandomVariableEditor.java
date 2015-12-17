/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IEditableOrderedByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedOrderedCompositeByteRandomVariableEditor
<
	ChildType extends BaseEditableOrderedByteRandomVariableWrapper<?, ?, ?>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>,
	Bridged extends AbstractEditableOrderedCompositeByteRandomVariable<ChildType, ProbabilityStrategiesEnum>
>
extends AbstractBridgedCompositeByteRandomVariableEditor<IEditableOrderedByteRandomVariable, ChildType, ProbabilityStrategiesEnum, Bridged>
{
	public AbstractBridgedOrderedCompositeByteRandomVariableEditor(final Bridged bridged)
	{
		super(bridged);
	}
}