/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.numeric.longs.inetrfaces.composite.IEditableCompositeLongRandomVariable;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.IEditableOrderedLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractEditableOrderedCompositeLongRandomVariable
<
	ChildType extends BaseEditableOrderedLongRandomVariableWrapper<?, ?, ?>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends BaseOrderedCompositeLongRandomVariable<ChildType>
implements IEditableCompositeLongRandomVariable<IEditableOrderedLongRandomVariable, ProbabilityStrategiesEnum>
{
	protected AbstractEditableOrderedCompositeLongRandomVariable(final Collection<? extends ChildType> elements)
	{
		super(elements);
	}

	public AbstractEditableOrderedCompositeLongRandomVariable()
	{
		super();
	}

	protected abstract AbstractBridgedCompositeLongRandomVariableEditor<IEditableOrderedLongRandomVariable, ChildType, ProbabilityStrategiesEnum, ?> getEditor();

	@Override
	public void add(final IEditableOrderedLongRandomVariable randomVariable, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		getEditor().add(randomVariable, probability, probabilityStrategy);
	}

	@Override
	public boolean remove(final IEditableOrderedLongRandomVariable randomVariable)
	{
		return getEditor().remove(randomVariable);
	}

	@Override
	public void add(final long value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		getEditor().add(value, probability, probabilityStrategy);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final long value)
	{
		return getEditor().remove(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final long value, final int probability)
	{
		return getEditor().setProbabilityOf(value, probability);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final long value, final int delta)
	{
		return getEditor().addProbabilityTo(value, delta);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(final long value, final int delta)
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