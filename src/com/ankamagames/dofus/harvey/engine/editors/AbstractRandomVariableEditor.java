package com.ankamagames.dofus.harvey.engine.editors;

import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public abstract class AbstractRandomVariableEditor<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>, BridgedType extends IEditableRandomVariable<Data, ParentType>>
implements IRandomVariableEditor<Data, ParentType>
{
	public static final class EditorToken{ private EditorToken(){} }
	protected static EditorToken editorToken = new EditorToken();

	protected BridgedType _bridged;

	public AbstractRandomVariableEditor(final BridgedType bridged)
	{
		_bridged = bridged;
	}

	public AbstractRandomVariableEditor(final BridgedType bridged, final @Nullable ParentType parent) throws OverOneProbabilityException
	{
		_bridged = bridged;
		setParent(parent);
	}

	@Override
	public EditorToken getEditorTocken()
	{
		return editorToken;
	}
}
