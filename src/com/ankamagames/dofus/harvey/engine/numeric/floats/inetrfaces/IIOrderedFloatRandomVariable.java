package com.ankamagames.dofus.harvey.engine.numeric.floats.inetrfaces;
/**
 *
 */


import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IOrderedFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIOrderedFloatRandomVariable
extends IISimpleOrderedFloatRandomVariable
{
	@Nullable IOrderedFloatRandomVariable getLowerThan(float value);
	@Nullable IOrderedFloatRandomVariable getLowerThanOrEqualTo(float value);
	@Nullable IOrderedFloatRandomVariable getGreaterThan(float value);
	@Nullable IOrderedFloatRandomVariable getGreaterThanOrEqualTo(float value);
}