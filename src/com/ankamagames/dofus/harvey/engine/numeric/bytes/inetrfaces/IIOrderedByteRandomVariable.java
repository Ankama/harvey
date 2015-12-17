package com.ankamagames.dofus.harvey.engine.numeric.bytes.inetrfaces;
/**
 *
 */


import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IOrderedByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIOrderedByteRandomVariable
extends IISimpleOrderedByteRandomVariable
{
	@Nullable IOrderedByteRandomVariable getLowerThan(byte value);
	@Nullable IOrderedByteRandomVariable getLowerThanOrEqualTo(byte value);
	@Nullable IOrderedByteRandomVariable getGreaterThan(byte value);
	@Nullable IOrderedByteRandomVariable getGreaterThanOrEqualTo(byte value);
}