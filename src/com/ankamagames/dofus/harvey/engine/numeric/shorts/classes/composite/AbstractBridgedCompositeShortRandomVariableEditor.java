/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.AbstractBridgedCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.inetrfaces.IIEditableShortRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.inetrfaces.composite.IEditableCompositeShortRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.inetrfaces.composite.IIEditableCompositeShortRandomVariable;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IEditableShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedCompositeShortRandomVariableEditor
<
	WrappableRandomVariableType extends IEditableShortRandomVariable,
	ChildType extends BaseShortRandomVariableWrapper<?, ?, ?>&IEditableShortRandomVariable,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>,
	Bridged extends AbstractCompositeShortRandomVariable<ChildType>&IEditableCompositeShortRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
>
extends AbstractBridgedCompositeRandomVariableEditor<WrappableRandomVariableType, ChildType, ProbabilityStrategiesEnum, Bridged>
implements IIEditableShortRandomVariable,IIEditableCompositeShortRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
{
	public AbstractBridgedCompositeShortRandomVariableEditor(final Bridged bridged, final ProbabilityStrategiesEnum defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	protected abstract ChildType getNewChild(final short value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy);

	@Override
	public void add(final short value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
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
	public boolean setProbabilityOf(final short value, final int probability)
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
	public boolean remove(final short value)
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
	public boolean addProbabilityTo(final short value, final int delta)
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
	public boolean removeProbabilityTo(final short value, final int delta)
	{
		return addProbabilityTo(value, -delta);
	}
}