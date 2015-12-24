package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.doubles.datawrapper.FixedProbabilityOrderedDoubleWrapper;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IEditableOrderedDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class BridgedOrderedCompositeDoubleRandomVariableEditor
<
	Bridged extends AbstractEditableOrderedCompositeDoubleRandomVariable<BaseEditableOrderedDoubleRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
>
extends AbstractBridgedCompositeDoubleRandomVariableEditor<IEditableOrderedDoubleRandomVariable, BaseEditableOrderedDoubleRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies, Bridged>
{
	public BridgedOrderedCompositeDoubleRandomVariableEditor(final Bridged bridged, final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	public BridgedOrderedCompositeDoubleRandomVariableEditor(final Bridged bridged)
	{
		this(bridged, ProbabilityStrategies.FIXED);
	}

	@Override
	protected BaseEditableOrderedDoubleRandomVariableWrapper<?, ?, ?> getNewChild(
			final double value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new FixedProbabilityOrderedDoubleWrapper(value), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableOrderedDoubleRandomVariableWrapper<?, ?, ?> getNewChild(
			final IEditableOrderedDoubleRandomVariable randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseOrderedDoubleRandomVariableWrapper<?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableOrderedDoubleRandomVariableWrapper<IEditableOrderedDoubleRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedDoubleRandomVariableWrapper<?, ?, ?>>> newWrapper =
				new BaseEditableOrderedDoubleRandomVariableWrapper<IEditableOrderedDoubleRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedDoubleRandomVariableWrapper<?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseDoubleRandomVariableWrapper<?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return getDefaultProbabilityStrategy().getNewProbabilityStrategy(probability);
	}
}
