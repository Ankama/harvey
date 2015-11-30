/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.parented;

import com.ankamagames.dofus.harvey.engine.inetrfaces.parented.IHasParent;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IParentedRandomVariable<Data, ParentType extends IParentingRandomVariable<Data, ?>>
	extends IRandomVariable<Data>, IHasParent<Data, ParentType>
{}