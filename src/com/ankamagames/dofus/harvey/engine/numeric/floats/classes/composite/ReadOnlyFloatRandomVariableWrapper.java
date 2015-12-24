/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyFloatRandomVariableWrapper
extends BaseFloatRandomVariableWrapper<IFloatRandomVariable, AbstractCompositeFloatRandomVariable<?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyFloatRandomVariableWrapper(final BaseFloatRandomVariableWrapper<?, ?, ?> base)
	{
		super(base.getElement(), base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}