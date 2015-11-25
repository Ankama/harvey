/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.composite;

import com.ankamagames.dofus.harvey.engine.inetrfaces.composite.IIParentedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IParentedRandomVariable<Data, ParentType extends IRandomVariable<Data>>
	extends IRandomVariable<Data>, IIParentedRandomVariable<Data, ParentType>
{}