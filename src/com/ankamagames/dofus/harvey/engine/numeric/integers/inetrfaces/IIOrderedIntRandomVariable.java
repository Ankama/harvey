package com.ankamagames.dofus.harvey.engine.numeric.integers.inetrfaces;
/**
 *
 */


import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IOrderedIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIOrderedIntRandomVariable
extends IISimpleOrderedIntRandomVariable
{
	@Nullable IOrderedIntRandomVariable getLowerThan(int value);
	@Nullable IOrderedIntRandomVariable getLowerThanOrEqualTo(int value);
	@Nullable IOrderedIntRandomVariable getGreaterThan(int value);
	@Nullable IOrderedIntRandomVariable getGreaterThanOrEqualTo(int value);
}