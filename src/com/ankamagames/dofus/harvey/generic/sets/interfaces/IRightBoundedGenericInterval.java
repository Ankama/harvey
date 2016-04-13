/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IRightBoundedInterval;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IRightBoundedGenericInterval<Data>
	extends IGenericInterval<Data>, IRightBoundedInterval<IOrderedGenericSet<Data>>
{}