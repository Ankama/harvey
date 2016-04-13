/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IGenericInterval<Data>
	extends IOrderedGenericSet<Data>, IInterval<IOrderedGenericSet<Data>>
{}