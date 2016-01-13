package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.numeric.floats.classes.datawrapper.BaseOrderedFloatWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.OneProbability;
import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IOrderedFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class BridgedOrderedCompositeFloatRandomVariableEditor
<
	Bridged extends AbstractEditableOrderedCompositeFloatRandomVariable<BaseEditableOrderedFloatRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
>
extends AbstractBridgedCompositeFloatRandomVariableEditor<IOrderedFloatRandomVariable, BaseEditableOrderedFloatRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies, Bridged>
{
	public BridgedOrderedCompositeFloatRandomVariableEditor(final Bridged bridged, final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	public BridgedOrderedCompositeFloatRandomVariableEditor(final Bridged bridged)
	{
		this(bridged, ProbabilityStrategies.FIXED);
	}

	@Override
	protected BaseEditableOrderedFloatRandomVariableWrapper<?, ?, ?> getNewChild(
			final float value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new BaseOrderedFloatWrapperRandomVariable<OneProbability>(value, OneProbability.getInstance()), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableOrderedFloatRandomVariableWrapper<?, ?, ?> getNewChild(
			final IOrderedFloatRandomVariable randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseOrderedFloatRandomVariableWrapper<?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableOrderedFloatRandomVariableWrapper<IOrderedFloatRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedFloatRandomVariableWrapper<?, ?, ?>>> newWrapper =
				new BaseEditableOrderedFloatRandomVariableWrapper<IOrderedFloatRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedFloatRandomVariableWrapper<?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseFloatRandomVariableWrapper<?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return getDefaultProbabilityStrategy().getNewProbabilityStrategy(probability);
	}
}