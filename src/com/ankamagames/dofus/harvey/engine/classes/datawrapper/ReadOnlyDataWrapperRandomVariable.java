/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ReadOnlyDataWrapperRandomVariable<Data>
extends BaseDataWrapperRandomVariable<Data, IProbabilityStrategy>
{
	public ReadOnlyDataWrapperRandomVariable(final BaseDataWrapperRandomVariable<Data, ?> base)
	{
		super(base._value, new ReadOnlyProbabilityStrategy(base._probabilityStrategy));
	}
}
