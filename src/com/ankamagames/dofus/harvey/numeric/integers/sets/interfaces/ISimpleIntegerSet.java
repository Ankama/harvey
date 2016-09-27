/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleIntegerSet
extends ISimpleSortedSet<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet>, IIntegerSet
{}