/**
 *
 */
package com.ankamagames.dofus.harvey.generic.randomvariables.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIGenericRandomVariable<Data>
{
	int getProbabilityOf(@Nullable Data value);
}
