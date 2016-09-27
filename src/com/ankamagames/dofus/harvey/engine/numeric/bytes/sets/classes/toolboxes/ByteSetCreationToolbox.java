/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.ByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.ByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.DegenerateByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.EmptyByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.MutableCompositeByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IDegenerateByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IEmptyByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ByteSetCreationToolbox<BridgedType extends IByteSet>
extends AbstractSetBridge<IByteSet, ISimpleByteSet, IElementaryByteSet, BridgedType>
implements ISortedSetCreationToolbox<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IEmptyByteSet, BridgedType>
{

	public ByteSetCreationToolbox(final BridgedType bridged)
	{
		super(bridged);
	}

	@Override
	public EmptyByteSet makeEmptySet()
	{
		return EmptyByteSet.getInstance();
	}

	@Override
	public ISimpleSet<IByteSet, ISimpleByteSet, IElementaryByteSet> makeSimplestSet(
		final Collection<IElementaryByteSet> elements)
	{
		if(elements.isEmpty())
			return makeEmptySet();
		if(elements.size() == 1)
		{
			final IElementaryByteSet element = elements.iterator().next().getAsElementarySet();
			if(element.isEmpty())
				return makeEmptySet();
			if(element.isDegenerate())
				return DegenerateByteSet.makeSet(element.getDataIterator().next());
			else
				return element;
		}
		return ByteSet.makeSet(elements);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IByteSet makeComposite(final Collection<? extends IByteSet> children)
	{
		if(children.size()<=0)
			return makeEmptySet();
		for(final IByteSet child:children)
			if(!child.isElementary())
				return MutableCompositeByteSet.makeSet(children);
		return ByteSet.makeSet((List<? extends IElementaryByteSet>)children);
	}

	@Override
	public IByteSet makeComposite(final IByteSet... sets)
	{
		return makeComposite(Arrays.asList(sets));
	}

	@Override
	public IByteInterval makeInterval(
		final IByteBound lowerBound, final IByteBound upperBound)
	{
		return ByteInterval.makeInterval(lowerBound.getValue(), upperBound.getValue());
	}

	@Override
	public IDegenerateByteSet makeDegenerate(
			final IByteBound bound)
	{
		return DegenerateByteSet.makeSet(bound.getValue());
	}
}