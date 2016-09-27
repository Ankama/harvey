/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.DegenerateIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.EmptyIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.IntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.IntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.MutableCompositeIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IDegenerateIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IEmptyIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class IntegerSetCreationToolbox<BridgedType extends IIntegerSet>
extends AbstractSetBridge<IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BridgedType>
implements ISortedSetCreationToolbox<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IEmptyIntegerSet, BridgedType>
{

	public IntegerSetCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public EmptyIntegerSet makeEmptySet()
	{
		return EmptyIntegerSet.getInstance();
	}

	@Override
	public ISimpleSet<IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet> makeSimplestSet(
		final Collection<IElementaryIntegerSet> elements)
	{
		if(elements.isEmpty())
			return makeEmptySet();
		if(elements.size() == 1)
		{
			final IElementaryIntegerSet element = elements.iterator().next().getAsElementarySet();
			if(element.isEmpty())
				return makeEmptySet();
			if(element.isDegenerate())
				return DegenerateIntegerSet.makeSet(element.getDataIterator().next());
			else
				return element;
		}
		return IntegerSet.makeSet(elements);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IIntegerSet makeComposite(final Collection<? extends IIntegerSet> children)
	{
		if(children.size()<=0)
			return makeEmptySet();
		for(final IIntegerSet child:children)
			if(!child.isElementary())
				return MutableCompositeIntegerSet.makeSet(children);
		return IntegerSet.makeSet((List<? extends IElementaryIntegerSet>)children);
	}

	@Override
	public IIntegerSet makeComposite(final IIntegerSet... sets)
	{
		return makeComposite(Arrays.asList(sets));
	}

	@Override
	public IIntegerInterval makeInterval(
		final IIntegerBound lowerBound, final IIntegerBound upperBound)
	{
		return IntegerInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
	}

	@Override
	public IDegenerateIntegerSet makeDegenerate(
			final IIntegerBound bound)
	{
		return DegenerateIntegerSet.makeSet(bound.getValue());
	}
}