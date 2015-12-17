/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.generic.interfaces.IEditableGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedUnorderedCompositeGenericRandomVariableEditor
<
	Data,
	ChildType extends BaseEditableGenericRandomVariableWrapper<Data, ?, ?, ?>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>,
	Bridged extends AbstractEditableUnorderedCompositeGenericRandomVariable<Data, ChildType, ProbabilityStrategiesEnum>
>
extends AbstractBridgedCompositeGenericRandomVariableEditor<Data, IEditableGenericRandomVariable<Data>, ChildType, ProbabilityStrategiesEnum, Bridged>
{
	public AbstractBridgedUnorderedCompositeGenericRandomVariableEditor(final Bridged bridged)
	{
		super(bridged);
	}
}