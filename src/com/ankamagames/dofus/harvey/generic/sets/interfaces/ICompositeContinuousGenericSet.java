/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeContinuousGenericSet<Data, ChildType extends IContinuousGenericSet<Data>>
extends ICompositeContinuousSet<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, ICompositeContinuousGenericSet<Data, ?>, ChildType>, IContinuousGenericSet<Data>
{
	public List<? extends IContinuousGenericSet<Data>> splitInParts(int parts);
}