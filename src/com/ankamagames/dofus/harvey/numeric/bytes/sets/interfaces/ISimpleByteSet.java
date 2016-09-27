/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleByteSet
extends ISimpleSortedSet<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet>, IByteSet
{}