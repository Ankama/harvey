/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleCompositeByteSet<ChildType extends IElementaryByteSet>
extends ISimpleCompositeSortedSet
<
	IByteBound,
	IByteSet,
	ISimpleByteSet,
	IElementaryByteSet,
	ICompositeByteSet<?>,
	ISimpleCompositeByteSet<ChildType>,
	ChildType
>,
ISimpleByteSet
{}