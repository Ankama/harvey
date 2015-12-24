/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyDoubleRandomVariableWrapper
extends BaseDoubleRandomVariableWrapper<IDoubleRandomVariable, AbstractCompositeDoubleRandomVariable<?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyDoubleRandomVariableWrapper(final BaseDoubleRandomVariableWrapper<?, ?, ?> base)
	{
		super(base.getElement(), base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}