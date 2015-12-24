/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyIntRandomVariableWrapper
extends BaseIntRandomVariableWrapper<IIntRandomVariable, AbstractCompositeIntRandomVariable<?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyIntRandomVariableWrapper(final BaseIntRandomVariableWrapper<?, ?, ?> base)
	{
		super(base.getElement(), base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}