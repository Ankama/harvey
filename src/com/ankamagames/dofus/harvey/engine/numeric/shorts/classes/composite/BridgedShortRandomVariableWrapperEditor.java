/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.BridgedRandomVariableWrapperEditor;
import com.ankamagames.dofus.harvey.engine.common.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.inetrfaces.IIEditableShortRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IEditableShortRandomVariable;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedShortRandomVariableWrapperEditor
<
	Bridged extends BaseShortRandomVariableWrapper<? extends IEditableShortRandomVariable, ?, ? extends IEditableProbabilityStrategy>&IEditableShortRandomVariable
>
extends BridgedRandomVariableWrapperEditor<Bridged>
implements IIEditableShortRandomVariable
{
	public BridgedShortRandomVariableWrapperEditor(final Bridged bridged)
	{
		super(bridged);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final short value, final int probability)
	{
		final IShortRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().setProbability(probability);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableShortRandomVariable)(element)).setProbabilityOf(value, RandomVariableUtils.divideFixedPrecision(probability, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final short value)
	{
		final IShortRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			clear();
			return true;
		}
		if(element.contains(value))
		{
			if(element instanceof IEditableRandomVariable)
				return ((IEditableShortRandomVariable)(element)).remove(value);
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final short value, final int delta)
	{
		final IShortRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().addProbability(delta);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableShortRandomVariable)(element)).addProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(final short value, final int delta)
	{
		final IShortRandomVariable element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().removeProbability(delta);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableShortRandomVariable)(element)).removeProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}
}