package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.shorts.datawrapper.FixedProbabilityOrderedShortWrapper;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IEditableOrderedShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class BridgedOrderedCompositeShortRandomVariableEditor
<
	Bridged extends AbstractEditableOrderedCompositeShortRandomVariable<BaseEditableOrderedShortRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
>
extends AbstractBridgedCompositeShortRandomVariableEditor<IEditableOrderedShortRandomVariable, BaseEditableOrderedShortRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies, Bridged>
{
	public BridgedOrderedCompositeShortRandomVariableEditor(final Bridged bridged, final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	public BridgedOrderedCompositeShortRandomVariableEditor(final Bridged bridged)
	{
		this(bridged, ProbabilityStrategies.FIXED);
	}

	@Override
	protected BaseEditableOrderedShortRandomVariableWrapper<?, ?, ?> getNewChild(
			final short value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new FixedProbabilityOrderedShortWrapper(value), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableOrderedShortRandomVariableWrapper<?, ?, ?> getNewChild(
			final IEditableOrderedShortRandomVariable randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseOrderedShortRandomVariableWrapper<?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableOrderedShortRandomVariableWrapper<IEditableOrderedShortRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedShortRandomVariableWrapper<?, ?, ?>>> newWrapper =
				new BaseEditableOrderedShortRandomVariableWrapper<IEditableOrderedShortRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedShortRandomVariableWrapper<?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseShortRandomVariableWrapper<?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return getDefaultProbabilityStrategy().getNewProbabilityStrategy(probability);
	}
}
