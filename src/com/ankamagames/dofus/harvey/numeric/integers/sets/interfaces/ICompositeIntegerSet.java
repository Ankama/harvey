/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeIntegerSet<ChildType extends IIntegerSet>
extends IIntegerSet, ICompositeSortedSet<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, ICompositeIntegerSet<?>, ChildType>
{}