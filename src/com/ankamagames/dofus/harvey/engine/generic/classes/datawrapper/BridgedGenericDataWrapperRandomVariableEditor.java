/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper;

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
public class BridgedGenericDataWrapperRandomVariableEditor
<
	Data,
	Bridged extends BaseGenericDataWrapperRandomVariable<Data, ? extends IEditableProbabilityStrategy>&IEditableGenericRandomVariable<Data>
>
implements IIEditableGenericRandomVariable<Data>
{
	Bridged _bridged;

	public BridgedGenericDataWrapperRandomVariableEditor(final Bridged bridged)
	{
		_bridged = bridged;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(@Nullable final Data value, final int probability)
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
	public boolean remove(@Nullable final Data value)
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
	public boolean addProbabilityTo(@Nullable final Data value, final int delta)
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
	public boolean removeProbabilityTo(@Nullable final Data value, final int delta)
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