/**
 *
 */
package com.ankamagames.dofus.harvey.engine.editors;

import com.ankamagames.dofus.harvey.engine.inetrfaces.composite.IIEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.inetrfaces.composite.IIEditableParentedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeRandomVariableEditor
<
	Data,
	ChildType extends IRandomVariable<Data>&IIEditableParentedRandomVariable<Data, ?>
>
	extends IRandomVariableEditor<Data>, IIEditableCompositeRandomVariable<Data, ChildType>
{}