/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IMergeableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.dynamicstrategies.IDynamicProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.IStaticProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedScalingProbability
<
	Bridged extends BaseRandomVariableWrapper<?, ?, ?, ?>
>
implements IBridgedEditableProbabilityStrategy<Bridged>, IDynamicProbabilityStrategy, IEditableProbabilityStrategy, IMergeableProbabilityStrategy
{
	protected Bridged _bridged;
	protected int _probability;

	public BridgedScalingProbability(final Bridged bridged, final int probability)
	{
		_bridged = bridged;
		_probability = probability;
	}

	public BridgedScalingProbability(final Bridged bridged)
	{
		this(bridged, RandomVariableUtils.ONE);
	}

	@SuppressWarnings("null")
	public BridgedScalingProbability(final int probability)
	{
		_bridged = null;
		_probability = probability;
	}

	public BridgedScalingProbability()
	{
		this(RandomVariableUtils.ONE);
	}

	@Override
	public void init(final Bridged bridged)
	{
		_bridged = bridged;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.probabilitystrategy.IProbabilityStrategy#getProbability()
	 */
	@Override
	public int getProbability()
	{
		final Iterator<? extends BaseRandomVariableWrapper<?, ?, ?, ?>> iterator = _bridged._parent.iterator();
		long scalingProba = 0;
		long cumuledProba = 0;

		while(iterator.hasNext())
		{
			final BaseRandomVariableWrapper<?, ?, ?, ?> bro = iterator.next();
			final IProbabilityStrategy broProbaStrat = bro._probabilityStrategy;
			if((broProbaStrat instanceof IStaticProbabilityStrategy)||(broProbaStrat instanceof BridgedFittingProbability))
			{
				cumuledProba += broProbaStrat.getProbability();
			}
			else if(broProbaStrat instanceof BridgedScalingProbability)
				scalingProba += ((BridgedScalingProbability<?>)broProbaStrat).getRawProbability();
		}
		final long remainingProba = RandomVariableUtils.ONE-cumuledProba;
		if(remainingProba<=0)
			return 0;
		return (int) (_probability*remainingProba/scalingProba);
	}

	protected int getRawProbability()
	{
		return _probability;
	}

	@Override
	public void setProbability(final int probability)
	{
		setRawProbability(probability);
	}

	protected void setRawProbability(final int probability)
	{
		_probability = probability;
	}

	@Override
	public void addProbability(final int probability)
	{
		final long newProbability = _probability + (long)probability;
		if((newProbability > Integer.MAX_VALUE)||(newProbability < Integer.MIN_VALUE))
			throw new ProbabilityOutOfBoundException();

		_probability = (int) newProbability;
	}

	@Override
	public void removeProbability(final int probability)
	{
		addProbability(-probability);
	}

	@Override
	public boolean merge(final IProbabilityStrategy other)
	{
		if(!(other instanceof BridgedScalingProbability))
			return false;

		final int otherProbability = ((BridgedScalingProbability<?>)other).getRawProbability();
		final long newProba = otherProbability+(long)_probability;
		if((newProba>Integer.MAX_VALUE)||(newProba < Integer.MIN_VALUE))
			return false;

		setRawProbability((int)newProba);

		return true;
	}
}