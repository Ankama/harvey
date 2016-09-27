/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ByteBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.ByteEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes.CompositeByteSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ICompositeByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CompositeByteSetBridge<ChildType extends IByteSet>
extends
AbstractCompositeSortedSetBridge
<
	IByteBound,
	IByteSet,
	ISimpleByteSet,
	IElementaryByteSet,
	ICompositeByteSet<?>,
	ChildType,
	BaseCompositeByteSet<ChildType>,
	TreeSet<ChildType>
>
{
	public static class ByteSortedSetFactory<ChildType extends IByteSet>
	extends SortedSetFactory<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, ChildType, TreeSet<ChildType>>
	{
		@Override
		public TreeSet<ChildType> makeSet(final Comparator<? super ChildType> comparator)
		{
			return new TreeSet<ChildType>(comparator);
		}
	}

	protected ByteBoundComparisonToolbox<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, BaseCompositeByteSet<ChildType>> _boundComparisonToolbox;

	protected ByteEqualityToolbox<IByteSet, ISimpleByteSet, IElementaryByteSet, BaseCompositeByteSet<ChildType>> _equalityToolbox;

	protected CompositeByteSetCreationToolbox<ChildType, BaseCompositeByteSet<ChildType>> _setCreationToolbox;



	public CompositeByteSetBridge(final BaseCompositeByteSet<ChildType> bridged)
	{
		super(bridged, new ByteSortedSetFactory<ChildType>());
		_boundComparisonToolbox = new ByteBoundComparisonToolbox<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, BaseCompositeByteSet<ChildType>>(bridged);
		_equalityToolbox = new ByteEqualityToolbox<IByteSet, ISimpleByteSet, IElementaryByteSet, BaseCompositeByteSet<ChildType>>(bridged);
		_setCreationToolbox = new CompositeByteSetCreationToolbox<ChildType, BaseCompositeByteSet<ChildType>>(bridged);
	}

	public CompositeByteSetBridge(final BaseCompositeByteSet<ChildType> bridged, final Iterable<ChildType> childrenIterable)
	{
		super(bridged,
			new ByteSortedSetFactory<ChildType>(),
			childrenIterable);
		_boundComparisonToolbox = new ByteBoundComparisonToolbox<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, BaseCompositeByteSet<ChildType>>(bridged);
		_equalityToolbox = new ByteEqualityToolbox<IByteSet, ISimpleByteSet, IElementaryByteSet, BaseCompositeByteSet<ChildType>>(bridged);
		_setCreationToolbox = new CompositeByteSetCreationToolbox<ChildType, BaseCompositeByteSet<ChildType>>(bridged);
	}

	public  CompositeByteSetBridge(final BaseCompositeByteSet<ChildType> bridged, final ChildType... children)
	{
		this(bridged, Arrays.asList(children));
	}

	@Override
	protected ByteBoundComparisonToolbox<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, BaseCompositeByteSet<ChildType>> getBoundComparisonToolbox()
	{
		return _boundComparisonToolbox;
	}

	@Override
	protected ByteEqualityToolbox<IByteSet, ISimpleByteSet, IElementaryByteSet, BaseCompositeByteSet<ChildType>> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected CompositeByteSetCreationToolbox<ChildType, BaseCompositeByteSet<ChildType>> getSetCreationToolbox()
	{
		return _setCreationToolbox;
	}
}
