/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.AbstractBridgedCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.numeric.integers.inetrfaces.IIEditableIntRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.integers.inetrfaces.composite.IEditableCompositeIntRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.integers.inetrfaces.composite.IIEditableCompositeIntRandomVariable;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IEditableIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedCompositeIntRandomVariableEditor
<
	WrappableRandomVariableType extends IEditableIntRandomVariable,
	ChildType extends BaseIntRandomVariableWrapper<?, ?, ?>&IEditableIntRandomVariable,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>,
	Bridged extends AbstractCompositeIntRandomVariable<ChildType>&IEditableCompositeIntRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
>
extends AbstractBridgedCompositeRandomVariableEditor<WrappableRandomVariableType, ChildType, ProbabilityStrategiesEnum, Bridged>
implements IIEditableIntRandomVariable,IIEditableCompositeIntRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
{
	public AbstractBridgedCompositeIntRandomVariableEditor(final Bridged bridged, final ProbabilityStrategiesEnum defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	protected abstract ChildType getNewChild(final int value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy);

	@Override
	public void add(final int value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		_bridged.getElements().add(getNewChild(value, probability, probabilityStrategy));
	}

	@Override
	public boolean remove(final WrappableRandomVariableType randomVariable)
	{
		boolean r = false;
		final Iterator<ChildType> it = _bridged.getElements().iterator();
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
	public boolean setProbabilityOf(final int value, final int probability)
	{
		final Collection<ChildType> elements = _bridged.getElements();
		final ArrayList<ChildType> containing = new ArrayList<ChildType>(elements.size());
		for(final ChildType element:elements)
		{
			if(element.contains(value))
				containing.add(element);
		}
		final int nbContaining = containing.size();
		if(nbContaining>1)
		{
			boolean r = false;
			final int diviedProbability = probability/nbContaining;
			int remain = probability-diviedProbability*nbContaining;

			for(final ChildType item:containing)
			{
				r |= item.setProbabilityOf(value, (remain-->0)?diviedProbability+RandomVariableUtils.SMALLEST:diviedProbability);
			}
			return r;
		}

		if(nbContaining==1)
		{
			return containing.get(0).setProbabilityOf(value, probability);
		}

		_bridged.getElements().add(getNewChild(value, probability, getDefaultProbabilityStrategy()));
		return true;
	}

	@Override
	public boolean remove(final int value)
	{
		boolean r = false;
		final Iterator<ChildType> it = _bridged.getElements().iterator();
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

	@Override
	public boolean addProbabilityTo(final int value, final int delta)
	{
		final Collection<ChildType> elements = _bridged.getElements();
		final ArrayList<ChildType> containing = new ArrayList<ChildType>(elements.size());
		for(final ChildType element:elements)
		{
			if(element.contains(value))
				containing.add(element);
		}
		final int nbContaining = containing.size();
		if(nbContaining>1)
		{
			boolean r = false;
			final int diviedDelta = delta/nbContaining;
			int remain = delta-diviedDelta*nbContaining;

			for(final ChildType item:containing)
			{
				r |= item.addProbabilityTo(value, (remain-->0)?diviedDelta+RandomVariableUtils.SMALLEST:diviedDelta);
			}
			return r;
		}

		if(nbContaining==1)
		{
			return containing.get(0).addProbabilityTo(value, delta);
		}

		_bridged.getElements().add(getNewChild(value, delta, getDefaultProbabilityStrategy()));
		return true;
	}

	@Override
	public boolean removeProbabilityTo(final int value, final int delta)
	{
		return addProbabilityTo(value, -delta);
	}
}