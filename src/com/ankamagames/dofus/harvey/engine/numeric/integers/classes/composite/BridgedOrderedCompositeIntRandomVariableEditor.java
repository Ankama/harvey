package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.integers.datawrapper.FixedProbabilityOrderedIntWrapper;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IEditableOrderedIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class BridgedOrderedCompositeIntRandomVariableEditor
<
	Bridged extends AbstractEditableOrderedCompositeIntRandomVariable<BaseEditableOrderedIntRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
>
extends AbstractBridgedCompositeIntRandomVariableEditor<IEditableOrderedIntRandomVariable, BaseEditableOrderedIntRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies, Bridged>
{
	public BridgedOrderedCompositeIntRandomVariableEditor(final Bridged bridged, final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	public BridgedOrderedCompositeIntRandomVariableEditor(final Bridged bridged)
	{
		this(bridged, ProbabilityStrategies.FIXED);
	}

	@Override
	protected BaseEditableOrderedIntRandomVariableWrapper<?, ?, ?> getNewChild(
			final int value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new FixedProbabilityOrderedIntWrapper(value), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableOrderedIntRandomVariableWrapper<?, ?, ?> getNewChild(
			final IEditableOrderedIntRandomVariable randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseOrderedIntRandomVariableWrapper<?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableOrderedIntRandomVariableWrapper<IEditableOrderedIntRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedIntRandomVariableWrapper<?, ?, ?>>> newWrapper =
				new BaseEditableOrderedIntRandomVariableWrapper<IEditableOrderedIntRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedIntRandomVariableWrapper<?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseIntRandomVariableWrapper<?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return getDefaultProbabilityStrategy().getNewProbabilityStrategy(probability);
	}
}
