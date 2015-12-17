/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.numeric.bytes.inetrfaces.IIEditableByteRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IEditableByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedByteWrapperRandomVariableEditor
<
	Bridged extends BaseByteWrapperRandomVariable<? extends IEditableProbabilityStrategy>&IEditableByteRandomVariable
>
implements IIEditableByteRandomVariable
{
	Bridged _bridged;

	public BridgedByteWrapperRandomVariableEditor(final Bridged bridged)
	{
		_bridged = bridged;
	}

	@Override
	public boolean containsOnly(final byte value)
	{
		return _bridged.contains(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final byte value, final int probability)
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
	public boolean remove(final byte value)
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
	public boolean add(final byte value, final int probability)
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
	public boolean addProbabilityTo(final byte value, final int delta)
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
	public boolean removeProbabilityTo(final byte value, final int delta)
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