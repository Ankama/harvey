/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.generic.interfaces.IGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyGenericRandomVariableWrapper<Data>
extends BaseGenericRandomVariableWrapper<Data, IGenericRandomVariable<Data>, AbstractCompositeGenericRandomVariable<Data, ?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyGenericRandomVariableWrapper(final BaseGenericRandomVariableWrapper<Data, ?, ?, ?> base)
	{
		super(base.getElement(), base.getParent(), new ReadOnlyProbabilityStrategy(base.getProbabilityStrategy()));
	}
}