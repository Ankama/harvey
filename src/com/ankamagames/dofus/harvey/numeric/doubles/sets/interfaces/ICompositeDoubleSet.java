/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeDoubleSet<ChildType extends IDoubleSet>
extends IDoubleSet, ICompositeContinuousSet<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, ICompositeDoubleSet<?>, ChildType>
{
	public List<? extends IDoubleSet> splitInParts(int parts);

}