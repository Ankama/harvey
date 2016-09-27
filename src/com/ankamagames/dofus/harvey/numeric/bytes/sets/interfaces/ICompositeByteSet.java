/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeByteSet<ChildType extends IByteSet>
extends IByteSet, ICompositeSortedSet<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, ICompositeByteSet<?>, ChildType>
{}