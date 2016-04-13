/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.AbstractBridgedCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.generic.inetrfaces.IIEditableGenericRandomVariable;
import com.ankamagames.dofus.harvey.engine.generic.inetrfaces.composite.IEditableCompositeGenericRandomVariable;
import com.ankamagames.dofus.harvey.engine.generic.inetrfaces.composite.IIEditableCompositeGenericRandomVariable;
import com.ankamagames.dofus.harvey.generic.interfaces.IEditableGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedCompositeGenericRandomVariableEditor
<
	Data,
	WrappableRandomVariableType extends IEditableGenericRandomVariable<Data>,
	ChildType extends BaseGenericRandomVariableWrapper<Data, ?, ?, ?>&IEditableGenericRandomVariable<Data>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>,
	Bridged extends AbstractCompositeGenericRandomVariable<Data, ChildType>&IEditableCompositeGenericRandomVariable<Data, WrappableRandomVariableType, ProbabilityStrategiesEnum>
>
extends AbstractBridgedCompositeRandomVariableEditor<WrappableRandomVariableType, ChildType, ProbabilityStrategiesEnum, Bridged>
implements IIEditableGenericRandomVariable<Data>,IIEditableCompositeGenericRandomVariable<Data, WrappableRandomVariableType, ProbabilityStrategiesEnum>
{
	public AbstractBridgedCompositeGenericRandomVariableEditor(final Bridged bridged, final ProbabilityStrategiesEnum defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	protected abstract ChildType getNewChild(final @Nullable Data value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy);

	@Override
	public void add(@Nullable final Data value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
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
	public boolean setProbabilityOf(@Nullable final Data value, final int probability)
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
	public boolean addProbabilityTo(@Nullable final Data value, final int delta)
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
	public boolean removeProbabilityTo(@Nullable final Data value, final int delta)
	{
		return addProbabilityTo(value, -delta);
	}

	@Override
	public boolean remove(@Nullable final Data value)
	{
		boolean r = false;
		final Iterator<ChildType> it = _bridged.childIterator();
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