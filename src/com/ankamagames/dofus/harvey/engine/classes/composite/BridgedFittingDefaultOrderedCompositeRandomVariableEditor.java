/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import com.ankamagames.dofus.harvey.engine.classes.composite.probabilityfactories.ProbabilityStrategies;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedFittingDefaultOrderedCompositeRandomVariableEditor
<
	Data,
	Bridged extends AbstractEditableOrderedCompositeRandomVariable<Data, BaseEditableOrderedRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
>
extends BridgedDefaultOrderedCompositeRandomVariableEditor<Data, Bridged>
{
	public BridgedFittingDefaultOrderedCompositeRandomVariableEditor(final Bridged bridged)
	{
		super(bridged);
	}

	@Override
	protected IBridgedEditableProbabilityStrategy<? super BaseRandomVariableWrapper<?, ?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return ProbabilityStrategies.FITTING.getNewProbabilityStrategy(probability);
	}
}