/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import java.util.Iterator;
import java.util.Set;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.classes.parentedwithprobability.ParentedRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.engine.exceptions.WrongContextException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parentedwithprobability.IParentedRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class AbstractCompositeRandomVariable
<
	Data,
	ParentType extends ICompositeRandomVariable<Data, ParentType, ?>,
	HeldType extends IParentingRandomVariable<Data, ChildType>,
	ChildType extends IParentedRandomVariableWithProbability<Data, ?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends ParentedRandomVariableWithProbability<Data, ParentType, HeldType, ProbabilityStrategy>
implements ICompositeRandomVariable<Data, ParentType, ChildType>
{
	public AbstractCompositeRandomVariable(final @Nullable ParentType parent,
			final HeldType heldRandomVariable, final ProbabilityStrategy probabilityStrategy)
	{
		super(parent, heldRandomVariable, probabilityStrategy);
	}

	@Override
	public int getProbability(final @Nullable IRandomVariable<Data> context)
	{
		if(context==this)
			return RandomVariableUtils.ONE;
		if(context != null)
		{
			int probability = getProbability();
			ICompositeRandomVariable<Data, ParentType, ?> curParent = getParent();
			while((!context.equals(curParent))&&(curParent!=null))
			{
				probability = RandomVariableUtils.multiplyFixedPrecision(probability, curParent.getProbability());
				curParent = curParent.getParent();
			}
			if(context.equals(curParent))
				return probability;
			else
				throw new WrongContextException();
		}
		else
		{
			int probability = getProbability();
			ICompositeRandomVariable<Data, ParentType, ?> curParent = getParent();
			while(curParent!=null)
			{
				probability = RandomVariableUtils.multiplyFixedPrecision(probability, curParent.getProbability());
				curParent = curParent.getParent();
			}
		}
		return 0;
	}

	@Override
	public int getProbabilityOf(final @Nullable Data value, final @Nullable IRandomVariable<Data> context)
	{
		return RandomVariableUtils.multiplyFixedPrecision(getProbability(context), _heldRandomVariable.getProbabilityOf(value));
	}

	@Override
	public Set<ChildType> getSubVariables()
	{
		return _heldRandomVariable.getSubVariables();
	}

	@Override
	public int size()
	{
		return _heldRandomVariable.size();
	}

	@Override
	public Iterator<ChildType> iterator()
	{
		return _heldRandomVariable.iterator();
	}
}
