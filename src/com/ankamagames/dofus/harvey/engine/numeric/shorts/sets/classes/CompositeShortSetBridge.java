/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ShortBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.ShortEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes.CompositeShortSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ICompositeShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CompositeShortSetBridge<ChildType extends IShortSet>
extends
AbstractCompositeSortedSetBridge
<
	IShortBound,
	IShortSet,
	ISimpleShortSet,
	IElementaryShortSet,
	ICompositeShortSet<?>,
	ChildType,
	BaseCompositeShortSet<ChildType>,
	TreeSet<ChildType>
>
{
	public static class ShortSortedSetFactory<ChildType extends IShortSet>
	extends SortedSetFactory<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, ChildType, TreeSet<ChildType>>
	{
		@Override
		public TreeSet<ChildType> makeSet(final Comparator<? super ChildType> comparator)
		{
			return new TreeSet<ChildType>(comparator);
		}
	}

	protected ShortBoundComparisonToolbox<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, BaseCompositeShortSet<ChildType>> _boundComparisonToolbox;

	protected ShortEqualityToolbox<IShortSet, ISimpleShortSet, IElementaryShortSet, BaseCompositeShortSet<ChildType>> _equalityToolbox;

	protected CompositeShortSetCreationToolbox<ChildType, BaseCompositeShortSet<ChildType>> _setCreationToolbox;



	public CompositeShortSetBridge(final BaseCompositeShortSet<ChildType> bridged)
	{
		super(bridged, new ShortSortedSetFactory<ChildType>());
		_boundComparisonToolbox = new ShortBoundComparisonToolbox<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, BaseCompositeShortSet<ChildType>>(bridged);
		_equalityToolbox = new ShortEqualityToolbox<IShortSet, ISimpleShortSet, IElementaryShortSet, BaseCompositeShortSet<ChildType>>(bridged);
		_setCreationToolbox = new CompositeShortSetCreationToolbox<ChildType, BaseCompositeShortSet<ChildType>>(bridged);
	}

	public CompositeShortSetBridge(final BaseCompositeShortSet<ChildType> bridged, final Iterable<ChildType> childrenIterable)
	{
		super(bridged,
			new ShortSortedSetFactory<ChildType>(),
			childrenIterable);
		_boundComparisonToolbox = new ShortBoundComparisonToolbox<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, BaseCompositeShortSet<ChildType>>(bridged);
		_equalityToolbox = new ShortEqualityToolbox<IShortSet, ISimpleShortSet, IElementaryShortSet, BaseCompositeShortSet<ChildType>>(bridged);
		_setCreationToolbox = new CompositeShortSetCreationToolbox<ChildType, BaseCompositeShortSet<ChildType>>(bridged);
	}

	public  CompositeShortSetBridge(final BaseCompositeShortSet<ChildType> bridged, final ChildType... children)
	{
		this(bridged, Arrays.asList(children));
	}

	@Override
	protected ShortBoundComparisonToolbox<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, BaseCompositeShortSet<ChildType>> getBoundComparisonToolbox()
	{
		return _boundComparisonToolbox;
	}

	@Override
	protected ShortEqualityToolbox<IShortSet, ISimpleShortSet, IElementaryShortSet, BaseCompositeShortSet<ChildType>> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected CompositeShortSetCreationToolbox<ChildType, BaseCompositeShortSet<ChildType>> getSetCreationToolbox()
	{
		return _setCreationToolbox;
	}
}
