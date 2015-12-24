/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.numeric.doubles.inetrfaces.IIEditableDoubleRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IEditableDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedDoubleWrapperRandomVariableEditor
<
	Bridged extends BaseDoubleWrapperRandomVariable<? extends IEditableProbabilityStrategy>&IEditableDoubleRandomVariable
>
implements IIEditableDoubleRandomVariable
{
	Bridged _bridged;

	public BridgedDoubleWrapperRandomVariableEditor(final Bridged bridged)
	{
		_bridged = bridged;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final double value, final int probability)
	{
		if(_bridged.isUnknown())
		{
			_bridged._value = value;
			_bridged.getProbabilityStrategy().setProbability(probability);
			return true;
		}
		if(_bridged.contains(value))
		{
			_bridged.getProbabilityStrategy().setProbability(probability);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final double value)
	{
		if(_bridged.contains(value))
		{
			clear();
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final double value, final int delta)
	{
		if(_bridged.contains(value))
		{
			_bridged.getProbabilityStrategy().addProbability(delta);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(final double value, final int delta)
	{
		if(_bridged.contains(value))
		{
			_bridged.getProbabilityStrategy().removeProbability(delta);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#clear()
	 */
	@Override
	public void clear()
	{
		_bridged.getProbabilityStrategy().setProbability(0);
	}
}