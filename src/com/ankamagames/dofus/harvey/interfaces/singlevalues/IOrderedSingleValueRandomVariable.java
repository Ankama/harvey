/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.singlevalues;

import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedSingleValueRandomVariable<Data>
extends IOrderedRandomVariable<Data>,
ISingleValueRandomVariable<Data>
{
	@Override
	public @Nullable IOrderedSingleValueRandomVariable<Data> getLowerThan(@Nullable Data value);

	@Override
	public @Nullable IOrderedSingleValueRandomVariable<Data> getLowerThanOrEqualTo(@Nullable Data value);

	@Override
	public @Nullable IOrderedSingleValueRandomVariable<Data> getGreaterThan(@Nullable Data value);

	@Override
	public @Nullable IOrderedSingleValueRandomVariable<Data> getGreaterThanOrEqualTo(@Nullable Data value);
}