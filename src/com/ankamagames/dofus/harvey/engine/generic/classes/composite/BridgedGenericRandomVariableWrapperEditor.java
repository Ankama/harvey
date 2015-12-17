/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.BridgedBasicCollectionWrapperEditor;
import com.ankamagames.dofus.harvey.engine.generic.inetrfaces.IIEditableGenericRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.generic.interfaces.IEditableGenericRandomVariable;

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
	Bridged extends BaseGenericRandomVariableWrapper<Data, ? extends IEditableGenericRandomVariable<Data>, ?, ? extends IEditableProbabilityStrategy>&IEditableGenericRandomVariable<Data>
>
extends BridgedBasicCollectionWrapperEditor<Bridged>
implements IIEditableGenericRandomVariable<Data>
{
	public BridgedGenericRandomVariableWrapperEditor(final Bridged bridged)
	{
		super(bridged);
	}

	@Override
	public boolean containsOnly(@Nullable final Data value)
	{
		return getBridged().getElement().containsOnly(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(@Nullable final Data value, final int probability)
	{
		if(getBridged().getElement().containsOnly(value))
		{
			getBridged().getProbabilityStrategy().setProbability(probability);
			return true;
		}
		return getBridged().getElement().setProbabilityOf(value, RandomVariableUtils.divideFixedPrecision(probability, getBridged().getProbabilityStrategy().getProbability()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(@Nullable final Data value)
	{
		return getBridged().getElement().remove(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#add(java.lang.Object, int)
	 */
	@Override
	public boolean add(@Nullable final Data value, final int probability)
	{
		return getBridged().getElement().add(value, RandomVariableUtils.divideFixedPrecision(probability, getBridged().getProbabilityStrategy().getProbability()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(@Nullable final Data value, final int delta)
	{
		if(getBridged().getElement().containsOnly(value))
		{
			getBridged().getProbabilityStrategy().addProbability(delta);
			return true;
		}
		return getBridged().getElement().addProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, getBridged().getProbabilityStrategy().getProbability()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(@Nullable final Data value, final int delta)
	{
		if(getBridged().getElement().containsOnly(value))
		{
			getBridged().getProbabilityStrategy().removeProbability(delta);
			return true;
		}
		return getBridged().getElement().removeProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, getBridged().getProbabilityStrategy().getProbability()));
	}
}