/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.AbstractBridgedCompositeRandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.inetrfaces.IIEditableByteRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.inetrfaces.composite.IEditableCompositeByteRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.inetrfaces.composite.IIEditableCompositeByteRandomVariable;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IEditableByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedCompositeByteRandomVariableEditor
<
	WrappableRandomVariableType extends IEditableByteRandomVariable,
	ChildType extends BaseByteRandomVariableWrapper<?, ?, ?>&IEditableByteRandomVariable,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>,
	Bridged extends AbstractCompositeByteRandomVariable<ChildType>&IEditableCompositeByteRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
>
extends AbstractBridgedCompositeRandomVariableEditor<WrappableRandomVariableType, ChildType, ProbabilityStrategiesEnum, Bridged>
implements IIEditableByteRandomVariable,IIEditableCompositeByteRandomVariable<WrappableRandomVariableType, ProbabilityStrategiesEnum>
{
	public AbstractBridgedCompositeByteRandomVariableEditor(final Bridged bridged)
	{
		super(bridged);
	}

	protected abstract ChildType getNewChild(final byte value, final int probability, final @Nullable ProbabilityStrategiesEnum probabilityStrategy);

	@Override
	public boolean containsOnly(final byte value)
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
	public void add(final byte value, final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
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
	public boolean setProbabilityOf(final byte value, final int probability)
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

		return add(value, probability);
	}

	@Override
	public boolean remove(final byte value)
	{
		boolean r = false;
		final Iterator<ChildType> it = _bridged.getElements().iterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
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
	public boolean add(final byte value, final int probability)
	{
		_bridged.getElements().add(getNewChild(value, probability, null));
		return true;
	}

	@Override
	public boolean addProbabilityTo(final byte value, final int delta)
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

		return add(value, delta);
	}

	@Override
	public boolean removeProbabilityTo(final byte value, final int delta)
	{
		return addProbabilityTo(value, -delta);
	}
}