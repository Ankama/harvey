/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.interfaces;

import com.ankamagames.dofus.harvey.engine.editors.IRandomVariableEditor;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableCompositeRandomVariableWithEditor<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, ChildType extends IRandomVariable<Data, ?>, Editor extends IRandomVariableEditor<Data, ParentType>>
	extends IEditableCompositeRandomVariable<Data, ParentType, ChildType>,
	IEditableRandomVariableWithEditor<Data, ParentType, Editor>
{}
