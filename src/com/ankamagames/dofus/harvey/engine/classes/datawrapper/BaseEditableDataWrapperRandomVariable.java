/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableDataWrapperRandomVariable<Data, ProbabilityStrategy extends IEditableProbabilityStrategy>
extends BaseDataWrapperRandomVariable<Data, ProbabilityStrategy>
implements IEditableRandomVariable<Data>
{
	BridgedDataWrapperRandomVariableEditor<Data, ?> _editor;

	public BaseEditableDataWrapperRandomVariable(final @Nullable Data value, final ProbabilityStrategy probabilityStrategy)
	{
		super(value, probabilityStrategy);
		_editor = new BridgedDataWrapperRandomVariableEditor<Data, BaseEditableDataWrapperRandomVariable<Data, ProbabilityStrategy>>(this);
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