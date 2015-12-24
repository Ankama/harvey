/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.numeric.floats.inetrfaces.composite.IEditableCompositeFloatRandomVariable;
import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IEditableFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractEditableUnorderedCompositeFloatRandomVariable
<
	ChildType extends BaseEditableFloatRandomVariableWrapper<?, ?, ?>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends BaseUnorderedCompositeFloatRandomVariable<ChildType>
implements IEditableCompositeFloatRandomVariable<IEditableFloatRandomVariable, ProbabilityStrategiesEnum>
{
	protected AbstractEditableUnorderedCompositeFloatRandomVariable(final Collection<? extends ChildType> elements)
	{
		super(elements);
	}

	public AbstractEditableUnorderedCompositeFloatRandomVariable()
	{
		super();
	}

	protected abstract AbstractBridgedCompositeFloatRandomVariableEditor<IEditableFloatRandomVariable, ChildType, ProbabilityStrategiesEnum, ?> getEditor();

	@Override
	public void add(final IEditableFloatRandomVariable randomVariable, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		getEditor().add(randomVariable, probability, probabilityStrategy);
	}

	@Override
	public boolean remove(final IEditableFloatRandomVariable randomVariable)
	{
		return getEditor().remove(randomVariable);
	}

	@Override
	public void add(final float value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		getEditor().add(value, probability, probabilityStrategy);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final float value)
	{
		return getEditor().remove(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final float value, final int probability)
	{
		return getEditor().setProbabilityOf(value, probability);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final float value, final int delta)
	{
		return getEditor().addProbabilityTo(value, delta);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(final float value, final int delta)
	{
		return getEditor().removeProbabilityTo(value, delta);
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