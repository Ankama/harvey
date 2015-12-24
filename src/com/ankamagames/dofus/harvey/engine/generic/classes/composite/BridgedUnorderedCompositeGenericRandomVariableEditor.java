/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.generic.datawrapper.FixedProbabilityGenericDataWrapper;
import com.ankamagames.dofus.harvey.generic.interfaces.IEditableGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedUnorderedCompositeGenericRandomVariableEditor
<
	Data,
	Bridged extends AbstractEditableUnorderedCompositeGenericRandomVariable<Data, BaseEditableGenericRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies>
>
extends AbstractBridgedCompositeGenericRandomVariableEditor<Data, IEditableGenericRandomVariable<Data>, BaseEditableGenericRandomVariableWrapper<Data, ?, ?, ?>, ProbabilityStrategies, Bridged>
{
	public BridgedUnorderedCompositeGenericRandomVariableEditor(final Bridged bridged, final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super(bridged, defaultProbabilityStrategy);
	}

	public BridgedUnorderedCompositeGenericRandomVariableEditor(final Bridged bridged)
	{
		this(bridged, ProbabilityStrategies.FIXED);
	}

	@Override
	protected BaseEditableGenericRandomVariableWrapper<Data, ?, ?, ?> getNewChild(
			@Nullable final Data value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new FixedProbabilityGenericDataWrapper<Data>(value), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableGenericRandomVariableWrapper<Data, ?, ?, ?> getNewChild(
			final IEditableGenericRandomVariable<Data> randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseGenericRandomVariableWrapper<?, ?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableGenericRandomVariableWrapper<Data, IEditableGenericRandomVariable<Data>, Bridged, IBridgedEditableProbabilityStrategy<? super BaseGenericRandomVariableWrapper<?, ?, ?, ?>>> newWrapper =
				new BaseEditableGenericRandomVariableWrapper<Data, IEditableGenericRandomVariable<Data>, Bridged, IBridgedEditableProbabilityStrategy<? super BaseGenericRandomVariableWrapper<?, ?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseGenericRandomVariableWrapper<?, ?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return getDefaultProbabilityStrategy().getNewProbabilityStrategy(probability);
	}
}