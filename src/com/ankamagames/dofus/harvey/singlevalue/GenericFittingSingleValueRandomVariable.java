/**
 *
 */
package com.ankamagames.dofus.harvey.singlevalue;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.behaviours.singlevalue.ISingleValueBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.dynamicstrategies.FittingProbability;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Holds one value of type Data with associated probability
 *
 * @author sgros
 *
 */
@NonNullByDefault
public class GenericFittingSingleValueRandomVariable<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>&Iterable<? extends IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>>,
		Behaviour extends ISingleValueBehaviour<Data>>
extends MergeableSingleValueRandomVariable<Data, ParentType, Behaviour, FittingProbability<Data, ParentType>>
{
	public GenericFittingSingleValueRandomVariable(final @Nullable ParentType parent, final Behaviour behaviour, final int probability) throws OverOneProbabilityException
	{
		super(parent, behaviour, new FittingProbability<Data, ParentType>(probability));
		_probabilityStrategy.init(this);
	}

	public GenericFittingSingleValueRandomVariable(final Behaviour behaviour, final int probability) throws OverOneProbabilityException
	{
		super(behaviour, new FittingProbability<Data, ParentType>(probability));
		_probabilityStrategy.init(this);
	}

	public GenericFittingSingleValueRandomVariable(final @Nullable ParentType parent, final Behaviour behaviour) throws OverOneProbabilityException
	{
		super(parent, behaviour, new FittingProbability<Data, ParentType>());
		_probabilityStrategy.init(this);
	}

	public GenericFittingSingleValueRandomVariable(final Behaviour behaviour) throws OverOneProbabilityException
	{
		super(behaviour, new FittingProbability<Data, ParentType>());
		_probabilityStrategy.init(this);
	}
}
