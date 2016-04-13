/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IRightBoundedContinuousInterval;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IRightBoundedContinuousGenericInterval<Data>
extends IRightBoundedContinuousInterval<IContinuousGenericSet<Data>>, IContinuousGenericInterval<Data>
{}