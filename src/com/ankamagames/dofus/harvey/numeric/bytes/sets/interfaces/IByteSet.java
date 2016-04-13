/**
 * 
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IByteSet<Elements extends IByteSet<?>>
	extends IOrderedSet<IByteSet<?>, Elements>
{
	byte getLowerBound();
	
	byte getUpperBound();
	
	boolean isGreaterThan(byte value);

	boolean isGreaterThanOrEqualTo(byte value);

	boolean hasValueLowerThan(byte value);

	boolean isLowerThan(byte value);

	boolean isLowerThanOrEqualTo(byte value);

	boolean hasValueGreaterThan(byte value);
	
	IByteSet<Elements> getGreaterThan(byte value);
	
	IByteSet<Elements> getGreaterThanOrEqualTo(byte value);
	
	IByteSet<Elements> getLowerThan(byte value);
	
	IByteSet<Elements> getLowerThanOrEqualTo(byte value);
}