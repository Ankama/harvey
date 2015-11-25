/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import java.util.Iterator;
import java.util.Set;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.composite.ParentedRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.WrongContextException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.IParentedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeRandomVariable
<
Data,
ParentType extends ICompositeRandomVariable<Data, ?, ?>,
ChildType extends IParentedRandomVariable<Data, ?>,
ProbabilityStrategy extends IProbabilityStrategy
>
extends ParentedRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>
implements ICompositeRandomVariable<Data, ParentType, ChildType>
{
	public AbstractCompositeRandomVariable(final ChildType heldRandomVariable,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(heldRandomVariable, parent, probabilityStrategy);
	}

	@Override
	public abstract Set<ChildType> getSubVariables();

	@Override
	public Iterator<ChildType> iterator()
	{
		return getSubVariables().iterator();
	}

	@Override
	public int size()
	{
		return getSubVariables().size();
	}

	@Override
	public int getProbability(final @Nullable IRandomVariable<Data> context)
	{
		if(context==this)
			return RandomVariableUtils.ONE;
		if(context != null)
		{
			int probability = getProbability();
			ICompositeRandomVariable<Data, ?, ?> curParent = getParent();
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
			ICompositeRandomVariable<Data, ?, ?> curParent = getParent();
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
}
