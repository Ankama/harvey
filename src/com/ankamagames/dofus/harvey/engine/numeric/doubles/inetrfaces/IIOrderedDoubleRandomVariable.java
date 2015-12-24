package com.ankamagames.dofus.harvey.engine.numeric.doubles.inetrfaces;
/**
 *
 */


import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IOrderedDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIOrderedDoubleRandomVariable
extends IISimpleOrderedDoubleRandomVariable
{
	@Nullable IOrderedDoubleRandomVariable getLowerThan(double value);
	@Nullable IOrderedDoubleRandomVariable getLowerThanOrEqualTo(double value);
	@Nullable IOrderedDoubleRandomVariable getGreaterThan(double value);
	@Nullable IOrderedDoubleRandomVariable getGreaterThanOrEqualTo(double value);
}