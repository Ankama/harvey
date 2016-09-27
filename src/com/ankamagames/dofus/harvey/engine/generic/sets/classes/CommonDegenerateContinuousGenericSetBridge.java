/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.ICommonSortedGenericSet;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIGenericBound;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIGenericBoundFactory;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonDegenerateContinuousGenericSetBridge<Data, Bound extends IContinuousBound<Bound>&IIGenericBound<Data>, Type extends ICommonSortedGenericSet<Data, Bound, ?, ?, ?>>
extends CommonDegenerateSortedGenericSetBridge<Data, Bound, Type>
{
	public CommonDegenerateContinuousGenericSetBridge(final @Nullable Data value, final IIGenericBoundFactory<Data, Bound> boundFactory, final Comparator<? super Data> comparator, final Type emptySet, final Type bridged)
	{
		super(value, boundFactory, comparator, emptySet, bridged);
	}
}