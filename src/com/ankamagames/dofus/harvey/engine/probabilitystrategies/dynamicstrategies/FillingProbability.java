/**
 *
 */
package com.ankamagames.dofus.harvey.engine.probabilitystrategies.dynamicstrategies;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IMergeableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FillingProbability<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>&Iterable<? extends IRandomVariableWithProbabilityStrategy<Data, ?, ?>>>
	implements IDynamicProbabilityStrategy, IMergeableProbabilityStrategy
{
	protected @Nullable IRandomVariable<Data, ParentType> _set;

	public FillingProbability(final @Nullable IRandomVariable<Data, ParentType> set)
	{
		_set = set;
	}

	public FillingProbability()
	{
		_set = null;
	}

	public void init(final @Nullable IRandomVariable<Data, ParentType> set)
	{
		_set = set;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.probabilitystrategy.IProbabilityStrategy#getProbability()
	 */
	@Override
	public int getProbability()
	{
		final IRandomVariable<Data, ParentType> set = _set;
		if(set==null)
			return 0;
		final ParentType parent = set.getParent();
		if(parent == null)
			return RandomVariableUtils.ONE;

		int nbFill = 0;
		long cumuledProba = 0;
		for(final IRandomVariableWithProbabilityStrategy<Data, ?, ?> bro:parent)
		{
			if(bro.getProbabilityStrategy() instanceof FillingProbability)
			{
				nbFill++;
			}
			else
				cumuledProba += bro.getProbability(parent);
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
