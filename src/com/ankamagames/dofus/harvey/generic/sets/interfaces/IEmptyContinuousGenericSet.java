/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptyContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEmptyContinuousGenericSet<Data>
extends IEmptyContinuousSet<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IEmptyContinuousGenericSet<Data>>,
IContinuousGenericSet<Data>,
IContinuousGenericInterval<Data>
{
	@Override
	Iterator<? extends IEmptyContinuousGenericSet<Data>> iterator();
	@Override
	IEmptyContinuousGenericSet<Data> intersect(IContinuousGenericSet<Data> set);
	@Override
	IEmptyContinuousGenericSet<Data> subtract(IContinuousGenericSet<Data> set);
	@Override
	IEmptyContinuousGenericSet<Data> getSimpleSet();
}