/**
 *
 */
package com.ankamagames.dofus.harvey.engine.editors;

import com.ankamagames.dofus.harvey.engine.editors.AbstractRandomVariableEditor.EditorToken;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IRandomVariableEditor<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>>
{
	void setParent(@Nullable ParentType parent) throws OverOneProbabilityException;
	EditorToken getEditorTocken();
}
