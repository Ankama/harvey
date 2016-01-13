/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.BridgedRandomVariableWrapperEditor;
import com.ankamagames.dofus.harvey.engine.common.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.longs.inetrfaces.IIEditableLongRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.ILongRandomVariable;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.IEditableLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedLongRandomVariableWrapperEditor
<
	Bridged extends BaseLongRandomVariableWrapper<?, ?, ? extends IEditableProbabilityStrategy>&IEditableLongRandomVariable
>
extends BridgedRandomVariableWrapperEditor<Bridged>
implements IIEditableLongRandomVariable
{
	public BridgedLongRandomVariableWrapperEditor(final Bridged bridged)
	{
		super(bridged);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final long value, final int probability)
	{
		final ILongRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().setProbability(probability);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableLongRandomVariable)(element)).setProbabilityOf(value, RandomVariableUtils.divideFixedPrecision(probability, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final long value)
	{
		final ILongRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			clear();
			return true;
		}
		if(element.contains(value))
		{
			if(element instanceof IEditableRandomVariable)
				return ((IEditableLongRandomVariable)(element)).remove(value);
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final long value, final int delta)
	{
		final ILongRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().addProbability(delta);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableLongRandomVariable)(element)).addProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(final long value, final int delta)
	{
		final ILongRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().removeProbability(delta);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableLongRandomVariable)(element)).removeProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}
}