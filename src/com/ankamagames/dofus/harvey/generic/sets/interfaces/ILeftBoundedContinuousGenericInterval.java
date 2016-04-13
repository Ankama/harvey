/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ILeftBoundedContinuousInterval;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ILeftBoundedContinuousGenericInterval<Data>
extends ILeftBoundedContinuousInterval<IContinuousGenericSet<Data>>, IContinuousGenericInterval<Data>
{}