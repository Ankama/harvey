/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.randomvariables.classes;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.SetUtils;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.classes.AbstractRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.IGenericElementaryEvent;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.IGenericRandomVariable;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseGenericRandomVariable<Data, Elements extends IGenericSet<Data>>
	extends AbstractRandomVariable<IGenericSet<Data>, IGenericElementaryEvent<Data, ?>, Elements, FixedProbability>
	implements IGenericRandomVariable<Data, Elements>
{
	public static <Data, Elements extends IGenericSet<Data>> BaseGenericRandomVariable<Data, Elements> makeRandomVariable(final Elements elements, final int probability)
	{
		return new BaseGenericRandomVariable<Data, Elements>(elements, new FixedProbability(probability));
	}
	
	private BaseGenericRandomVariable(final Elements elements, final FixedProbability probability)
	{
		super(elements, probability);
	}

	@Override
	public int getProbability(final IGenericSet<Data> set)
	{
		final IGenericSet<Data> intersection = SetUtils.getIntersection(getElements(), set);
		return (int)(getProbability() * (intersection.size()/getElements().size()));
	}

	@Override
	public int getProbabilityOf(final Data value)
	{
		if(getElements().contains(value))
			return (int)(getProbability()/getElements().size());
		return 0;
	}

	@Override
	public Iterator<IGenericElementaryEvent<Data, ?>> iterator()
	{
		return new Iterator<IGenericElementaryEvent<Data,?>>()
			{
				int proba = (int)(getProbability()/getElements().size());
				final Iterator<Data> it = getElements().iterator();
							
				@Override
				public boolean hasNext()
				{
					return it.hasNext();
				}

				@Override
				public IGenericElementaryEvent<Data, ?> next()
				{
					return BaseGenericElementaryEvent.makeElementaryEvent(it.next(), proba);
				}

				@Override
				public void remove()
				{
					throw new UnsupportedOperationException();
				}
			};
	}
}
