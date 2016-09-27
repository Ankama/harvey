/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleSortedGenericSet<Data>
extends ISimpleSortedSet<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>>, ISortedGenericSet<Data>
{}