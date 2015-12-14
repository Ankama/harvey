/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
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
public class BridgedRandomVariableWrapperEditor
<
	Data,
	Bridged extends BaseRandomVariableWrapper<Data, ? extends IEditableRandomVariable<Data>, ?, ? extends IEditableProbabilityStrategy>&IEditableRandomVariable<Data>
>
implements IIEditableRandomVariable<Data>
{
	Bridged _bridged;

	public BridgedRandomVariableWrapperEditor(final Bridged bridged)
	{
		_bridged = bridged;
	}

	@Override
	public boolean containsOnly(@Nullable final Data value)
	{
		return _bridged._element.containsOnly(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(@Nullable final Data value, final int probability)
	{
		if(_bridged._element.containsOnly(value))
		{
			_bridged._probabilityStrategy.setProbability(probability);
			return true;
		}
		return _bridged._element.setProbabilityOf(value, RandomVariableUtils.divideFixedPrecision(probability, _bridged._probabilityStrategy.getProbability()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(@Nullable final Data value)
	{
		return _bridged._element.remove(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#add(java.lang.Object, int)
	 */
	@Override
	public boolean add(@Nullable final Data value, final int probability)
	{
		return _bridged._element.add(value, RandomVariableUtils.divideFixedPrecision(probability, _bridged._probabilityStrategy.getProbability()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(@Nullable final Data value, final int delta)
	{
		if(_bridged._element.containsOnly(value))
		{
			_bridged._probabilityStrategy.addProbability(delta);
			return true;
		}
		return _bridged._element.addProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, _bridged._probabilityStrategy.getProbability()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(@Nullable final Data value, final int delta)
	{
		if(_bridged._element.containsOnly(value))
		{
			_bridged._probabilityStrategy.removeProbability(delta);
			return true;
		}
		return _bridged._element.removeProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, _bridged._probabilityStrategy.getProbability()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#clear()
	 */
	@Override
	public void clear()
	{
		_bridged._element.clear();
	}
}