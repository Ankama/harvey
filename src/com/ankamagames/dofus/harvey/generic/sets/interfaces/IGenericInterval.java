/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IGenericInterval<Data>
extends
IInterval<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>>,
ISortedGenericSet<Data>,
IElementarySortedGenericSet<Data>
{
	@Override
	IGenericInterval<Data> getSimpleSet();
}