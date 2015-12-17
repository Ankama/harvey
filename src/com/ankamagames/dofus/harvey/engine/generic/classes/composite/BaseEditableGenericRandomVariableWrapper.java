/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.generic.interfaces.IEditableGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableGenericRandomVariableWrapper
<
	Data,
	ChildType extends IEditableGenericRandomVariable<Data>,
	ParentType extends AbstractCompositeGenericRandomVariable<Data, ?>&IEditableGenericRandomVariable<Data>,
	ProbabilityStrategy extends IEditableProbabilityStrategy
>
extends BaseGenericRandomVariableWrapper<Data, ChildType, ParentType, ProbabilityStrategy>
implements IEditableGenericRandomVariable<Data>
{
	BridgedGenericRandomVariableWrapperEditor<Data, ?> _editor;

	public BaseEditableGenericRandomVariableWrapper(final ChildType element, final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
		_editor = new BridgedGenericRandomVariableWrapperEditor<Data, BaseEditableGenericRandomVariableWrapper<Data, ChildType, ParentType, ProbabilityStrategy>>(this);
	}

	@Override
	public boolean containsOnly(@Nullable final Data value)
	{
		return _editor.containsOnly(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(@Nullable final Data value, final int probability)
	{
		return _editor.setProbabilityOf(value, probability);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(@Nullable final Data value)
	{
		return _editor.remove(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#add(java.lang.Object, int)
	 */
	@Override
	public boolean add(@Nullable final Data value, final int probability)
	{
		return _editor.add(value, probability);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(@Nullable final Data value, final int delta)
	{
		return _editor.addProbabilityTo(value, delta);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(@Nullable final Data value, final int delta)
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