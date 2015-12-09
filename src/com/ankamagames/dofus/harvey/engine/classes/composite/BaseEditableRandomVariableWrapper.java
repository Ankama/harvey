/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableRandomVariableWrapper
<
	Data,
	ChildType extends IEditableRandomVariable<Data>,
	ParentType extends AbstractCompositeRandomVariable<Data, ?>&IEditableRandomVariable<Data>,
	ProbabilityStrategy extends IEditableProbabilityStrategy
>
extends BaseRandomVariableWrapper<Data, ChildType, ParentType, ProbabilityStrategy>
implements IEditableRandomVariable<Data>
{
	public BaseEditableRandomVariableWrapper(final ChildType element, final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
	}

	@Override
	public boolean containsOnly(@Nullable final Data value)
	{
		return _element.containsOnly(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(@Nullable final Data value, final int probability)
	{
		if(_element.containsOnly(value))
		{
			_probabilityStrategy.setProbability(probability);
			return true;
		}
		return _element.setProbabilityOf(value, RandomVariableUtils.divideFixedPrecision(probability, _probabilityStrategy.getProbability()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(@Nullable final Data value)
	{
		return _element.remove(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#add(java.lang.Object, int)
	 */
	@Override
	public boolean add(@Nullable final Data value, final int probability)
	{
		return _element.add(value, RandomVariableUtils.divideFixedPrecision(probability, _probabilityStrategy.getProbability()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(@Nullable final Data value, final int delta)
	{
		if(_element.containsOnly(value))
		{
			_probabilityStrategy.addProbability(delta);
			return true;
		}
		return _element.addProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, _probabilityStrategy.getProbability()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(@Nullable final Data value, final int delta)
	{
		if(_element.containsOnly(value))
		{
			_probabilityStrategy.removeProbability(delta);
			return true;
		}
		return _element.removeProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, _probabilityStrategy.getProbability()));
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#clear()
	 */
	@Override
	public void clear()
	{
		_element.clear();
	}
}