/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.AbstractBridgedCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.numeric.longs.inetrfaces.IIEditableLongRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.longs.inetrfaces.composite.IEditableCompositeLongRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.longs.inetrfaces.composite.IIEditableCompositeLongRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.ILongRandomVariable;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.IEditableLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedCompositeLongRandomVariableEditor
<
	WrappableRandomVariableType extends ILongRandomVariable,
	ChildType extends BaseLongRandomVariableWrapper<?, ?, ? extends IEditableProbabilityStrategy>&IEditableLongRandomVariable,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>,
	Bridged extends AbstractCompositeLongRandomVariable<ChildType>&IEditableCompositeLongRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
>
extends AbstractBridgedCompositeRandomVariableEditor<WrappableRandomVariableType, ChildType, ProbabilityStrategiesEnum, Bridged>
implements IIEditableLongRandomVariable,IIEditableCompositeLongRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
{
	public AbstractBridgedCompositeLongRandomVariableEditor(final Bridged bridged, final ProbabilityStrategiesEnum defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	protected abstract ChildType getNewChild(final long value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy);

	@Override
	public void add(final long value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		if(probabilityStrategy==getDefaultProbabilityStrategy())
			_bridged.getDefaultElements().add(getNewChild(value, probability, probabilityStrategy));
		else
			_bridged.getOtherElements().add(getNewChild(value, probability, probabilityStrategy));
	}

	@Override
	public boolean remove(final WrappableRandomVariableType randomVariable)
	{
		boolean r = false;
		final Iterator<ChildType> it = _bridged.iterator();
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
	public boolean setProbabilityOf(final long value, final int probability)
	{
		long cumulatedProbability = 0;
		for(final ChildType element:_bridged.getOtherElements())
		{
			if(element.contains(value))
				cumulatedProbability += element.getProbabilityOf(value);
		}

		final int newProba = (int)(probability-cumulatedProbability);
		final Collection<ChildType> defaultElements = _bridged.getDefaultElements();
		for(final ChildType element:defaultElements)
		{
			if(element.contains(value))
			{
				return element.setProbabilityOf(value, newProba);
			}
		}

		defaultElements.add(getNewChild(value, newProba, getDefaultProbabilityStrategy()));
		return true;
	}

	@Override
	public boolean addProbabilityTo(final long value, final int delta)
	{
		final Collection<ChildType> elements = _bridged.getDefaultElements();
		for(final ChildType element:elements)
		{
			if(element.contains(value))
				return element.addProbabilityTo(value, delta);
		}

		_bridged.getDefaultElements().add(getNewChild(value, delta, getDefaultProbabilityStrategy()));
		return true;
	}

	@Override
	public boolean removeProbabilityTo(final long value, final int delta)
	{
		return addProbabilityTo(value, -delta);
	}

	@Override
	public boolean remove(final long value)
	{
		boolean r = false;
		final Iterator<ChildType> it = _bridged.iterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			if(element.remove(value))
			{
				r = true;
				if(element.isUnknown())
				{
					it.remove();
				}
			}
		}
		return r;
	}
}