/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours.singlevalue;

import com.ankamagames.dofus.harvey.engine.behaviours.IRandomVariableBehaviour;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISingleValueBehaviour<Data>
	extends IRandomVariableBehaviour<Data>
{
	@Nullable Data getValue();
}
