/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import com.ankamagames.dofus.harvey.datawrapper.FixedProbabilityDataWrapper;
import com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedDefaultCompositeRandomVariableEditor
<
	Data,
	Bridged extends AbstractEditableUnorderedCompositeRandomVariable<Data, BaseEditableRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
>
extends BridgedAbstractCompositeRandomVariableEditor<Data, BaseEditableRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies, Bridged>
{
	public BridgedDefaultCompositeRandomVariableEditor(final Bridged bridged)
	{
		super(bridged);
	}

	@Override
	protected BaseEditableRandomVariableWrapper<Data, ?, ?, ?> getNewChild(
			@Nullable final Data value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new FixedProbabilityDataWrapper<Data>(value), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableRandomVariableWrapper<Data, ?, ?, ?> getNewChild(
			final IEditableRandomVariable<Data> randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseRandomVariableWrapper<?, ?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableRandomVariableWrapper<Data, IEditableRandomVariable<Data>, Bridged, IBridgedEditableProbabilityStrategy<? super BaseRandomVariableWrapper<?, ?, ?, ?>>> newWrapper =
				new BaseEditableRandomVariableWrapper<Data, IEditableRandomVariable<Data>, Bridged, IBridgedEditableProbabilityStrategy<? super BaseRandomVariableWrapper<?, ?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseRandomVariableWrapper<?, ?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return ProbabilityStrategies.FIXED.getNewProbabilityStrategy(probability);
	}
}