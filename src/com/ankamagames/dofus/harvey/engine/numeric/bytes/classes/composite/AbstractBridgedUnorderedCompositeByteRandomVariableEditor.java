/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IEditableByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedUnorderedCompositeByteRandomVariableEditor
<
	ChildType extends BaseEditableByteRandomVariableWrapper<?, ?, ?>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>,
	Bridged extends AbstractEditableUnorderedCompositeByteRandomVariable<ChildType, ProbabilityStrategiesEnum>
>
extends AbstractBridgedCompositeByteRandomVariableEditor<IEditableByteRandomVariable, ChildType, ProbabilityStrategiesEnum, Bridged>
{
	public AbstractBridgedUnorderedCompositeByteRandomVariableEditor(final Bridged bridged)
	{
		super(bridged);
	}
}