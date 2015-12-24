/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.integers.datawrapper.FixedProbabilityIntWrapper;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IEditableIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedUnorderedCompositeIntRandomVariableEditor
<
	Bridged extends AbstractEditableUnorderedCompositeIntRandomVariable<BaseEditableIntRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
>
extends AbstractBridgedCompositeIntRandomVariableEditor<IEditableIntRandomVariable, BaseEditableIntRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies, Bridged>
{
	public BridgedUnorderedCompositeIntRandomVariableEditor(final Bridged bridged, final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	public BridgedUnorderedCompositeIntRandomVariableEditor(final Bridged bridged)
	{
		this(bridged, ProbabilityStrategies.FIXED);
	}

	@Override
	protected BaseEditableIntRandomVariableWrapper<?, ?, ?> getNewChild(
			final int value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new FixedProbabilityIntWrapper(value), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableIntRandomVariableWrapper<?, ?, ?> getNewChild(
			final IEditableIntRandomVariable randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseIntRandomVariableWrapper<?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableIntRandomVariableWrapper<IEditableIntRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseIntRandomVariableWrapper<?, ?, ?>>> newWrapper =
				new BaseEditableIntRandomVariableWrapper<IEditableIntRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseIntRandomVariableWrapper<?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseIntRandomVariableWrapper<?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return getDefaultProbabilityStrategy().getNewProbabilityStrategy(probability);
	}
}