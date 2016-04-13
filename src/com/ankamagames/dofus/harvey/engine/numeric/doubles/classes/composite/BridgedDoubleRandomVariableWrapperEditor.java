/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.BridgedRandomVariableWrapperEditor;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.inetrfaces.IIEditableDoubleRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IDoubleRandomVariable;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IEditableDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedDoubleRandomVariableWrapperEditor
<
	Bridged extends BaseDoubleRandomVariableWrapper<?, ?, ? extends IEditableProbabilityStrategy>&IEditableDoubleRandomVariable
>
extends BridgedRandomVariableWrapperEditor<Bridged>
implements IIEditableDoubleRandomVariable
{
	public BridgedDoubleRandomVariableWrapperEditor(final Bridged bridged)
	{
		super(bridged);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final double value, final int probability)
	{
		final IDoubleRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().setProbability(probability);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableDoubleRandomVariable)(element)).setProbabilityOf(value, RandomVariableUtils.divideFixedPrecision(probability, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final double value)
	{
		final IDoubleRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			clear();
			return true;
		}
		if(element.contains(value))
		{
			if(element instanceof IEditableRandomVariable)
				return ((IEditableDoubleRandomVariable)(element)).remove(value);
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final double value, final int delta)
	{
		final IDoubleRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().addProbability(delta);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableDoubleRandomVariable)(element)).addProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(final double value, final int delta)
	{
		final IDoubleRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().removeProbability(delta);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableDoubleRandomVariable)(element)).removeProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}
}