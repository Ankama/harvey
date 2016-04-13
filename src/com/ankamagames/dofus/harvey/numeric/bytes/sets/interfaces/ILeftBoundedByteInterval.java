/**
 * 
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ILeftBoundedInterval;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ILeftBoundedByteInterval<Elements extends IByteSet<?>>
	extends ILeftBoundedInterval<IByteSet<?>, Elements>, IByteInterval<Elements>
{
	byte getLowerBound();
}