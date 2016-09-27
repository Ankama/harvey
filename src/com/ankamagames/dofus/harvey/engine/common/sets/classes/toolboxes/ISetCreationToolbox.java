/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISetCreationToolbox
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	EmptySet extends IEmptySet<Set, SimpleSet, ElementarySet, EmptySet>,
	Bridged extends ISet<Set, SimpleSet, ElementarySet>
>
{
	IEmptySet<Set, SimpleSet, ElementarySet, EmptySet> makeEmptySet();

	Set makeComposite(Set... sets);
	Set makeComposite(Collection<? extends Set> children);

	ISimpleSet<Set, SimpleSet, ElementarySet> makeSimplestSet(Collection<ElementarySet> elements);
}