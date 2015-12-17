/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.generic.interfaces.IEditableOrderedGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedOrderedCompositeGenericRandomVariableEditor
<
	Data,
	ChildType extends BaseEditableOrderedGenericRandomVariableWrapper<Data, ?, ?, ?>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>,
	Bridged extends AbstractEditableOrderedCompositeGenericRandomVariable<Data, ChildType, ProbabilityStrategiesEnum>
>
extends AbstractBridgedCompositeGenericRandomVariableEditor<Data, IEditableOrderedGenericRandomVariable<Data>, ChildType, ProbabilityStrategiesEnum, Bridged>
{
	public AbstractBridgedOrderedCompositeGenericRandomVariableEditor(final Bridged bridged)
	{
		super(bridged);
	}
}