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
public interface IDegenerateGenericInterval<Data>
	extends IDegenerateInterval<IOrderedGenericSet<Data>>,
	IBoundedGenericInterval<Data>,
	IDegenerateOrderedGenericSet<Data>
{}