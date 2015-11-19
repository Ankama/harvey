package com.ankamagames.dofus.harvey.engine.editors;

import java.util.Collection;
import java.util.HashMap;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.base.classes.BaseRandomVariable;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class CompositeRandomVariableEditor
<
Data,
ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>,
ChildType extends IEditableRandomVariable<Data, ?>,
BridgedType extends BaseRandomVariable<Data, ParentType, ?, ?>&IEditableCompositeRandomVariable<Data, ParentType, ChildType>&IEditableRandomVariableWithEditor<Data, ParentType, ?>
>
extends	RandomVariableEditor<Data, ParentType, BridgedType>
implements ICompositeRandomVariableEditor<Data, ParentType, ChildType>
{
	public CompositeRandomVariableEditor(
			final BridgedType abstractCopositeEditableRandomVariable)
	{
		super(abstractCopositeEditableRandomVariable);
	}

	public CompositeRandomVariableEditor(
			final BridgedType abstractCompositeEditableRandomVariable,
			final @Nullable ParentType parent) throws OverOneProbabilityException
	{
		super(abstractCompositeEditableRandomVariable, parent);
	}

	@Override
	public void checkConsistency() throws OverOneProbabilityException
	{
		int proba = 0;
		for(final IEditableRandomVariable<Data, ?> subValue:_bridged.getSubVariables())
		{
			proba+=subValue.getProbability(_bridged);
		}
		if(proba>RandomVariableUtils.ONE)
		{
			throw new OverOneProbabilityException();
		}
	}

	@Override
	public void addElement(final ChildType newChild)
			throws OverOneProbabilityException
	{
		_bridged.getSubVariables().add(newChild);
		final IEditableCompositeRandomVariable<Data, ?, ?> formerParent = newChild.getParent();
		if((formerParent == null)||( !formerParent.equals(_bridged)))
		{
			@SuppressWarnings( "unchecked" )
			final
			IEditableRandomVariable<Data, IEditableCompositeRandomVariable<Data, ?, ?>> cast = (IEditableRandomVariable<Data, IEditableCompositeRandomVariable<Data, ?, ?>>)newChild;
			cast.setParent(_bridged);
		}
		try
		{
			checkConsistency();
		}
		catch(final OverOneProbabilityException e)
		{
			_bridged.getSubVariables().remove(newChild);
			if((formerParent==null)||(!formerParent.equals(_bridged)))
			{
				@SuppressWarnings( "unchecked" )
				final
				IEditableRandomVariable<Data, IEditableCompositeRandomVariable<Data, ?, ?>> cast = (IEditableRandomVariable<Data, IEditableCompositeRandomVariable<Data, ?, ?>>)newChild;
				cast.setParent(formerParent);
			}
			throw e;
		}
	}

	@Override
	public void addElements(final ChildType... newChilds)
			throws OverOneProbabilityException
	{
		//        ATTENTION ! DUPLICATE METHOD (void addElements(final Collection<ChildType> newChilds) ci-dessous)
		final HashMap<ChildType, IEditableCompositeRandomVariable<Data, ?, ?>> formerParents = new HashMap<ChildType, IEditableCompositeRandomVariable<Data, ?, ?>>();
		for(final ChildType newChild:newChilds)
		{
			_bridged.getSubVariables().add(newChild);
			final IEditableCompositeRandomVariable<Data, ?, ?> formerParent = newChild.getParent();
			if((formerParent == null)||( !formerParent.equals(_bridged)))
			{
				formerParents.put(newChild, formerParent);
				@SuppressWarnings( "unchecked" )
				final IEditableRandomVariable<Data, IEditableCompositeRandomVariable<Data, ?, ?>> cast = (IEditableRandomVariable<Data, IEditableCompositeRandomVariable<Data, ?, ?>>)newChild;
				cast.setParent(_bridged);
			}
		}
		try
		{
			checkConsistency();
		}
		catch(final OverOneProbabilityException e)
		{
			for(final ChildType newChild:newChilds)
			{
				_bridged.getSubVariables().remove(newChild);
				IEditableCompositeRandomVariable<Data, ?, ?> formerParent;
				if((formerParent = formerParents.get(newChild))!=null)
				{
					@SuppressWarnings( "unchecked" )
					final IEditableRandomVariable<Data, IEditableCompositeRandomVariable<Data, ?, ?>> cast = (IEditableRandomVariable<Data, IEditableCompositeRandomVariable<Data, ?, ?>>)newChild;
					cast.setParent(formerParent);
				}
			}
			throw e;
		}
	}

	@Override
	public void addElements(final Collection<ChildType> newChilds)
			throws OverOneProbabilityException
	{
		//        ATTENTION ! DUPLICATE METHOD (void addElements(final ChildType... newChilds) ci-dessus)
		final HashMap<ChildType, IEditableCompositeRandomVariable<Data, ?, ?>> formerParents = new HashMap<ChildType, IEditableCompositeRandomVariable<Data, ?, ?>>();
		for(final ChildType newChild:newChilds)
		{
			_bridged.getSubVariables().add(newChild);
			final IEditableCompositeRandomVariable<Data, ?, ?> formerParent = newChild.getParent();
			if((formerParent == null)||( !formerParent.equals(_bridged)))
			{
				formerParents.put(newChild, formerParent);
				@SuppressWarnings( "unchecked" )
				final IEditableRandomVariable<Data, IEditableCompositeRandomVariable<Data, ?, ?>> cast = (IEditableRandomVariable<Data, IEditableCompositeRandomVariable<Data, ?, ?>>)newChild;
				cast.setParent(_bridged);
			}
		}
		try
		{
			checkConsistency();
		}
		catch(final OverOneProbabilityException e)
		{
			for(final ChildType newChild:newChilds)
			{
				_bridged.getSubVariables().remove(newChild);
				IEditableCompositeRandomVariable<Data, ?, ?> formerParent;
				if((formerParent = formerParents.get(newChild))!=null)
				{
					@SuppressWarnings( "unchecked" )
					final IEditableRandomVariable<Data, IEditableCompositeRandomVariable<Data, ?, ?>> cast = (IEditableRandomVariable<Data, IEditableCompositeRandomVariable<Data, ?, ?>>)newChild;
					cast.setParent(formerParent);
				}
			}
			throw e;
		}
	}
}
