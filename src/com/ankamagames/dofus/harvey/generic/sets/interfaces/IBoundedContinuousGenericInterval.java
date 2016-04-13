/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBoundedInterval;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IBoundedContinuousGenericInterval<Data>
extends ILeftBoundedContinuousGenericInterval<Data>,
IRightBoundedContinuousGenericInterval<Data>,
IBoundedInterval<IContinuousGenericSet<Data>>
{}