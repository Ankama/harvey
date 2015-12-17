package com.ankamagames.dofus.harvey.engine.generic.inetrfaces;
/**
 *
 */


import com.ankamagames.dofus.harvey.generic.interfaces.IOrderedGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIOrderedGenericRandomVariable<Data>
extends IISimpleOrderedGenericRandomVariable<Data>
{
	@Nullable IOrderedGenericRandomVariable<Data> getLowerThan(@Nullable Data value);
	@Nullable IOrderedGenericRandomVariable<Data> getLowerThanOrEqualTo(@Nullable Data value);
	@Nullable IOrderedGenericRandomVariable<Data> getGreaterThan(@Nullable Data value);
	@Nullable IOrderedGenericRandomVariable<Data> getGreaterThanOrEqualTo(@Nullable Data value);
}