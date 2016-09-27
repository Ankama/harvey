package com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes;

import java.util.Arrays;
import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.CheckedSetArrayList;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISetCreationToolbox;
import com.ankamagames.dofus.harvey.generic.sets.classes.DegenerateGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.EmptyGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.GenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.MutableCompositeGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptyGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public abstract class AbstractGenericSetCreationToolbox<Data, BridgedType extends IGenericSet<Data>>
extends AbstractSetBridge<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, BridgedType>
implements ISetCreationToolbox<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, IEmptyGenericSet<Data>, BridgedType>
{

	public AbstractGenericSetCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public EmptyGenericSet<Data> makeEmptySet()
	{
		return EmptyGenericSet.getInstance();
	}

	@Override
	public ISimpleGenericSet<Data> makeSimplestSet(final Collection<IElementaryGenericSet<Data>> elements)
	{
		final CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>> checkedCollection = CheckedSetArrayList.makeCheckedSetArrayList(elements);;
		if(elements.isEmpty())
			return makeEmptySet();
		if(elements.size() == 1)
		{
			final IElementaryGenericSet<Data> element = elements.iterator().next().getAsElementarySet();
			if(element.isEmpty())
				return makeEmptySet();
			if(element.isDegenerate())
				return DegenerateGenericSet.makeSet(element.getDataIterator().next());
			else
				return element;
		}
		return GenericSet.makeSet(checkedCollection);
	}

	@Override
	public IGenericSet<Data> makeComposite(final IGenericSet<Data>... sets) {
		return makeComposite(Arrays.asList(sets));
	}

	@SuppressWarnings("unchecked")
	@Override
	public IGenericSet<Data> makeComposite(final Collection<? extends IGenericSet<Data>> children)
	{
		final CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>> checkedCollection = CheckedSetArrayList.makeCheckedSetArrayList();
		checkedCollection.addAllSets(children);
		if(children.size()<=0)
			return makeEmptySet();
		for(final IGenericSet<Data> child:children)
			if(!child.isElementary())
				return MutableCompositeGenericSet.makeSet(checkedCollection);
		return GenericSet.makeSet(checkedCollection);
	}
}
