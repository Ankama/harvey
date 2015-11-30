/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces;

import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIOrderedRandomVariable<Data>
extends IISimpleOrderedRandomVariable<Data>
{
	@Nullable IOrderedRandomVariable<Data> getLowerThan(@Nullable Data value);
	@Nullable IOrderedRandomVariable<Data> getLowerThanOrEqualTo(@Nullable Data value);
	@Nullable IOrderedRandomVariable<Data> getGreaterThan(@Nullable Data value);
	@Nullable IOrderedRandomVariable<Data> getGreaterThanOrEqualTo(@Nullable Data value);
}