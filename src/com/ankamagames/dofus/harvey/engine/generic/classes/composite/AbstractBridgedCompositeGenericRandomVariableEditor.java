/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
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
	public boolean setProbabilityOf(@Nullable final Data value, final int probability)
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
	public boolean remove(@Nullable final Data value)
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
	public boolean addProbabilityTo(@Nullable final Data value, final int delta)
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
	public boolean removeProbabilityTo(@Nullable final Data value, final int delta)
	{
		return addProbabilityTo(value, -delta);
	}
}