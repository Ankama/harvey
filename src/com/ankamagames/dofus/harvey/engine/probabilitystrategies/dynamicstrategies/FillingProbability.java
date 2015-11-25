/**
 *
 */
package com.ankamagames.dofus.harvey.engine.probabilitystrategies.dynamicstrategies;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.inetrfaces.composite.IParentedRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IMergeableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.composite.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.IParentedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FillingProbability<Data, ParentType extends ICompositeRandomVariable<Data, ?, ? extends IParentedRandomVariableWithProbabilityStrategy<Data, ?, ?>>>
	implements IDynamicProbabilityStrategy, IMergeableProbabilityStrategy
{
	protected @Nullable IParentedRandomVariable<Data, ParentType> _set;

	public FillingProbability(final @Nullable IParentedRandomVariable<Data, ParentType> set)
	{
		_set = set;
	}

	public FillingProbability()
	{
		_set = null;
	}

	public void init(final @Nullable IParentedRandomVariable<Data, ParentType> set)
	{
		_set = set;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.probabilitystrategy.IProbabilityStrategy#getProbability()
	 */
	@Override
	public int getProbability()
	{
		final IParentedRandomVariable<Data, ParentType> set = _set;
		if(set==null)
			return 0;
		final ParentType parent = set.getParent();
		if(parent == null)
			return RandomVariableUtils.ONE;

		int nbFill = 0;
		long cumuledProba = 0;
		for(final  IParentedRandomVariableWithProbabilityStrategy<Data, ?, ?> bro:parent)
		{
			if(bro.getProbabilityStrategy() instanceof FillingProbability)
			{
				nbFill++;
			}
			else
				cumuledProba += bro.getProbability();
		}
		return (int) ((RandomVariableUtils.ONE-cumuledProba)/nbFill);
	}

	@Override
	public boolean merge(final IProbabilityStrategy other)
	{
		if(!(other instanceof FillingProbability))
			return false;
		return true;
	}

}
