/**
 *
 */
package com.ankamagames.dofus.harvey.generic.randomvariables.sets;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.classes.AbstractImpossibleRandomVariable;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.ISortedGenericElementaryEvent;
import com.ankamagames.dofus.harvey.generic.randomvariables.interfaces.ISortedGenericRandomVariable;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class SortedGenericImpossibleRandomVariable<Data>
extends AbstractImpossibleRandomVariable<ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IEmptySortedGenericSet<Data>, ISortedGenericRandomVariable<Data, ?>, ISortedGenericElementaryEvent<Data, ?>>
implements ISortedGenericRandomVariable<Data, IEmptySortedGenericSet<Data>>
{
	@SuppressWarnings("rawtypes")
	private static SortedGenericImpossibleRandomVariable _instance = new SortedGenericImpossibleRandomVariable();

	@SuppressWarnings("unchecked")
	public static <T> SortedGenericImpossibleRandomVariable<T> getInstance()
	{
		return _instance;
	}

	@Override
	public IEmptySortedGenericSet<Data> getElements()
	{
		return EmptySortedGenericSet.getInstance();
	}

	@Override
	public int getProbabilityOf(final @Nullable Data value)
	{
		return 0;
	}


}
