/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.BridgedRandomVariableWrapperEditor;
import com.ankamagames.dofus.harvey.engine.common.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.generic.inetrfaces.IIEditableGenericRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.generic.interfaces.IEditableGenericRandomVariable;
import com.ankamagames.dofus.harvey.generic.interfaces.IGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedGenericRandomVariableWrapperEditor
<
	Data,
	Bridged extends BaseGenericRandomVariableWrapper<Data, ?, ?, ? extends IEditableProbabilityStrategy>&IEditableGenericRandomVariable<Data>
>
extends BridgedRandomVariableWrapperEditor<Bridged>
implements IIEditableGenericRandomVariable<Data>
{
	public BridgedGenericRandomVariableWrapperEditor(final Bridged bridged)
	{
		super(bridged);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final @Nullable Data value, final int probability)
	{
		final IGenericRandomVariable<Data> element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().setProbability(probability);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableGenericRandomVariable<Data>)(element)).setProbabilityOf(value, RandomVariableUtils.divideFixedPrecision(probability, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final @Nullable Data value)
	{
		final IGenericRandomVariable<Data> element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			clear();
			return true;
		}
		if(element.contains(value))
		{
			if(element instanceof IEditableRandomVariable)
				return ((IEditableGenericRandomVariable<Data>)(element)).remove(value);
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final @Nullable Data value, final int delta)
	{
		final IGenericRandomVariable<Data> element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().addProbability(delta);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableGenericRandomVariable<Data>)(element)).addProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(final @Nullable Data value, final int delta)
	{
		final IGenericRandomVariable<Data> element = getBridged().getElement();
		if(element.containsOnly(value))
		{
			getBridged().getProbabilityStrategy().removeProbability(delta);
			return true;
		}
		if(element instanceof IEditableRandomVariable)
			return ((IEditableGenericRandomVariable<Data>)(element)).removeProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, getBridged().getProbabilityStrategy().getProbability()));
		return false;
	}
}