/**
 *
 */
package com.ankamagames.dofus.harvey.engine.mergestrategies;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.ISingleValueRandomVariable;
import com.ankamagames.dofus.harvey.singlevalue.MergeableSingleValueRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class SingleValueMergeStrategty<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>>
	implements IMergeStrategy<Data, ParentType>
{
	private final MergeableSingleValueRandomVariable<Data, ParentType, ?, ?> _randomVar;

	public SingleValueMergeStrategty(final MergeableSingleValueRandomVariable<Data, ParentType, ?, ?> randomVar)
	{
		_randomVar = randomVar;
	}

	@Override
	public boolean merge(final IEditableRandomVariable<Data, ParentType> other)
	{
		if (this == other)
			return true;

		if(! ((other instanceof ISingleValueRandomVariable)&&(other instanceof IEditableRandomVariableWithProbabilityStrategy)))
			return false;

		final ParentType parent = _randomVar.getParent();
		@SuppressWarnings("unchecked")
		final ISingleValueRandomVariable<Data, ParentType> SVOther = (ISingleValueRandomVariable<Data, ParentType>)other;
		if(parent==null)
		{
			if(SVOther.getParent()!=null)
				return false;
		}
		else if(!parent.equals(SVOther.getParent()))
			return false;

		final Data value = _randomVar.getValue();
		if(value==null)
		{
			if(SVOther.getValue()!=null)
				return false;
		}
		else if(!value.equals(SVOther.getValue()))
			return false;

		@SuppressWarnings("unchecked")
		final IEditableRandomVariableWithProbabilityStrategy<Data, ParentType, ?> otherWPS = (IEditableRandomVariableWithProbabilityStrategy<Data, ParentType, ?>)other;
		return _randomVar.getProbabilityStrategy().merge(otherWPS.getProbabilityStrategy());
	}
}
