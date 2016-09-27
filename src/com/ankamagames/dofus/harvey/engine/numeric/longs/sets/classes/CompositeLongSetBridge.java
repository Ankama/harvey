/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.LongBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.LongEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes.CompositeLongSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ICompositeLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CompositeLongSetBridge<ChildType extends ILongSet>
extends
AbstractCompositeSortedSetBridge
<
	ILongBound,
	ILongSet,
	ISimpleLongSet,
	IElementaryLongSet,
	ICompositeLongSet<?>,
	ChildType,
	BaseCompositeLongSet<ChildType>,
	TreeSet<ChildType>
>
{
	public static class LongSortedSetFactory<ChildType extends ILongSet>
	extends SortedSetFactory<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ChildType, TreeSet<ChildType>>
	{
		@Override
		public TreeSet<ChildType> makeSet(final Comparator<? super ChildType> comparator)
		{
			return new TreeSet<ChildType>(comparator);
		}
	}

	protected LongBoundComparisonToolbox<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, BaseCompositeLongSet<ChildType>> _boundComparisonToolbox;

	protected LongEqualityToolbox<ILongSet, ISimpleLongSet, IElementaryLongSet, BaseCompositeLongSet<ChildType>> _equalityToolbox;

	protected CompositeLongSetCreationToolbox<ChildType, BaseCompositeLongSet<ChildType>> _setCreationToolbox;



	public CompositeLongSetBridge(final BaseCompositeLongSet<ChildType> bridged)
	{
		super(bridged, new LongSortedSetFactory<ChildType>());
		_boundComparisonToolbox = new LongBoundComparisonToolbox<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, BaseCompositeLongSet<ChildType>>(bridged);
		_equalityToolbox = new LongEqualityToolbox<ILongSet, ISimpleLongSet, IElementaryLongSet, BaseCompositeLongSet<ChildType>>(bridged);
		_setCreationToolbox = new CompositeLongSetCreationToolbox<ChildType, BaseCompositeLongSet<ChildType>>(bridged);
	}

	public CompositeLongSetBridge(final BaseCompositeLongSet<ChildType> bridged, final Iterable<ChildType> childrenIterable)
	{
		super(bridged,
			new LongSortedSetFactory<ChildType>(),
			childrenIterable);
		_boundComparisonToolbox = new LongBoundComparisonToolbox<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, BaseCompositeLongSet<ChildType>>(bridged);
		_equalityToolbox = new LongEqualityToolbox<ILongSet, ISimpleLongSet, IElementaryLongSet, BaseCompositeLongSet<ChildType>>(bridged);
		_setCreationToolbox = new CompositeLongSetCreationToolbox<ChildType, BaseCompositeLongSet<ChildType>>(bridged);
	}

	public  CompositeLongSetBridge(final BaseCompositeLongSet<ChildType> bridged, final ChildType... children)
	{
		this(bridged, Arrays.asList(children));
	}

	@Override
	protected LongBoundComparisonToolbox<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, BaseCompositeLongSet<ChildType>> getBoundComparisonToolbox()
	{
		return _boundComparisonToolbox;
	}

	@Override
	protected LongEqualityToolbox<ILongSet, ISimpleLongSet, IElementaryLongSet, BaseCompositeLongSet<ChildType>> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected CompositeLongSetCreationToolbox<ChildType, BaseCompositeLongSet<ChildType>> getSetCreationToolbox()
	{
		return _setCreationToolbox;
	}
}
