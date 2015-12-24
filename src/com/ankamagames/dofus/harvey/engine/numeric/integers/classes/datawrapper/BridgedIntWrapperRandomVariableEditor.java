/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.numeric.integers.inetrfaces.IIEditableIntRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IEditableIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedIntWrapperRandomVariableEditor
<
	Bridged extends BaseIntWrapperRandomVariable<? extends IEditableProbabilityStrategy>&IEditableIntRandomVariable
>
implements IIEditableIntRandomVariable
{
	Bridged _bridged;

	public BridgedIntWrapperRandomVariableEditor(final Bridged bridged)
	{
		_bridged = bridged;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final int value, final int probability)
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
	public boolean remove(final int value)
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
	public boolean addProbabilityTo(final int value, final int delta)
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
	public boolean removeProbabilityTo(final int value, final int delta)
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