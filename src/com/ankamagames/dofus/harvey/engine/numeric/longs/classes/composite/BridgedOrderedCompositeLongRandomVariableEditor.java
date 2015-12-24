package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.longs.datawrapper.FixedProbabilityOrderedLongWrapper;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.IEditableOrderedLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class BridgedOrderedCompositeLongRandomVariableEditor
<
	Bridged extends AbstractEditableOrderedCompositeLongRandomVariable<BaseEditableOrderedLongRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
>
extends AbstractBridgedCompositeLongRandomVariableEditor<IEditableOrderedLongRandomVariable, BaseEditableOrderedLongRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies, Bridged>
{
	public BridgedOrderedCompositeLongRandomVariableEditor(final Bridged bridged, final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	public BridgedOrderedCompositeLongRandomVariableEditor(final Bridged bridged)
	{
		this(bridged, ProbabilityStrategies.FIXED);
	}

	@Override
	protected BaseEditableOrderedLongRandomVariableWrapper<?, ?, ?> getNewChild(
			final long value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new FixedProbabilityOrderedLongWrapper(value), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableOrderedLongRandomVariableWrapper<?, ?, ?> getNewChild(
			final IEditableOrderedLongRandomVariable randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseOrderedLongRandomVariableWrapper<?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableOrderedLongRandomVariableWrapper<IEditableOrderedLongRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedLongRandomVariableWrapper<?, ?, ?>>> newWrapper =
				new BaseEditableOrderedLongRandomVariableWrapper<IEditableOrderedLongRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedLongRandomVariableWrapper<?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseLongRandomVariableWrapper<?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return getDefaultProbabilityStrategy().getNewProbabilityStrategy(probability);
	}
}
