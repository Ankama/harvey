/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.classes.composite.BridgedBasicCollectionWrapperEditor;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.inetrfaces.IIEditableByteRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IEditableByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedByteRandomVariableWrapperEditor
<
	Bridged extends BaseByteRandomVariableWrapper<? extends IEditableByteRandomVariable, ?, ? extends IEditableProbabilityStrategy>&IEditableByteRandomVariable
>
extends BridgedBasicCollectionWrapperEditor<Bridged>
implements IIEditableByteRandomVariable
{
	public BridgedByteRandomVariableWrapperEditor(final Bridged bridged)
	{
		super(bridged);
	}

	@Override
	public boolean containsOnly(final byte value)
	{
		return getBridged().getElement().containsOnly(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final byte value, final int probability)
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
	public boolean remove(final byte value)
	{
		return getBridged().getElement().remove(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#add(java.lang.Object, int)
	 */
	@Override
	public boolean add(final byte value, final int probability)
	{
		return getBridged().getElement().add(value, RandomVariableUtils.divideFixedPrecision(probability, getBridged().getProbabilityStrategy().getProbability()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final byte value, final int delta)
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
	public boolean removeProbabilityTo(final byte value, final int delta)
	{
		if(getBridged().getElement().containsOnly(value))
		{
			getBridged().getProbabilityStrategy().removeProbability(delta);
			return true;
		}
		return getBridged().getElement().removeProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, getBridged().getProbabilityStrategy().getProbability()));
	}
}