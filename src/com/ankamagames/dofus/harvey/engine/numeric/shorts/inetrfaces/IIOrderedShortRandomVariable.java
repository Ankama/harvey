package com.ankamagames.dofus.harvey.engine.numeric.shorts.inetrfaces;
/**
 *
 */


import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IOrderedShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIOrderedShortRandomVariable
extends IISimpleOrderedShortRandomVariable
{
	@Nullable IOrderedShortRandomVariable getLowerThan(short value);
	@Nullable IOrderedShortRandomVariable getLowerThanOrEqualTo(short value);
	@Nullable IOrderedShortRandomVariable getGreaterThan(short value);
	@Nullable IOrderedShortRandomVariable getGreaterThanOrEqualTo(short value);
}