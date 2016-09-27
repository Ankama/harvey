/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeLongSet<ChildType extends ILongSet>
extends ILongSet, ICompositeSortedSet<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ICompositeLongSet<?>, ChildType>
{}