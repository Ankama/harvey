/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.parented;

import com.ankamagames.dofus.harvey.engine.inetrfaces.parented.IIEditableParentedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableParentedRandomVariable<Data, ParentType extends IRandomVariable<Data>>
	extends IRandomVariable<Data>,
	IIEditableParentedRandomVariable<Data, ParentType>
{}