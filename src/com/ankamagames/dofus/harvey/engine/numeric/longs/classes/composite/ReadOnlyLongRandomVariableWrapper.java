/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.ILongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyLongRandomVariableWrapper
extends BaseLongRandomVariableWrapper<ILongRandomVariable, AbstractCompositeLongRandomVariable<?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyLongRandomVariableWrapper(final BaseLongRandomVariableWrapper<?, ?, ?> base)
	{
		super(base.getElement(), base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}