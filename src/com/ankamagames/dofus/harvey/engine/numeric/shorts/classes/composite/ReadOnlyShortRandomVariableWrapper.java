/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyShortRandomVariableWrapper
extends BaseShortRandomVariableWrapper<IShortRandomVariable, AbstractCompositeShortRandomVariable<?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyShortRandomVariableWrapper(final BaseShortRandomVariableWrapper<?, ?, ?> base)
	{
		super(base.getElement(), base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}