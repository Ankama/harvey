package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.bytes.datawrapper.FixedProbabilityOrderedByteWrapper;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IEditableOrderedByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class BridgedOrderedCompositeByteRandomVariableEditor
<
	Bridged extends AbstractEditableOrderedCompositeByteRandomVariable<BaseEditableOrderedByteRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
>
extends AbstractBridgedCompositeByteRandomVariableEditor<IEditableOrderedByteRandomVariable, BaseEditableOrderedByteRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies, Bridged>
{
	protected ProbabilityStrategies _defaultProbabilityStrategy;

	public BridgedOrderedCompositeByteRandomVariableEditor(final Bridged bridged, final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super(bridged);
		_defaultProbabilityStrategy = defaultProbabilityStrategy;
	}

	public BridgedOrderedCompositeByteRandomVariableEditor(final Bridged bridged)
	{
		this(bridged, ProbabilityStrategies.FIXED);
	}

	protected ProbabilityStrategies getDefaultProbabilityStrategy()
	{
		return _defaultProbabilityStrategy;
	}

	@Override
	protected BaseEditableOrderedByteRandomVariableWrapper<?, ?, ?> getNewChild(
			final byte value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new FixedProbabilityOrderedByteWrapper(value), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableOrderedByteRandomVariableWrapper<?, ?, ?> getNewChild(
			final IEditableOrderedByteRandomVariable randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseOrderedByteRandomVariableWrapper<?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableOrderedByteRandomVariableWrapper<IEditableOrderedByteRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedByteRandomVariableWrapper<?, ?, ?>>> newWrapper =
				new BaseEditableOrderedByteRandomVariableWrapper<IEditableOrderedByteRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseOrderedByteRandomVariableWrapper<?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseByteRandomVariableWrapper<?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return getDefaultProbabilityStrategy().getNewProbabilityStrategy(probability);
	}
}
