/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleCompositeIntegerSet<ChildType extends IElementaryIntegerSet>
extends ISimpleCompositeSortedSet
<
	IIntegerBound,
	IIntegerSet,
	ISimpleIntegerSet,
	IElementaryIntegerSet,
	ICompositeIntegerSet<?>,
	ISimpleCompositeIntegerSet<ChildType>,
	ChildType
>, ISimpleIntegerSet
{}