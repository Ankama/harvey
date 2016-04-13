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
public interface IBoundedGenericInterval<Data>
extends ILeftBoundedGenericInterval<Data>,
IRightBoundedGenericInterval<Data>,
IBoundedInterval<IOrderedGenericSet<Data>>
{}