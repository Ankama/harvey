/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.probabilityfactories.ProbabilityStrategies;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.bytes.datawrapper.FixedProbabilityByteWrapper;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IEditableByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedUnorderedCompositeByteRandomVariableEditor
<
	Bridged extends AbstractEditableUnorderedCompositeByteRandomVariable<BaseEditableByteRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies>
>
extends AbstractBridgedCompositeByteRandomVariableEditor<IEditableByteRandomVariable, BaseEditableByteRandomVariableWrapper<?, ?, ?>, ProbabilityStrategies, Bridged>
{
	protected ProbabilityStrategies _defaultProbabilityStrategy;

	public BridgedUnorderedCompositeByteRandomVariableEditor(final Bridged bridged, final ProbabilityStrategies defaultProbabilityStrategy)
	{
		super(bridged);
		_defaultProbabilityStrategy = defaultProbabilityStrategy;
	}

	public BridgedUnorderedCompositeByteRandomVariableEditor(final Bridged bridged)
	{
		this(bridged, ProbabilityStrategies.FIXED);
	}

	protected ProbabilityStrategies getDefaultProbabilityStrategy()
	{
		return _defaultProbabilityStrategy;
	}

	@Override
	protected BaseEditableByteRandomVariableWrapper<?, ?, ?> getNewChild(
			final byte value, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		return getNewChild(new FixedProbabilityByteWrapper(value), probability, probabilityStrategy);
	}

	@Override
	protected BaseEditableByteRandomVariableWrapper<?, ?, ?> getNewChild(
			final IEditableByteRandomVariable randomVariable, final int probability,
			final @Nullable ProbabilityStrategies probabilityStrategy)
	{
		final IBridgedEditableProbabilityStrategy<? super BaseByteRandomVariableWrapper<?, ?, ?>> newProbabilityStrategy;
		if(probabilityStrategy!=null)
			newProbabilityStrategy = probabilityStrategy.getNewProbabilityStrategy(probability);
		else
			newProbabilityStrategy = getDefaultProbabilityStrategy(probability);
		final BaseEditableByteRandomVariableWrapper<IEditableByteRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseByteRandomVariableWrapper<?, ?, ?>>> newWrapper =
				new BaseEditableByteRandomVariableWrapper<IEditableByteRandomVariable, Bridged, IBridgedEditableProbabilityStrategy<? super BaseByteRandomVariableWrapper<?, ?, ?>>>(randomVariable, _bridged, newProbabilityStrategy);
		newProbabilityStrategy.init(newWrapper);
		return newWrapper;
	}

	protected IBridgedEditableProbabilityStrategy<? super BaseByteRandomVariableWrapper<?, ?, ?>> getDefaultProbabilityStrategy(
			final int probability)
	{
		return getDefaultProbabilityStrategy().getNewProbabilityStrategy(probability);
	}
}