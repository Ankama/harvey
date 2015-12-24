/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.shorts.datawrapper.FixedProbabilityShortWrapper;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IEditableShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedUnorderedCompositeShortRandomVariableEditor
<
	Bridged extends AbstractEditableUnorderedCompositeShortRandomVariable<BaseEditableShortRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
>
extends AbstractBridgedCompositeShortRandomVariableEditor<IEditableShortRandomVariable, BaseEditableShortRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies, Bridged>
{
	public BridgedUnorderedCompositeShortRandomVariableEditor(final Bridged bridged, final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	public BridgedUnorderedCompositeShortRandomVariableEditor(final Bridged bridged)
	{
		this(bridged, ProbabilityStrategies.FIXED);
	}

	@Override
	protected BaseEditableShortRandomVariableWrapper<?, ?, ?> getNewChild(
			final short value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new FixedProbabilityShortWrapper(value), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableShortRandomVariableWrapper<?, ?, ?> getNewChild(
			final IEditableShortRandomVariable randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseShortRandomVariableWrapper<?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableShortRandomVariableWrapper<IEditableShortRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseShortRandomVariableWrapper<?, ?, ?>>> newWrapper =
				new BaseEditableShortRandomVariableWrapper<IEditableShortRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseShortRandomVariableWrapper<?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseShortRandomVariableWrapper<?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return getDefaultProbabilityStrategy().getNewProbabilityStrategy(probability);
	}
}