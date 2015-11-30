/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces.parented;

import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IHasParent<Data, ParentType extends IRandomVariable<Data>>
{
	@Nullable ParentType getParent();
}