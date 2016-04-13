/**
 * 
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IRightBoundedInterval;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IRightBoundedByteInterval<Elements extends IByteSet<?>>
	extends IRightBoundedInterval<IByteSet<?>, Elements>, IByteInterval<Elements>
{
	byte getUpperBound();
}