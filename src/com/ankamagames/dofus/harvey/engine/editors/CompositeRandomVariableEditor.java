/**
 *
 */
package com.ankamagames.dofus.harvey.engine.editors;

import com.ankamagames.dofus.harvey.engine.exceptions.ProbabilityOutOfBoundException;
import com.ankamagames.dofus.harvey.engine.inetrfaces.parentedwithprobability.IEditableParentedRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.composite.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parenting.IEditableParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CompositeRandomVariableEditor
<
	Data,
	ParentType extends IEditableParentingRandomVariable<Data, ?>,
	ChildType extends IEditableParentedRandomVariableWithProbabilityStrategy<Data, ParentType, ?>,
	Bridged extends IEditableCompositeRandomVariable<Data, ParentType, ChildType, ?>
>
	extends ParentingRandomVariableEditor<Data, ChildType, Bridged>
	implements ICompositeRandomVariableEditor<Data, ChildType, Bridged>
{
	public CompositeRandomVariableEditor(final Bridged bridged)
	{
		super(bridged);
	}

	@Override
	public void setProbabilityOf(@Nullable final Data value, final int probability)
			throws ProbabilityOutOfBoundException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean remove(@Nullable final Data value)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(@Nullable final Data value, final int probability)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void addProbabilityTo(@Nullable final Data value, final int delta)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void removeProbabilityTo(@Nullable final Data value, final int delta)
	{
		// TODO Auto-generated method stub

	}
}
