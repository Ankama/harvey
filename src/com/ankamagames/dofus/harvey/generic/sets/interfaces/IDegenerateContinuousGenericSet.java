/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateContinuousGenericSet<Data>
extends IDegenerateContinuousSet<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IDegenerateContinuousGenericSet<Data>>,
IContinuousGenericInterval<Data>
{
	@Nullable Data getValue();

	@Override
	Iterator<? extends IDegenerateContinuousGenericSet<Data>> iterator();

	@Override
	IDegenerateContinuousGenericSet<Data> getSimpleSet();
}