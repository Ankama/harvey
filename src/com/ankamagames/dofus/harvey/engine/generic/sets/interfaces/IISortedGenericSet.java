/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IISortedGenericSet
<
	Data,
	Set extends ISortedSet<IGenericBound<Data>, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<IGenericBound<Data>, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<IGenericBound<Data>, Set, SimpleSet, ElementarySet>
>
extends IIGenericSet<Data, Set, SimpleSet, ElementarySet>,
ICommonSortedGenericSet<Data, IGenericBound<Data>, Set, SimpleSet, ElementarySet>,
ISortedSet<IGenericBound<Data>, Set, SimpleSet, ElementarySet>
{
	Iterator<Data> getReverseDataIterator();
}
