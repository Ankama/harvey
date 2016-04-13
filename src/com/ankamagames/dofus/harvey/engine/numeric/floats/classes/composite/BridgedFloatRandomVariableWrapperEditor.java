/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.BridgedRandomVariableWrapperEditor;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.floats.inetrfaces.IIEditableFloatRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IFloatRandomVariable;
import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IEditableFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedFloatRandomVariableWrapperEditor
<
	Bridged extends BaseFloatRandomVariableWrapper<?, ?, ? extends IEditableProbabilityStrategy>&IEditableFloatRandomVariable
>
extends BridgedRandomVariableWrapperEditor<Bridged>
implements IIEditableFloatRandomVariable
{
	public BridgedFloatRandomVariableWrapperEditor(final Bridged bridged)
	{
		super(bridged);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final float value, final int probability)
	{
		final IFloatRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().setProbability(probability);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableFloatRandomVariable)(element)).setProbabilityOf(value, RandomVariableUtils.divideFixedPrecision(probability, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final float value)
	{
		final IFloatRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			clear();
			return true;
		}
		if(element.contains(value))
		{
			if(element instanceof IEditableRandomVariable)
				return ((IEditableFloatRandomVariable)(element)).remove(value);
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final float value, final int delta)
	{
		final IFloatRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().addProbability(delta);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableFloatRandomVariable)(element)).addProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(final float value, final int delta)
	{
		final IFloatRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().removeProbability(delta);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableFloatRandomVariable)(element)).removeProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}
}