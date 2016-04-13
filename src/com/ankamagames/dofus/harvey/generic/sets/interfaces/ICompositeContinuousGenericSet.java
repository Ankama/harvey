/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeContinuousSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeContinuousGenericSet<Data, ChildType extends IContinuousGenericSet<Data>>
	extends IContinuousGenericSet<Data>, ICompositeContinuousSet<IContinuousGenericSet<Data>, ChildType>
{
	@Override
	ICompositeContinuousGenericSet<Data, ?> getMergedSet();
}