/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeContinuousByteSet<ChildType extends IContinuousByteSet>
extends ICompositeContinuousSet<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, ICompositeContinuousByteSet<?>, ChildType>, IContinuousByteSet
{
	public List<? extends IContinuousByteSet> splitInParts(int parts);
}