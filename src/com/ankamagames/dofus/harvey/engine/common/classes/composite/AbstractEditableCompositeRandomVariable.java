/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractEditableCompositeRandomVariable
<
	WrappableBaseCollectionType extends IEditableRandomVariable,
	ChildType extends EditableRandomVariableWrapper<?, ?, ?>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends AbstractCompositeRandomVariable<ChildType>
implements IEditableCompositeRandomVariable<WrappableBaseCollectionType, ProbabilityStrategiesEnum>
{
	protected abstract AbstractBridgedCompositeRandomVariableEditor<WrappableBaseCollectionType, ChildType, ProbabilityStrategiesEnum, ?> getEditor();

	@Override
	public void add(final WrappableBaseCollectionType randomVariable, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		getEditor().add(randomVariable, probability, probabilityStrategy);
	}

	@Override
	public boolean remove(final WrappableBaseCollectionType randomVariable)
	{
		return getEditor().remove(randomVariable);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#clear()
	 */
	@Override
	public void clear()
	{
		getEditor().clear();
	}
}