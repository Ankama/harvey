/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface ICommonGenericSet
<
Data,
Set extends ISet<Set, SimpleSet, ElementarySet>,
SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>
>
extends ISet<Set, SimpleSet, ElementarySet>
{
	boolean contains(@Nullable Data value);
	@Override
	Set unite(Set set);
	@Override
	Set intersect(Set set);
	@Override
	Set subtract(Set set);
	@Override
	Set symmetricSubtract(Set set);
	@Override
	Iterator<? extends ElementarySet> iterator();
	@Override
	SimpleSet getSimpleSet();
}
