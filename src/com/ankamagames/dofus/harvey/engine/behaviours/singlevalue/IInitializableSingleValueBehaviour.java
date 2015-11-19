/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours.singlevalue;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IInitializableSingleValueBehaviour<Data>
	extends ISingleValueBehaviour<Data>
{
	void init(@Nullable Data value);
}
