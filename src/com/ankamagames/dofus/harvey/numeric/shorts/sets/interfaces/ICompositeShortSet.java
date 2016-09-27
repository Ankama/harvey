/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeShortSet<ChildType extends IShortSet>
extends IShortSet, ICompositeSortedSet<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, ICompositeShortSet<?>, ChildType>
{}