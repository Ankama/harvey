/**
 *
 */
package com.ankamagames.dofus.harvey.engine.probabilitystrategies.dynamicstrategies;

import com.ankamagames.dofus.harvey.RandomVariableUtils;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class GaussianProbabilityStrategy<Data extends Number> implements IDynamicProbabilityStrategy<Data> {

	private final double _rho; // the mean value of the gaussian
	private final double _sigma; // the x scale of the gaussian

	public GaussianProbabilityStrategy(final double rho, final double sigma)
	{
		_rho = rho;
		_sigma = sigma;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy#getProbability()
	 */
	@Override
	public int getProbability()
	{
		return 1;
	}


	@Override
	public int getProbability(final Data data)
	{
		final double x = data.doubleValue();
		return RandomVariableUtils.convertFromDouble((Math.exp(-(Math.pow(x-_rho,2)/(2*Math.pow(_sigma, 2)))))/(_sigma*Math.sqrt(2*Math.PI)));
	}

}
