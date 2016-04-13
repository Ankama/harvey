/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateInterval;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateContinuousGenericInterval<Data>
	extends IDegenerateInterval<IContinuousGenericSet<Data>>,
	IBoundedContinuousGenericInterval<Data>,
	IDegenerateContinuousGenericSet<Data>
{}