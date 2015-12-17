/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IEditableBasicCollection;
import com.ankamagames.dofus.harvey.engine.common.interfaces.IIEditableBasicCollection;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IBridgedProbabilityStrategyFactory;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.interfaces.composite.IIEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedCompositeRandomVariableEditor
<
	WrappableBaseCollectionType extends IEditableBasicCollection,
	ChildType extends BasicCollectionWrapper<?, ?, ?>&IEditableBasicCollection,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>,
	Bridged extends AbstractCompositeRandomVariable<ChildType>&IEditableCompositeRandomVariable<WrappableBaseCollectionType, ProbabilityStrategiesEnum>
>
implements IIEditableBasicCollection,IIEditableCompositeRandomVariable<WrappableBaseCollectionType, ProbabilityStrategiesEnum>
{
	protected Bridged _bridged;

	public AbstractBridgedCompositeRandomVariableEditor(final Bridged bridged)
	{
		_bridged = bridged;
	}

	protected abstract ChildType getNewChild(final WrappableBaseCollectionType randomVariable, final int probability, final @Nullable ProbabilityStrategiesEnum probabilityStrategy);

	@Override
	public void add(final WrappableBaseCollectionType randomVariable,
			final int probability, final ProbabilityStrategiesEnum probabilityStrategy)
	{
		_bridged.getElements().add(getNewChild(randomVariable, probability, probabilityStrategy));
	}

	@Override
	public boolean remove(final WrappableBaseCollectionType randomVariable)
	{
		boolean r = false;
		final Iterator<ChildType> it = _bridged.getElements().iterator();
		while(it.hasNext())
		{
			final ChildType element = it.next();
			if(element.getElement().equals(randomVariable))
			{
				it.remove();
				r = true;
			}
		}
		return r;
	}

	@Override
	public void clear()
	{
		_bridged.getElements().clear();
	}
}