/**
 * 
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateInterval;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateByteInterval<Elements extends IDegenerateByteInterval<?>>
	extends IDegenerateInterval<IByteSet<?>, Elements>, IDegenerateByteSet<Elements>, IBoundedByteInterval<Elements>
{}