/**
 *
 */
package com.ankamagames.dofus.harvey.engine.editors;

import com.ankamagames.dofus.harvey.engine.inetrfaces.composite.IIEditableParentedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IParentedRandomVariableEditor<Data, ParentType extends IRandomVariable<Data>>
	extends IRandomVariableEditor<Data>, IIEditableParentedRandomVariable<Data, ParentType>
{}