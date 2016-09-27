/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleCompositeLongSet<ChildType extends IElementaryLongSet>
extends ISimpleCompositeSortedSet
<
	ILongBound,
	ILongSet,
	ISimpleLongSet,
	IElementaryLongSet,
	ICompositeLongSet<?>,
	ISimpleCompositeLongSet<ChildType>,
	ChildType
>,
ISimpleLongSet
{}