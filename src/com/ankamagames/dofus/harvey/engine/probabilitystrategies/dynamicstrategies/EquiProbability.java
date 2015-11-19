/**
 *
 */
package com.ankamagames.dofus.harvey.engine.probabilitystrategies.dynamicstrategies;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IMergeableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.IStaticProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.ISingleValueRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class EquiProbability<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>&Iterable<IRandomVariableWithProbabilityStrategy<Data, ?, ?>>>
	implements IDynamicProbabilityStrategy, IMergeableProbabilityStrategy
{
	protected @Nullable ISingleValueRandomVariable<Data, ParentType> _set;

	public EquiProbability(final @Nullable ISingleValueRandomVariable<Data, ParentType> set)
	{
		_set = set;
	}

	public EquiProbability()
	{
		_set = null;
	}

	public void init(final @Nullable ISingleValueRandomVariable<Data, ParentType> set)
	{
		_set = set;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.probabilitystrategy.IProbabilityStrategy#getProbability()
	 */
	@Override
	public int getProbability()
	{
		final ISingleValueRandomVariable<Data, ParentType> set = _set;
		if(set==null)
			return 0;
		final ParentType parent = set.getParent();
		if(parent == null)
			return RandomVariableUtils.ONE;

		int nbEqual = 0;
		int nbEqui = 0;
		long cumuledProba = 0;
		for(final IRandomVariableWithProbabilityStrategy<Data, ?, ?> bro:parent)
		{
			final IProbabilityStrategy broProbaStrat = bro.getProbabilityStrategy();
			if(broProbaStrat instanceof EquiProbability)
			{
				final Data value = getValue();
				if(value==null)
				{
					if(((EquiProbability<?, ?>)broProbaStrat).getValue()==null)
						nbEqual++;
				}
				else
					if(value.equals(((EquiProbability<?, ?>)broProbaStrat).getValue()))
						nbEqual++;
				nbEqui++;
			}
			else if((broProbaStrat instanceof IStaticProbabilityStrategy)||(broProbaStrat instanceof FittingProbability)||(broProbaStrat instanceof ScalingProbability))
				cumuledProba += bro.getProbability(parent);
		}
		return (int) ((RandomVariableUtils.ONE-cumuledProba)/(nbEqui*nbEqual));
	}

	protected @Nullable Data getValue()
	{
		final ISingleValueRandomVariable<Data, ParentType> set = _set;
		if (set != null)
			return set.getValue();
		throw new NullPointerException();
	}

	@Override
	public boolean merge(final IProbabilityStrategy other)
	{
		if(!(other instanceof EquiProbability))
			return false;
		return true;
	}
}
