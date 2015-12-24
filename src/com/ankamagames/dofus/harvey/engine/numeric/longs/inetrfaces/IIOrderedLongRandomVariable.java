package com.ankamagames.dofus.harvey.engine.numeric.longs.inetrfaces;
/**
 *
 */


import com.ankamagames.dofus.harvey.numeric.longs.interfaces.IOrderedLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIOrderedLongRandomVariable
extends IISimpleOrderedLongRandomVariable
{
	@Nullable IOrderedLongRandomVariable getLowerThan(long value);
	@Nullable IOrderedLongRandomVariable getLowerThanOrEqualTo(long value);
	@Nullable IOrderedLongRandomVariable getGreaterThan(long value);
	@Nullable IOrderedLongRandomVariable getGreaterThanOrEqualTo(long value);
}