/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.BoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox.CompositeFloatSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox.FloatBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox.FloatEqualityToolbox;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ICompositeFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IElementaryFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ISimpleFloatSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CompositeFloatSetBridge<ChildType extends IFloatSet, Bridged extends ICompositeFloatSet<ChildType>>
extends AbstractCompositeContinuousSetBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, ICompositeFloatSet<?>, ChildType, Bridged, SortedSet<ChildType>>
{

	protected FloatBoundComparisonToolbox<Bridged> _boundComparisonToolbox;
	protected FloatEqualityToolbox<Bridged> _equalityToolbox;
	protected CompositeFloatSetCreationToolbox<ChildType, Bridged> _setCreationToolbox;
	protected FloatSplitter _splitter;



	public static class FloatSetFactory<ChildType extends IFloatSet>
	extends SortedSetFactory<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, ChildType, SortedSet<ChildType>>
	{
		@Override
		public TreeSet<ChildType> makeSet(final Comparator<? super ChildType> comparator)
		{
			return new TreeSet<ChildType>(comparator);
		}
	}

	public CompositeFloatSetBridge(final Bridged bridged)
	{
		super(bridged, new FloatSetFactory<ChildType>());
		_splitter = FloatSplitter.getInstance();
		_boundComparisonToolbox = new FloatBoundComparisonToolbox<Bridged>(bridged);
		_equalityToolbox = new FloatEqualityToolbox<Bridged>(bridged);
		_setCreationToolbox = new CompositeFloatSetCreationToolbox<ChildType, Bridged>(bridged);
	}

	public CompositeFloatSetBridge(final Bridged bridged, final ChildType element)
	{
		this(bridged);
		_children.add(element);
		_descChildren.add(element);
	}

	public CompositeFloatSetBridge(final Bridged bridged, final Iterable<? extends ChildType> collection)
	{
		this(bridged);
		for(final ChildType child:collection)
		{
			_children.add(child);
			_descChildren.add(child);
		}
	}

	@Override
	protected BoundComparisonToolbox<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, Bridged> getBoundComparisonToolbox()
	{
		return _boundComparisonToolbox;
	}

	@Override
	protected CompositeFloatSetCreationToolbox<ChildType, Bridged> getSetCreationToolbox()
	{
		return _setCreationToolbox;
	}

	@Override
	protected IEqualityToolbox<IFloatSet, ISimpleFloatSet, IElementaryFloatSet, Bridged> getEqualityToolbox()
	{
		return _equalityToolbox;
	}


}