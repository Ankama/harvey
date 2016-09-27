/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeContinuousLongSet<ChildType extends IContinuousLongSet>
extends ICompositeContinuousSet<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, ICompositeContinuousLongSet<?>, ChildType>, IContinuousLongSet
{
	public List<? extends IContinuousLongSet> splitInParts(int parts);
}