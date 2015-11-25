/**
 *
 */
package com.ankamagames.dofus.harvey.engine.editors;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.inetrfaces.IIRandomVariableDecorator;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.IEditableParentedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ParentedRandomVariableEditor
<
	Data,
	ParentType extends IRandomVariable<Data>,
	Bridged extends IEditableParentedRandomVariable<Data, ParentType>&IIRandomVariableDecorator<Data, ? extends IEditableRandomVariable<Data>>
>
implements IRandomVariableEditor<Data>
{
	protected Bridged _bridged;

	public ParentedRandomVariableEditor(final Bridged bridged)
	{
		_bridged = bridged;
	}

	@Override
	public void setProbabilityOf(final @Nullable Data value, final int probability) throws OverOneProbabilityException
	{
		_bridged.getDecoratedRandomVariable().setProbabilityOf(value, RandomVariableUtils.divideFixedPrecision(probability, _bridged.getProbability()));
	}

	@Override
	public boolean remove(final @Nullable Data value)
	{
		return _bridged.getDecoratedRandomVariable().remove(value);
	}

	@Override
	public void add(final @Nullable Data value, final int probability)
	{
		_bridged.getDecoratedRandomVariable().add(value, RandomVariableUtils.divideFixedPrecision(probability, _bridged.getProbability()));
	}

	@Override
	public void addProbabilityTo(final @Nullable Data value, final int delta)
	{
		_bridged.getDecoratedRandomVariable().addProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, _bridged.getProbability()));
	}

	@Override
	public void removeProbabilityTo(final @Nullable Data value, final int delta)
	{
		_bridged.getDecoratedRandomVariable().removeProbabilityTo(value, RandomVariableUtils.divideFixedPrecision(delta, _bridged.getProbability()));
	}
}