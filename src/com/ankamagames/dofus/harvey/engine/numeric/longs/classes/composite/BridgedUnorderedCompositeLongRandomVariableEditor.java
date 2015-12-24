/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.longs.datawrapper.FixedProbabilityLongWrapper;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.IEditableLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedUnorderedCompositeLongRandomVariableEditor
<
	Bridged extends AbstractEditableUnorderedCompositeLongRandomVariable<BaseEditableLongRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
>
extends AbstractBridgedCompositeLongRandomVariableEditor<IEditableLongRandomVariable, BaseEditableLongRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies, Bridged>
{
	public BridgedUnorderedCompositeLongRandomVariableEditor(final Bridged bridged, final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	public BridgedUnorderedCompositeLongRandomVariableEditor(final Bridged bridged)
	{
		this(bridged, ProbabilityStrategies.FIXED);
	}

	@Override
	protected BaseEditableLongRandomVariableWrapper<?, ?, ?> getNewChild(
			final long value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new FixedProbabilityLongWrapper(value), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableLongRandomVariableWrapper<?, ?, ?> getNewChild(
			final IEditableLongRandomVariable randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseLongRandomVariableWrapper<?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableLongRandomVariableWrapper<IEditableLongRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseLongRandomVariableWrapper<?, ?, ?>>> newWrapper =
				new BaseEditableLongRandomVariableWrapper<IEditableLongRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseLongRandomVariableWrapper<?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseLongRandomVariableWrapper<?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return getDefaultProbabilityStrategy().getNewProbabilityStrategy(probability);
	}
}