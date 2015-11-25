/**
 *
 */
package com.ankamagames.dofus.harvey.engine.editors;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.inetrfaces.IIRandomVariableDecorator;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.IEditableParentedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CompositeRandomVariableEditor
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>,
	ChildType extends IEditableParentedRandomVariable<Data, Bridged>,
	Bridged extends IEditableCompositeRandomVariable<Data, ParentType, ChildType>&IIRandomVariableDecorator<Data, ? extends IEditableRandomVariable<Data>>
>
extends ParentedRandomVariableEditor<Data, ParentType, Bridged>
implements ICompositeRandomVariableEditor<Data, ChildType>
{
	public CompositeRandomVariableEditor(final Bridged bridged)
	{
		super(bridged);
	}

	protected void _checkProbability() throws OverOneProbabilityException
	{
		long sum = 0;
		for(final ChildType subVariable:_bridged.getSubVariables())
		{
			sum += subVariable.getProbability();
		}
		if(sum > RandomVariableUtils.ONE)
			throw new OverOneProbabilityException();
	}

	protected void addSubVariableNoCheck(final ChildType subVariable)
	{
		subVariable.setParent(_bridged);
		_bridged.getSubVariables().add(subVariable);
	}

	@Override
	public void addSubVariable(final ChildType subVariable) throws OverOneProbabilityException
	{
		final Bridged formerParent = subVariable.getParent();
		addSubVariableNoCheck(subVariable);
		try
		{
			_checkProbability();
		}
		catch(final OverOneProbabilityException e)
		{
			subVariable.setParent(formerParent);
			throw e;
		}
	}

	public void addSubVariablesNoCheck(final Collection<ChildType> subVariables)
	{
		for(final ChildType subVariable:subVariables)
		{
			subVariable.setParent(_bridged);
		}
		_bridged.getSubVariables().addAll(subVariables);
	}

	@Override
	public void addSubVariables(final Collection<ChildType> subVariables) throws OverOneProbabilityException
	{
		final HashMap<ChildType, Bridged> formerParents = new HashMap<ChildType, Bridged>(subVariables.size());
		for(final ChildType subVariable:subVariables)
		{
			formerParents.put(subVariable, subVariable.getParent());
			subVariable.setParent(_bridged);
		}
		_bridged.getSubVariables().addAll(subVariables);
		try
		{
			_checkProbability();
		}
		catch(final OverOneProbabilityException e)
		{
			final Iterator<Entry<ChildType, Bridged>> iterator = formerParents.entrySet().iterator();
			while(iterator.hasNext())
			{
				final Entry<ChildType, Bridged> entry = iterator.next();
				entry.getKey().setParent(entry.getValue());
			}
			throw e;
		}
	}

	@Override
	public void removeSubVariable(final ChildType subVariable)
	{
		_bridged.getSubVariables().remove(subVariable);
		subVariable.setParent(null);
	}

	@Override
	public void removeSubVariables(final Collection<ChildType> subVariables)
	{
		_bridged.getSubVariables().removeAll(subVariables);
		for(final ChildType subVariable:subVariables)
			subVariable.setParent(null);
	}

	@Override
	public void retainSubVariables(final Collection<ChildType> subVariables)
	{
		final Iterator<ChildType> iterator = _bridged.iterator();
		while(iterator.hasNext())
		{
			final ChildType item = iterator.next();
			if(!subVariables.contains(item))
			{
				iterator.remove();
				item.setParent(null);
			}
		}
	}

	@Override
	public void clear()
	{
		for(final ChildType subVariable:_bridged.getSubVariables())
			subVariable.setParent(null);
		_bridged.getSubVariables().clear();
	}
}
