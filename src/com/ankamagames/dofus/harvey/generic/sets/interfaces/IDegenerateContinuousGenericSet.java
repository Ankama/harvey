/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateContinuousSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateContinuousGenericSet<Data>
	extends IContinuousGenericSet<Data>, IDegenerateContinuousSet<IContinuousGenericSet<Data>>
{
	@Nullable Data getValue();
}