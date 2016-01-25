/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.numeric.floats.classes.datawrapper.BaseFloatWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.OneProbability;
import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedUnorderedCompositeFloatRandomVariableEditor
<
	Bridged extends AbstractEditableUnorderedCompositeFloatRandomVariable<BaseEditableFloatRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
>
extends AbstractBridgedCompositeFloatRandomVariableEditor<IFloatRandomVariable, BaseEditableFloatRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies, Bridged>
{
	public BridgedUnorderedCompositeFloatRandomVariableEditor(final Bridged bridged, final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	public BridgedUnorderedCompositeFloatRandomVariableEditor(final Bridged bridged)
	{
		this(bridged, ProbabilityStrategies.FIXED);
	}

	@Override
	protected BaseEditableFloatRandomVariableWrapper<?, ?, ?> getNewChild(
			final float value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new BaseFloatWrapperRandomVariable<OneProbability>(value, OneProbability.getInstance()), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableFloatRandomVariableWrapper<?, ?, ?> getNewChild(
			final IFloatRandomVariable randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseFloatRandomVariableWrapper<?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableFloatRandomVariableWrapper<IFloatRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseFloatRandomVariableWrapper<?, ?, ?>>> newWrapper =
				new BaseEditableFloatRandomVariableWrapper<IFloatRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseFloatRandomVariableWrapper<?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseFloatRandomVariableWrapper<?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return getDefaultProbabilityStrategy().getNewProbabilityStrategy(probability);
	}
}