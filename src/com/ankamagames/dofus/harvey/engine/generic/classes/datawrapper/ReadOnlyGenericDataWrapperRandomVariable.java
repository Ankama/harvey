/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyGenericDataWrapperRandomVariable<Data>
extends BaseGenericDataWrapperRandomVariable<Data, IProbabilityStrategy>
{
	public ReadOnlyGenericDataWrapperRandomVariable(final BaseGenericDataWrapperRandomVariable<Data, ?> base)
	{
		super(base._value, new ReadOnlyProbabilityStrategy(base._probabilityStrategy));
	}
}
