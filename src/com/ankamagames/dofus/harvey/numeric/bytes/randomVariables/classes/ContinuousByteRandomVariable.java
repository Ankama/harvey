/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.randomVariables.classes;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.classes.AbstractContinuousRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.dynamicstrategies.GaussianProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.bytes.randomVariables.Interfaces.IContinuousByteElementaryEvent;
import com.ankamagames.dofus.harvey.numeric.bytes.randomVariables.Interfaces.IContinuousByteRandomVariable;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IDegenerateContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class ContinuousByteRandomVariable
extends AbstractContinuousRandomVariable<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IDegenerateContinuousByteSet, IContinuousByteRandomVariable, IContinuousByteElementaryEvent, IContinuousByteSet, GaussianProbabilityStrategy<Byte>>
implements IContinuousByteRandomVariable
{
	public static ContinuousByteRandomVariable makeGaussianRandomVariable(final IContinuousByteSet set, final double expectation, final double sigma)
	{
		return new ContinuousByteRandomVariable(set, new GaussianProbabilityStrategy<Byte>(expectation, sigma));
	}


	public ContinuousByteRandomVariable(final IContinuousByteSet elements, final GaussianProbabilityStrategy<Byte> probability)
	{
		super(elements, probability);
	}


	@Override
	public IContinuousByteRandomVariable or(
			final IContinuousByteRandomVariable RV)
	{
		if(RV.isImpossible())
			return this;
		if(isImpossible())
			return RV;
		if(RV.isElementaryEvent() && isElementaryEvent() && getElements().equals(RV.getElements()))
			return this;
		return ContinuousByteRandomVariable.makeCompositeRandomVariable(this, RV);
	}


	@Override
	public IContinuousByteRandomVariable and(
			final IContinuousByteRandomVariable RV)
	{
		if(RV.isImpossible())
			return RV;
		if(isImpossible())
			return this;
		if(RV.isElementaryEvent() && isElementaryEvent() && getElements().equals(RV.getElements()))
			return this;
		final double mean =  ((getMean()*Math.pow(RV.getVariance(),2)) + (RV.getMean()*Math.pow(getVariance(), 2)))/ (Math.pow(getVariance(), 2)+Math.pow(RV.getVariance(), 2));
		final double sigma = Math.sqrt( ( (Math.pow(getVariance(), 2) * Math.pow(RV.getVariance(), 2)) ) / ( Math.pow(getVariance(), 2)+Math.pow(RV.getVariance(), 2) ) );
		return ContinuousByteRandomVariable.makeGaussianRandomVariable(getElements().intersect(RV.getElements()), mean, sigma);


	}


	@Override
	public IContinuousByteRandomVariable add(
			final IContinuousByteRandomVariable RV)
	{
		if(RV.isImpossible())
			return this;
		if(isImpossible())
			return RV;
		final IContinuousByteSet unite = getElements().unite(RV.getElements());
		return ContinuousByteRandomVariable.makeGaussianRandomVariable(unite, RV.getMean()+getMean(), getVariance()+RV.getVariance());
	}


	@Override
	public IContinuousByteRandomVariable remove(
			final IContinuousByteRandomVariable RV)
	{
		if(RV.isImpossible())
			return this;
		if(isImpossible())
			return RV;
		final IContinuousByteSet unite = getElements().subtract(RV.getElements());
		return ContinuousByteRandomVariable.makeGaussianRandomVariable(unite, RV.getMean()-getMean(), getVariance()-RV.getVariance());
	}


	@Override
	public IContinuousByteRandomVariable multiply(
			final IContinuousByteRandomVariable RV)
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IContinuousByteRandomVariable divide(
			final IContinuousByteRandomVariable RV) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<IContinuousByteElementaryEvent> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
