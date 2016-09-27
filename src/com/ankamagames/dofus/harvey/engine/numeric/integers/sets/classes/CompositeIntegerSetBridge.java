/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.CompositeIntegerSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.IntegerBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes.IntegerEqualityToolbox;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ICompositeIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CompositeIntegerSetBridge<ChildType extends IIntegerSet>
extends
AbstractCompositeSortedSetBridge
<
	IIntegerBound,
	IIntegerSet,
	ISimpleIntegerSet,
	IElementaryIntegerSet,
	ICompositeIntegerSet<?>,
	ChildType,
	BaseCompositeIntegerSet<ChildType>,
	TreeSet<ChildType>
>
{
	public static class IntegerSortedSetFactory<ChildType extends IIntegerSet>
	extends SortedSetFactory<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, ChildType, TreeSet<ChildType>>
	{
		@Override
		public TreeSet<ChildType> makeSet(final Comparator<? super ChildType> comparator)
		{
			return new TreeSet<ChildType>(comparator);
		}
	}

	protected IntegerBoundComparisonToolbox<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BaseCompositeIntegerSet<ChildType>> _boundComparisonToolbox;

	protected IntegerEqualityToolbox<IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BaseCompositeIntegerSet<ChildType>> _equalityToolbox;

	protected CompositeIntegerSetCreationToolbox<ChildType, BaseCompositeIntegerSet<ChildType>> _setCreationToolbox;



	public CompositeIntegerSetBridge(final BaseCompositeIntegerSet<ChildType> bridged)
	{
		super(bridged, new IntegerSortedSetFactory<ChildType>());
		_boundComparisonToolbox = new IntegerBoundComparisonToolbox<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BaseCompositeIntegerSet<ChildType>>(bridged);
		_equalityToolbox = new IntegerEqualityToolbox<IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BaseCompositeIntegerSet<ChildType>>(bridged);
		_setCreationToolbox = new CompositeIntegerSetCreationToolbox<ChildType, BaseCompositeIntegerSet<ChildType>>(bridged);
	}

	public CompositeIntegerSetBridge(final BaseCompositeIntegerSet<ChildType> bridged, final Iterable<ChildType> childrenIterable)
	{
		super(bridged,
			new IntegerSortedSetFactory<ChildType>(),
			childrenIterable);
		_boundComparisonToolbox = new IntegerBoundComparisonToolbox<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BaseCompositeIntegerSet<ChildType>>(bridged);
		_equalityToolbox = new IntegerEqualityToolbox<IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BaseCompositeIntegerSet<ChildType>>(bridged);
		_setCreationToolbox = new CompositeIntegerSetCreationToolbox<ChildType, BaseCompositeIntegerSet<ChildType>>(bridged);
	}

	public  CompositeIntegerSetBridge(final BaseCompositeIntegerSet<ChildType> bridged, final ChildType... children)
	{
		this(bridged, Arrays.asList(children));
	}

	@Override
	protected IntegerBoundComparisonToolbox<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BaseCompositeIntegerSet<ChildType>> getBoundComparisonToolbox()
	{
		return _boundComparisonToolbox;
	}

	@Override
	protected IntegerEqualityToolbox<IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, BaseCompositeIntegerSet<ChildType>> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected CompositeIntegerSetCreationToolbox<ChildType, BaseCompositeIntegerSet<ChildType>> getSetCreationToolbox()
	{
		return _setCreationToolbox;
	}
}
