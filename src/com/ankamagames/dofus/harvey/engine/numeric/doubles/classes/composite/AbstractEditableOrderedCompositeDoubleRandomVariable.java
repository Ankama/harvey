/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.inetrfaces.composite.IEditableCompositeDoubleRandomVariable;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IEditableOrderedDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractEditableOrderedCompositeDoubleRandomVariable
<
	ChildType extends BaseEditableOrderedDoubleRandomVariableWrapper<?, ?, ?>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends BaseOrderedCompositeDoubleRandomVariable<ChildType>
implements IEditableCompositeDoubleRandomVariable<IEditableOrderedDoubleRandomVariable, ProbabilityStrategiesEnum>
{
	protected AbstractEditableOrderedCompositeDoubleRandomVariable(final Collection<? extends ChildType> elements)
	{
		super(elements);
	}

	public AbstractEditableOrderedCompositeDoubleRandomVariable()
	{
		super();
	}

	protected abstract AbstractBridgedCompositeDoubleRandomVariableEditor<IEditableOrderedDoubleRandomVariable, ChildType, ProbabilityStrategiesEnum, ?> getEditor();

	@Override
	public void add(final IEditableOrderedDoubleRandomVariable randomVariable, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		getEditor().add(randomVariable, probability, probabilityStrategy);
	}

	@Override
	public boolean remove(final IEditableOrderedDoubleRandomVariable randomVariable)
	{
		return getEditor().remove(randomVariable);
	}

	@Override
	public void add(final double value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		getEditor().add(value, probability, probabilityStrategy);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final double value)
	{
		return getEditor().remove(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final double value, final int probability)
	{
		return getEditor().setProbabilityOf(value, probability);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final double value, final int delta)
	{
		return getEditor().addProbabilityTo(value, delta);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(final double value, final int delta)
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