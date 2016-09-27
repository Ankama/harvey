/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.BoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox.CompositeDoubleSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox.DoubleBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox.DoubleEqualityToolbox;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ICompositeDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IElementaryDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CompositeDoubleSetBridge<ChildType extends IDoubleSet, Bridged extends ICompositeDoubleSet<ChildType>>
extends AbstractCompositeContinuousSetBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, ICompositeDoubleSet<?>, ChildType, Bridged, SortedSet<ChildType>>
{

	protected DoubleBoundComparisonToolbox<Bridged> _boundComparisonToolbox;
	protected DoubleEqualityToolbox<Bridged> _equalityToolbox;
	protected CompositeDoubleSetCreationToolbox<ChildType, Bridged> _setCreationToolbox;
	protected DoubleSplitter _splitter;



	public static class DoubleSetFactory<ChildType extends IDoubleSet>
	extends SortedSetFactory<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, ChildType, SortedSet<ChildType>>
	{
		@Override
		public TreeSet<ChildType> makeSet(final Comparator<? super ChildType> comparator)
		{
			return new TreeSet<ChildType>(comparator);
		}
	}

	public CompositeDoubleSetBridge(final Bridged bridged)
	{
		super(bridged, new DoubleSetFactory<ChildType>());
		_splitter = DoubleSplitter.getInstance();
		_boundComparisonToolbox = new DoubleBoundComparisonToolbox<Bridged>(bridged);
		_equalityToolbox = new DoubleEqualityToolbox<Bridged>(bridged);
		_setCreationToolbox = new CompositeDoubleSetCreationToolbox<ChildType, Bridged>(bridged);
	}

	public CompositeDoubleSetBridge(final Bridged bridged, final ChildType element)
	{
		this(bridged);
		_children.add(element);
		_descChildren.add(element);
	}

	public CompositeDoubleSetBridge(final Bridged bridged, final Iterable<? extends ChildType> collection)
	{
		this(bridged);
		for(final ChildType child:collection)
		{
			_children.add(child);
			_descChildren.add(child);
		}
	}

	@Override
	protected BoundComparisonToolbox<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, Bridged> getBoundComparisonToolbox()
	{
		return _boundComparisonToolbox;
	}

	@Override
	protected CompositeDoubleSetCreationToolbox<ChildType, Bridged> getSetCreationToolbox()
	{
		return _setCreationToolbox;
	}

	@Override
	protected IEqualityToolbox<IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, Bridged> getEqualityToolbox()
	{
		return _equalityToolbox;
	}


}