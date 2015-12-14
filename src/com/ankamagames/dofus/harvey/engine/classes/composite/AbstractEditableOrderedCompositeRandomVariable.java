/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.inetrfaces.composite.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractEditableOrderedCompositeRandomVariable
<
	Data,
	ChildType extends BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends BaseOrderedCompositeRandomVariable<Data, ChildType>
implements IEditableCompositeRandomVariable<Data, IEditableOrderedRandomVariable<Data>, ProbabilityStrategiesEnum>
{
	protected AbstractEditableOrderedCompositeRandomVariable(final Collection<? extends ChildType> elements)
	{
		super(elements);
	}

	public AbstractEditableOrderedCompositeRandomVariable()
	{
		super();
	}

	protected abstract BridgedAbstractOrderedCompositeRandomVariableEditor<Data, ChildType, ProbabilityStrategiesEnum, ?> getEditor();

	@Override
	public void add(final IEditableOrderedRandomVariable<Data> randomVariable, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		getEditor().add(randomVariable, probability, probabilityStrategy);
	}

	@Override
	public boolean remove(final IEditableOrderedRandomVariable<Data> randomVariable)
	{
		return getEditor().remove(randomVariable);
	}

	@Override
	public void add(@Nullable final Data value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		getEditor().add(value, probability, probabilityStrategy);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#add(java.lang.Object, int)
	 */
	@Override
	public boolean add(final @Nullable Data value, final int probability)
	{
		return getEditor().add(value, probability);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final @Nullable Data value)
	{
		return getEditor().remove(value);
	}

	@Override
	public boolean containsOnly(@Nullable final Data value)
	{
		return getEditor().containsOnly(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final @Nullable Data value, final int probability)
	{
		return getEditor().setProbabilityOf(value, probability);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final @Nullable Data value, final int delta)
	{
		return getEditor().addProbabilityTo(value, delta);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(final @Nullable Data value, final int delta)
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