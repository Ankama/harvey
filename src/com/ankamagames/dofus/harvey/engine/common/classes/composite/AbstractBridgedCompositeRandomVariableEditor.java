/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IIEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IIEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedCompositeRandomVariableEditor
<
	WrappableBaseCollectionType extends IRandomVariable,
	ChildType extends RandomVariableWrapper<?, ?, ?>&IEditableRandomVariable,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>,
	Bridged extends AbstractCompositeRandomVariable<ChildType>&IEditableCompositeRandomVariable<WrappableBaseCollectionType, ProbabilityStrategiesEnum>
>
implements IIEditableRandomVariable,IIEditableCompositeRandomVariable<WrappableBaseCollectionType, ProbabilityStrategiesEnum>
{
	protected Bridged _bridged;
	protected ProbabilityStrategiesEnum _defaultProbabilityStrategy;

	public AbstractBridgedCompositeRandomVariableEditor(final Bridged bridged, final ProbabilityStrategiesEnum defaultProbabilityStrategy)
	{
		_bridged = bridged;
		_defaultProbabilityStrategy = defaultProbabilityStrategy;
	}

	protected ProbabilityStrategiesEnum getDefaultProbabilityStrategy()
	{
		return _defaultProbabilityStrategy;
	}

	protected abstract ChildType getNewChild(final WrappableBaseCollectionType randomVariable, final int probability, final ProbabilityStrategiesEnum probabilityStrategy);

	@Override
	public void add(final WrappableBaseCollectionType randomVariable,
			final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		_bridged.getOtherElements().add(getNewChild(randomVariable, probability, probabilityStrategy));
	}

	@Override
	public boolean remove(final WrappableBaseCollectionType randomVariable)
	{
		boolean r = false;
		final Iterator<ChildType> it = _bridged.childIterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			if(element.getElement().equals(randomVariable))
			{
				it.remove();
				r = true;
			}
		}
		return r;
	}

	@Override
	public void clear()
	{
		_bridged.getDefaultElements().clear();
		_bridged.getOtherElements().clear();
	}
}