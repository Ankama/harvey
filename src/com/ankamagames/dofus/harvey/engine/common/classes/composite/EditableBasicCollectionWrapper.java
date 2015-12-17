/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IEditableBasicCollection;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class EditableBasicCollectionWrapper
<
	ChildType extends IEditableBasicCollection,
	ParentType extends AbstractCompositeRandomVariable<?>&IEditableBasicCollection,
	ProbabilityStrategy extends IEditableProbabilityStrategy
>
extends BasicCollectionWrapper<ChildType, ParentType, ProbabilityStrategy>
implements IEditableBasicCollection
{
	BridgedBasicCollectionWrapperEditor<?> _editor;

	public EditableBasicCollectionWrapper(final ChildType element, final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
		_editor = new BridgedBasicCollectionWrapperEditor<EditableBasicCollectionWrapper<ChildType, ParentType, ProbabilityStrategy>>(this);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#clear()
	 */
	@Override
	public void clear()
	{
		_editor.clear();
	}
}