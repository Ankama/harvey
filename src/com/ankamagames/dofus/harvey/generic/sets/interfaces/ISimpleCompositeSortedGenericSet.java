/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleCompositeSortedGenericSet<Data, ChildType extends IElementarySortedGenericSet<Data>>
extends ISimpleCompositeSortedSet
<
	IGenericBound<Data>,
	ISortedGenericSet<Data>,
	ISimpleSortedGenericSet<Data>,
	IElementarySortedGenericSet<Data>,
	ICompositeSortedGenericSet<Data, ?>,
	ISimpleCompositeSortedGenericSet<Data, ChildType>,
	ChildType
>,
ISimpleSortedGenericSet<Data>
{}