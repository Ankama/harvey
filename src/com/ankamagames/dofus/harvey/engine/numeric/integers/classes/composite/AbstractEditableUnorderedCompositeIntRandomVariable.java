/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.numeric.integers.inetrfaces.composite.IEditableCompositeIntRandomVariable;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IEditableIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractEditableUnorderedCompositeIntRandomVariable
<
	ChildType extends BaseEditableIntRandomVariableWrapper<?, ?, ?>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends BaseUnorderedCompositeIntRandomVariable<ChildType>
implements IEditableCompositeIntRandomVariable<IEditableIntRandomVariable, ProbabilityStrategiesEnum>
{
	protected AbstractEditableUnorderedCompositeIntRandomVariable(final Collection<? extends ChildType> elements)
	{
		super(elements);
	}

	public AbstractEditableUnorderedCompositeIntRandomVariable()
	{
		super();
	}

	protected abstract AbstractBridgedCompositeIntRandomVariableEditor<IEditableIntRandomVariable, ChildType, ProbabilityStrategiesEnum, ?> getEditor();

	@Override
	public void add(final IEditableIntRandomVariable randomVariable, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		getEditor().add(randomVariable, probability, probabilityStrategy);
	}

	@Override
	public boolean remove(final IEditableIntRandomVariable randomVariable)
	{
		return getEditor().remove(randomVariable);
	}

	@Override
	public void add(final int value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		getEditor().add(value, probability, probabilityStrategy);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final int value)
	{
		return getEditor().remove(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final int value, final int probability)
	{
		return getEditor().setProbabilityOf(value, probability);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final int value, final int delta)
	{
		return getEditor().addProbabilityTo(value, delta);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(final int value, final int delta)
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