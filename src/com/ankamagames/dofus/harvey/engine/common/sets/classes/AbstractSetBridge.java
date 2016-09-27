/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

/**
 * This implementation uses "toolboxes" to postpone implementation of set type specific aspects
 * to specialized implementations.
 * As this is not the only way to do things, I put it in bridged classes allowing to easily
 * change this aspect (I hope).
 *
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractSetBridge
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	Bridged extends ISet<Set, SimpleSet, ElementarySet>
>
{
	protected Bridged _bridged;

	public AbstractSetBridge(final Bridged bridged)
	{
		_bridged = bridged;
	}
}