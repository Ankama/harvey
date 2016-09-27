/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox.CompositeDoubleSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox.DoubleSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ICompositeDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IElementaryDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CommonCompositeDoubleSetBridge
<
	ChildType extends IDoubleSet,
	Bridged extends ICompositeDoubleSet<ChildType>
>
extends AbstractSetBridge<IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, Bridged>
{
	protected DoubleSetCreationToolbox<? extends ICompositeDoubleSet<ChildType>> _setCreationToolbox;

	public CommonCompositeDoubleSetBridge(final Bridged bridged,
		final CompositeDoubleSetCreationToolbox<ChildType, ? extends ICompositeDoubleSet<ChildType>> setCreationToolbox)
	{
		super(bridged);
		_setCreationToolbox = setCreationToolbox;
	}

	public @Nullable IDoubleBound getLowerBound()
	{
		if(_bridged.getChildrenCount()<=0)
			return null;
		final ChildType next = _bridged.getChildren().iterator().next();
		return next.getLowerBound();
	}

	public @Nullable IDoubleBound getUpperBound()
	{
		if(_bridged.getChildrenCount()<=0)
			return null;
		final ChildType next = _bridged.getChildrenDescending().iterator().next();
		return next.getUpperBound();
	}

	protected boolean checkBoundary(final double value, final ChildType child)
	{
		final IDoubleBound lowerBound = child.getLowerBound();
		return (lowerBound!=null)&&((lowerBound.getValue()-value)>0);
	}

	public boolean contains(final double value)
	{
		for(final ChildType child:_bridged.getChildren())
			if(child.contains(value))
				return true;
			else
			{
				if(checkBoundary(value, child))
					return false;
			}
		return false;
	}


	public List<? extends IDoubleSet> split(final double[] values, final boolean[] isIntervalStart) {
		final List<IDoubleSet> r = new ArrayList<IDoubleSet>( values.length + 1 );
		final ArrayList<List<IDoubleSet>> partsList = new ArrayList<List<IDoubleSet>>(values.length+1);
		for(int i = 0 ; i <= values.length ; i++)
			partsList.add(new LinkedList<IDoubleSet>());
		for (final ChildType child : _bridged.getChildren()) {
			final List<? extends IDoubleSet> currentSplit = child.split(values, isIntervalStart);
			final Iterator<List<IDoubleSet>> it = partsList.iterator();
			for(final IDoubleSet elmt:currentSplit) {
				final List<IDoubleSet> next = it.next();
				if(!elmt.isEmpty())
					next.add(elmt);
			}
		}

		for(final List<IDoubleSet> splitList:partsList)
		{
			if (splitList.isEmpty())
				r.add(_setCreationToolbox.makeEmptySet().getAsSet());
			else if (splitList.size() == 1)
				r.add(splitList.get(0));
			else
				r.add(_setCreationToolbox.makeComposite(splitList));
		}

		return r;
	}

}
