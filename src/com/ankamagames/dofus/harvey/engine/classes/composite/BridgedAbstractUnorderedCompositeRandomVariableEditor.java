/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class BridgedAbstractUnorderedCompositeRandomVariableEditor
<
	Data,
	ChildType extends BaseEditableRandomVariableWrapper<Data, ?, ?, ?>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>,
	Bridged extends AbstractEditableUnorderedCompositeRandomVariable<Data, ChildType, ProbabilityStrategiesEnum>
>
extends BridgedAbstractCompositeRandomVariableEditor<Data, IEditableRandomVariable<Data>, ChildType, ProbabilityStrategiesEnum, Bridged>
{
	public BridgedAbstractUnorderedCompositeRandomVariableEditor(final Bridged bridged)
	{
		super(bridged);
	}
}