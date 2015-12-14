/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedDataWrapperRandomVariableEditor
<
	Data,
	Bridged extends BaseDataWrapperRandomVariable<Data, ? extends IEditableProbabilityStrategy>&IEditableRandomVariable<Data>
>
implements IIEditableRandomVariable<Data>
{
	Bridged _bridged;

	public BridgedDataWrapperRandomVariableEditor(final Bridged bridged)
	{
		_bridged = bridged;
	}

	@Override
	public boolean containsOnly(@Nullable final Data value)
	{
		return _bridged.contains(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(@Nullable final Data value, final int probability)
	{
		if(_bridged.isEmpty())
		{
			_bridged._value = value;
			_bridged._probabilityStrategy.setProbability(probability);
			return true;
		}
		if(_bridged.contains(value))
		{
			_bridged._probabilityStrategy.setProbability(probability);
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
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#add(java.lang.Object, int)
	 */
	@Override
	public boolean add(@Nullable final Data value, final int probability)
	{
		if(_bridged.contains(value))
		{
			_bridged._probabilityStrategy.addProbability(probability);
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
			_bridged._probabilityStrategy.addProbability(delta);
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
			_bridged._probabilityStrategy.removeProbability(delta);
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
		_bridged._probabilityStrategy.setProbability(0);
	}
}