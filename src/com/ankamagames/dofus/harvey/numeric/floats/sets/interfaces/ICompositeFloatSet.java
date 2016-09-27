/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces;

import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeFloatSet<ChildType extends IFloatSet>
extends IFloatSet, ICompositeContinuousSet<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, ICompositeFloatSet<?>, ChildType>
{
	public List<? extends IFloatSet> splitInParts(int parts);

}