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
public class BridgedScalingDefaultCompositeRandomVariableEditor
<
	Data,
	Bridged extends AbstractEditableUnorderedCompositeRandomVariable<Data, BaseEditableRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
>
extends BridgedDefaultCompositeRandomVariableEditor<Data, Bridged>
{
	public BridgedScalingDefaultCompositeRandomVariableEditor(final Bridged bridged)
	{
		super(bridged);
	}

	@Override
	protected IBridgedEditableProbabilityStrategy<? super BaseRandomVariableWrapper<?, ?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return ProbabilityStrategies.SCALING.getNewProbabilityStrategy(probability);
	}
}