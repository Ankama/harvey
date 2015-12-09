/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.inetrfaces.composite.IIEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class BridgedAbstractCompositeRandomVariableEditor
<
	Data,
	ChildType extends BaseEditableRandomVariableWrapper<Data, ?, ?, ?>,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>,
	Bridged extends AbstractEditableUnorderedCompositeRandomVariable<Data, ChildType, ProbabilityStrategiesEnum>
>
implements IIEditableRandomVariable<Data>,IIEditableCompositeRandomVariable<Data, ProbabilityStrategiesEnum>
{
	protected Bridged _bridged;

	public BridgedAbstractCompositeRandomVariableEditor(final Bridged bridged)
	{
		_bridged = bridged;
	}

	protected abstract ChildType getNewChild(final @Nullable Data value, final int probability, final @Nullable ProbabilityStrategiesEnum probabilityStrategy);
	protected abstract ChildType getNewChild(final IEditableRandomVariable<Data> randomVariable, final int probability, final @Nullable ProbabilityStrategiesEnum probabilityStrategy);

	@Override
	public boolean containsOnly(@Nullable final Data value)
	{
		boolean contains = false;
		for(final ChildType element:_bridged.getElements())
		{
			if(element.contains(value))
			{
				contains = true;
				if(!element.containsOnly(value))
					return false;
			}
			else if(!element.isEmpty())
				return false;
		}
		return contains;
	}

	@Override
	public void add(final IEditableRandomVariable<Data> randomVariable,
			final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		_bridged.getElements().add(getNewChild(randomVariable, probability, probabilityStrategy));
	}

	@Override
	public void add(@Nullable final Data value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		_bridged.getElements().add(getNewChild(value, probability, probabilityStrategy));
	}

	@Override
	public boolean remove(final IEditableRandomVariable<Data> randomVariable)
	{
		boolean r = false;
		final Iterator<ChildType> it = _bridged.getElements().iterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			if(element._element.equals(randomVariable))
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
		final HashSet<? extends BaseEditableRandomVariableWrapper<Data, ?, ?, ?>> elements = _bridged.getElements();
		final ArrayList<BaseEditableRandomVariableWrapper<Data, ?, ?, ?>> containing = new ArrayList<BaseEditableRandomVariableWrapper<Data,?,?,?>>(elements.size());
		for(final BaseEditableRandomVariableWrapper<Data, ?, ?, ?> element:elements)
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

			for(final BaseEditableRandomVariableWrapper<Data, ?, ?, ?> item:containing)
			{
				r |= item.setProbabilityOf(value, (remain-->0)?diviedProbability+RandomVariableUtils.SMALLEST:diviedProbability);
			}
			return r;
		}

		if(nbContaining==1)
		{
			return containing.get(0).setProbabilityOf(value, probability);
		}

		return add(value, probability);
	}

	@Override
	public boolean remove(@Nullable final Data value)
	{
		boolean r = false;
		final Iterator<? extends BaseEditableRandomVariableWrapper<Data, ?, ?, ?>> it = _bridged.getElements().iterator();
		while(it.hasNext())
		{
			final BaseEditableRandomVariableWrapper<Data, ?, ?, ?> element = it.next();
			if(element.remove(value))
			{
				r = true;
				if(element.isEmpty())
				{
					it.remove();
				}
			}
		}
		return r;
	}

	@Override
	public boolean add(final @Nullable Data value, final int probability)
	{
		_bridged.getElements().add(getNewChild(value, probability, null));
		return true;
	}

	@Override
	public boolean addProbabilityTo(@Nullable final Data value, final int delta)
	{
		final HashSet<? extends BaseEditableRandomVariableWrapper<Data, ?, ?, ?>> elements = _bridged.getElements();
		final ArrayList<BaseEditableRandomVariableWrapper<Data, ?, ?, ?>> containing = new ArrayList<BaseEditableRandomVariableWrapper<Data,?,?,?>>(elements.size());
		for(final BaseEditableRandomVariableWrapper<Data, ?, ?, ?> element:elements)
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

			for(final BaseEditableRandomVariableWrapper<Data, ?, ?, ?> item:containing)
			{
				r |= item.addProbabilityTo(value, (remain-->0)?diviedDelta+RandomVariableUtils.SMALLEST:diviedDelta);
			}
			return r;
		}

		if(nbContaining==1)
		{
			return containing.get(0).addProbabilityTo(value, delta);
		}

		return add(value, delta);
	}

	@Override
	public boolean removeProbabilityTo(@Nullable final Data value, final int delta)
	{
		return addProbabilityTo(value, -delta);
	}

	@Override
	public void clear()
	{
		_bridged.getElements().clear();
	}
}