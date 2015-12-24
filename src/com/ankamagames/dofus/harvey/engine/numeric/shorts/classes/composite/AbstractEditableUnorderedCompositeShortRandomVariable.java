/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.inetrfaces.composite.IEditableCompositeShortRandomVariable;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IEditableShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractEditableUnorderedCompositeShortRandomVariable
<
	ChildType extends BaseEditableShortRandomVariableWrapper<?, ?, ?>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
extends BaseUnorderedCompositeShortRandomVariable<ChildType>
implements IEditableCompositeShortRandomVariable<IEditableShortRandomVariable, ProbabilityStrategiesEnum>
{
	protected AbstractEditableUnorderedCompositeShortRandomVariable(final Collection<? extends ChildType> elements)
	{
		super(elements);
	}

	public AbstractEditableUnorderedCompositeShortRandomVariable()
	{
		super();
	}

	protected abstract AbstractBridgedCompositeShortRandomVariableEditor<IEditableShortRandomVariable, ChildType, ProbabilityStrategiesEnum, ?> getEditor();

	@Override
	public void add(final IEditableShortRandomVariable randomVariable, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		getEditor().add(randomVariable, probability, probabilityStrategy);
	}

	@Override
	public boolean remove(final IEditableShortRandomVariable randomVariable)
	{
		return getEditor().remove(randomVariable);
	}

	@Override
	public void add(final short value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		getEditor().add(value, probability, probabilityStrategy);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final short value)
	{
		return getEditor().remove(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final short value, final int probability)
	{
		return getEditor().setProbabilityOf(value, probability);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final short value, final int delta)
	{
		return getEditor().addProbabilityTo(value, delta);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(final short value, final int delta)
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