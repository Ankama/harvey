/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.interfaces.IEditableOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class BridgedAbstractOrderedCompositeRandomVariableEditor
<
	Data,
	ChildType extends BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>,
	Bridged extends AbstractEditableOrderedCompositeRandomVariable<Data, ChildType, ProbabilityStrategiesEnum>
>
extends BridgedAbstractCompositeRandomVariableEditor<Data, IEditableOrderedRandomVariable<Data>, ChildType, ProbabilityStrategiesEnum, Bridged>
{
	public BridgedAbstractOrderedCompositeRandomVariableEditor(final Bridged bridged)
	{
		super(bridged);
	}
}