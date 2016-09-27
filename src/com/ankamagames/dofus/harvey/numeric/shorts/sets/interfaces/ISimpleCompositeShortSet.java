/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleCompositeShortSet<ChildType extends IElementaryShortSet>
extends ISimpleCompositeSortedSet
<
	IShortBound,
	IShortSet,
	ISimpleShortSet,
	IElementaryShortSet,
	ICompositeShortSet<?>,
	ISimpleCompositeShortSet<ChildType>,
	ChildType
>,
ISimpleShortSet
{}