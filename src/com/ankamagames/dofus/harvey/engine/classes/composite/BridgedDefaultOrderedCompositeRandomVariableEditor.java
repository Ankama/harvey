package com.ankamagames.dofus.harvey.engine.classes.composite;

import com.ankamagames.dofus.harvey.datawrapper.FixedProbabilityOrderedDataWrapper;
import com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.interfaces.IEditableOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class BridgedDefaultOrderedCompositeRandomVariableEditor
<
	Data,
	Bridged extends AbstractEditableOrderedCompositeRandomVariable<Data, BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
>
extends BridgedAbstractOrderedCompositeRandomVariableEditor<Data, BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies, Bridged>
{
	public BridgedDefaultOrderedCompositeRandomVariableEditor(final Bridged bridged)
	{
		super(bridged);
	}

	@Override
	protected BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?> getNewChild(
			final @Nullable Data value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new FixedProbabilityOrderedDataWrapper<Data>(value), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?> getNewChild(
			final IEditableOrderedRandomVariable<Data> randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseOrderedRandomVariableWrapper<?, ?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableOrderedRandomVariableWrapper<Data, IEditableOrderedRandomVariable<Data>, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedRandomVariableWrapper<?, ?, ?, ?>>> newWrapper =
				new BaseEditableOrderedRandomVariableWrapper<Data, IEditableOrderedRandomVariable<Data>, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedRandomVariableWrapper<?, ?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseRandomVariableWrapper<?, ?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return ProbabilityStrategies.FIXED.getNewProbabilityStrategy(probability);
	}
}
