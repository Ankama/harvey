/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ILeftBoundedInterval;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ILeftBoundedGenericInterval<Data>
	extends IGenericInterval<Data>, ILeftBoundedInterval<IOrderedGenericSet<Data>>
{}