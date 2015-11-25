/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces.singlevalues;

import org.eclipse.jdt.annotation.NonNullByDefault;

import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIEditableSingleValueRandomVariable<Data>
{
	void setValue(@Nullable Data value);
}
