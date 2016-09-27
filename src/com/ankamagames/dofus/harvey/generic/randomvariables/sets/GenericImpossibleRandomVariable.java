/**
 *
 */
package com.ankamagames.dofus.harvey.generic.randomvariables.sets;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.classes.AbstractImpossibleRandomVariable;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.IGenericElementaryEvent;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.IGenericRandomVariable;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptyGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptyGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class GenericImpossibleRandomVariable<Data>
extends AbstractImpossibleRandomVariable<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, IEmptyGenericSet<Data>, IGenericRandomVariable<Data, ?>, IGenericElementaryEvent<Data, ?>>
implements IGenericRandomVariable<Data, IEmptyGenericSet<Data>>
{
	@SuppressWarnings("rawtypes")
	private static GenericImpossibleRandomVariable _instance = new GenericImpossibleRandomVariable();

	@SuppressWarnings("unchecked")
	public static <T> GenericImpossibleRandomVariable<T> getInstance()
	{
		return _instance;
	}

	@Override
	public IEmptyGenericSet<Data> getElements()
	{
		return EmptyGenericSet.getInstance();
	}

	@Override
	public int getProbabilityOf(final @Nullable Data value)
	{
		return 0;
	}


}
