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
public class BridgedScalingDefaultUnorderedCompositeRandomVariableEditor
<
	Data,
	Bridged extends AbstractEditableUnorderedCompositeRandomVariable<Data, BaseEditableRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
>
extends BridgedDefaultUnorderedCompositeRandomVariableEditor<Data, Bridged>
{
	public BridgedScalingDefaultUnorderedCompositeRandomVariableEditor(final Bridged bridged)
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