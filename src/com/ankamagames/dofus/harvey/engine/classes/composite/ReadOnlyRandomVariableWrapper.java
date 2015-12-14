/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyRandomVariableWrapper<Data>
extends BaseRandomVariableWrapper<Data, IRandomVariable<Data>, AbstractCompositeRandomVariable<Data, ?>, ReadOnlyProbabilityStrategy>
{
	public ReadOnlyRandomVariableWrapper(final BaseRandomVariableWrapper<Data, ?, ?, ?> base)
	{
		super(base._element, base._parent, new ReadOnlyProbabilityStrategy(base._probabilityStrategy));
	}
}