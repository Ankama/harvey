/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.SurroundingValuesProvider;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.CompositeSortedGenericSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.GenericBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.GenericEqualityToolbox;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CompositeSortedGenericSetBridge<Data, ChildType extends ISortedGenericSet<Data>>
extends
AbstractCompositeSortedSetBridge
<
	IGenericBound<Data>,
	ISortedGenericSet<Data>,
	ISimpleSortedGenericSet<Data>,
	IElementarySortedGenericSet<Data>,
	ICompositeSortedGenericSet<Data, ?>,
	ChildType,
	BaseCompositeSortedGenericSet<Data, ChildType>,
	TreeSet<ChildType>
>
{
	public static class GenericSortedSetFactory<Data, ChildType extends ISortedGenericSet<Data>>
	extends SortedSetFactory<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, ChildType, TreeSet<ChildType>>
	{
		@Override
		public TreeSet<ChildType> makeSet(final Comparator<? super ChildType> comparator)
		{
			return new TreeSet<ChildType>(comparator);
		}
	}

	protected GenericBoundComparisonToolbox<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BaseCompositeSortedGenericSet<Data, ChildType>> _boundComparisonToolbox;

	protected GenericEqualityToolbox<Data, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BaseCompositeSortedGenericSet<Data, ChildType>> _equalityToolbox;

	protected CompositeSortedGenericSetCreationToolbox<Data, ChildType, BaseCompositeSortedGenericSet<Data,ChildType>> _setCreationToolbox;



	public CompositeSortedGenericSetBridge(final BaseCompositeSortedGenericSet<Data, ChildType> bridged, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		super(bridged, new GenericSortedSetFactory<Data, ChildType>());
		_boundComparisonToolbox = new GenericBoundComparisonToolbox<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BaseCompositeSortedGenericSet<Data, ChildType>>(bridged, comparator);
		_equalityToolbox = new GenericEqualityToolbox<Data, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BaseCompositeSortedGenericSet<Data, ChildType>>(bridged);
		_setCreationToolbox = new CompositeSortedGenericSetCreationToolbox<Data, ChildType, BaseCompositeSortedGenericSet<Data,ChildType>>(bridged, comparator, surroundingProvider);
	}

	public CompositeSortedGenericSetBridge(final BaseCompositeSortedGenericSet<Data, ChildType> bridged, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider, final Iterable<ChildType> childrenIterable)
	{
		super(bridged,
			new GenericSortedSetFactory<Data, ChildType>(),
			childrenIterable);
		_boundComparisonToolbox = new GenericBoundComparisonToolbox<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BaseCompositeSortedGenericSet<Data, ChildType>>(bridged, comparator);
		_equalityToolbox = new GenericEqualityToolbox<Data, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BaseCompositeSortedGenericSet<Data, ChildType>>(bridged);
		_setCreationToolbox = new CompositeSortedGenericSetCreationToolbox<Data, ChildType, BaseCompositeSortedGenericSet<Data,ChildType>>(bridged, comparator, surroundingProvider);
	}

	public  CompositeSortedGenericSetBridge(final BaseCompositeSortedGenericSet<Data, ChildType> bridged, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider, final ChildType... children)
	{
		this(bridged, comparator, surroundingProvider, Arrays.asList(children));
	}

	@Override
	protected GenericBoundComparisonToolbox<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BaseCompositeSortedGenericSet<Data, ChildType>> getBoundComparisonToolbox()
	{
		return _boundComparisonToolbox;
	}

	@Override
	protected GenericEqualityToolbox<Data, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BaseCompositeSortedGenericSet<Data, ChildType>> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected CompositeSortedGenericSetCreationToolbox<Data, ChildType, BaseCompositeSortedGenericSet<Data, ChildType>> getSetCreationToolbox()
	{
		return _setCreationToolbox;
	}
//
//	public List<? extends ISortedGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart) {
//		final List<ISortedGenericSet<Data>> r = new ArrayList<ISortedGenericSet<Data>>( values.length + 1 );
//		final ArrayList<List<ISortedGenericSet<Data>>> partsList = new ArrayList<List<ISortedGenericSet<Data>>>(values.length+1);
//		for(int i = 0 ; i <= values.length ; i++)
//			partsList.add(new LinkedList<ISortedGenericSet<Data>>());
//		for (final ChildType child : getChildren()) {
//			final List<? extends ISortedGenericSet<Data>> currentSplit = child.split(values, isIntervalStart);
//			final Iterator<List<ISortedGenericSet<Data>>> it = partsList.iterator();
//			for(final ISortedGenericSet<Data> elmt:currentSplit) {
//				final List<ISortedGenericSet<Data>> next = it.next();
//				if(!next.isEmpty())
//					next.add(elmt);
//			}
//		}
//
//		for(final List<ISortedGenericSet<Data>> splitList:partsList)
//		{
//			if (splitList.isEmpty())
//				r.add(getSetCreationToolbox().makeEmptySet().getAsSet());
//			else if (splitList.size() == 1)
//				r.add(splitList.get(0));
//			else
//				r.add(getSetCreationToolbox().makeComposite(splitList));
//		}
//
//		return r;
//	}
}
