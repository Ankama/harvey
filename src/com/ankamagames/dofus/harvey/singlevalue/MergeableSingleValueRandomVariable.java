/**
 *
 */
package com.ankamagames.dofus.harvey.singlevalue;

import com.ankamagames.dofus.harvey.engine.base.classes.BaseEditableSingleValueRandomVariable;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IMergeableRandomVariableWithBehaviourProbabilityStrategyEditorAndMergeStrategy;
import com.ankamagames.dofus.harvey.engine.behaviours.singlevalue.ISingleValueBehaviour;
import com.ankamagames.dofus.harvey.engine.editors.RandomVariableEditor;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.mergestrategies.SingleValueMergeStrategty;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IMergeableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class MergeableSingleValueRandomVariable<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, Behaviour extends ISingleValueBehaviour<Data>, ProbabilityStrategy extends IMergeableProbabilityStrategy>
extends BaseEditableSingleValueRandomVariable<Data, ParentType, Behaviour, ProbabilityStrategy>
implements IMergeableSingleValueRandomVariable<Data, ParentType>,
	IMergeableRandomVariableWithBehaviourProbabilityStrategyEditorAndMergeStrategy
	<
		Data,
		ParentType,
		Behaviour,
		ProbabilityStrategy,
		RandomVariableEditor<Data, ParentType, BaseEditableSingleValueRandomVariable<Data, ParentType, Behaviour, ProbabilityStrategy>>,
		SingleValueMergeStrategty<Data, ParentType>
	>
{
	SingleValueMergeStrategty<Data, ParentType> _mergeStrategy;

	public MergeableSingleValueRandomVariable(final @Nullable ParentType parent, final Behaviour behaviour, final ProbabilityStrategy strategy) throws OverOneProbabilityException
	{
		super(parent, behaviour, strategy);
		_mergeStrategy = new SingleValueMergeStrategty<Data, ParentType>(this);
	}

	public MergeableSingleValueRandomVariable(final Behaviour behaviour, final ProbabilityStrategy strategy) throws OverOneProbabilityException
	{
		super(behaviour, strategy);
		_mergeStrategy = new SingleValueMergeStrategty<Data, ParentType>(this);
	}

	@Override
	public boolean merge(final IEditableRandomVariable<Data, ParentType> other)
	{
		return _mergeStrategy.merge(other);
	}

	@Override
	public SingleValueMergeStrategty<Data, ParentType> getMergeStrategy()
	{
		return _mergeStrategy;
	}
}