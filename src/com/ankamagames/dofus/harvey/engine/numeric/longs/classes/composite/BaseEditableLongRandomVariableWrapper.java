/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.IEditableLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableLongRandomVariableWrapper
<
	ChildType extends IEditableLongRandomVariable,
	ParentType extends AbstractCompositeLongRandomVariable<?>&IEditableLongRandomVariable,
	ProbabilityStrategy extends IEditableProbabilityStrategy
>
extends BaseLongRandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>
implements IEditableLongRandomVariable
{
	BridgedLongRandomVariableWrapperEditor<?> _editor;

	public BaseEditableLongRandomVariableWrapper(final ChildType element, final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
		_editor = new BridgedLongRandomVariableWrapperEditor<BaseEditableLongRandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>>(this);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final long value, final int probability)
	{
		return _editor.setProbabilityOf(value, probability);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final long value)
	{
		return _editor.remove(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final long value, final int delta)
	{
		return _editor.addProbabilityTo(value, delta);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(final long value, final int delta)
	{
		return _editor.removeProbabilityTo(value, delta);
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