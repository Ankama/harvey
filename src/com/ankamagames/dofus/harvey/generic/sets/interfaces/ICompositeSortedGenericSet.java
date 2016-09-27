/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeSortedGenericSet<Data, ChildType extends ISortedGenericSet<Data>>
extends ISortedGenericSet<Data>, ICompositeSortedSet<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ICompositeSortedGenericSet<Data, ?>, ChildType>
{}