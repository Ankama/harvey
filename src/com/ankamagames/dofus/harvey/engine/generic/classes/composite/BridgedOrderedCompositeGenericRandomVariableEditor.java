package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.generic.datawrapper.FixedProbabilityOrderedGenericDataWrapper;
import com.ankamagames.dofus.harvey.generic.interfaces.IEditableOrderedGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class BridgedOrderedCompositeGenericRandomVariableEditor
<
	Data,
	Bridged extends AbstractEditableOrderedCompositeGenericRandomVariable<Data, BaseEditableOrderedGenericRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
>
extends AbstractBridgedCompositeGenericRandomVariableEditor<Data, IEditableOrderedGenericRandomVariable<Data>, BaseEditableOrderedGenericRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies, Bridged>
{
	public BridgedOrderedCompositeGenericRandomVariableEditor(final Bridged bridged, final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	public BridgedOrderedCompositeGenericRandomVariableEditor(final Bridged bridged)
	{
		this(bridged, ProbabilityStrategies.FIXED);
	}

	@Override
	protected BaseEditableOrderedGenericRandomVariableWrapper<Data, ?, ?, ?> getNewChild(
			final @Nullable Data value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new FixedProbabilityOrderedGenericDataWrapper<Data>(value), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableOrderedGenericRandomVariableWrapper<Data, ?, ?, ?> getNewChild(
			final IEditableOrderedGenericRandomVariable<Data> randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseOrderedGenericRandomVariableWrapper<?, ?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableOrderedGenericRandomVariableWrapper<Data, IEditableOrderedGenericRandomVariable<Data>, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedGenericRandomVariableWrapper<?, ?, ?, ?>>> newWrapper =
				new BaseEditableOrderedGenericRandomVariableWrapper<Data, IEditableOrderedGenericRandomVariable<Data>, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedGenericRandomVariableWrapper<?, ?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseGenericRandomVariableWrapper<?, ?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return getDefaultProbabilityStrategy().getNewProbabilityStrategy(probability);
	}
}
