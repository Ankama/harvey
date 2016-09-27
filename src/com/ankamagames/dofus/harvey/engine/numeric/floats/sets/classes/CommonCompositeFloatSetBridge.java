/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox.CompositeFloatSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox.FloatSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ICompositeFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IElementaryFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ISimpleFloatSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CommonCompositeFloatSetBridge
<
	ChildType extends IFloatSet,
	Bridged extends ICompositeFloatSet<ChildType>
>
extends AbstractSetBridge<IFloatSet, ISimpleFloatSet, IElementaryFloatSet, Bridged>
{
	protected FloatSetCreationToolbox<? extends ICompositeFloatSet<ChildType>> _setCreationToolbox;

	public CommonCompositeFloatSetBridge(final Bridged bridged,
		final CompositeFloatSetCreationToolbox<ChildType, ? extends ICompositeFloatSet<ChildType>> setCreationToolbox)
	{
		super(bridged);
		_setCreationToolbox = setCreationToolbox;
	}

	public @Nullable IFloatBound getLowerBound()
	{
		if(_bridged.getChildrenCount()<=0)
			return null;
		final ChildType next = _bridged.getChildren().iterator().next();
		return next.getLowerBound();
	}

	public @Nullable IFloatBound getUpperBound()
	{
		if(_bridged.getChildrenCount()<=0)
			return null;
		final ChildType next = _bridged.getChildrenDescending().iterator().next();
		return next.getUpperBound();
	}

	protected boolean checkBoundary(final float value, final ChildType child)
	{
		final IFloatBound lowerBound = child.getLowerBound();
		return (lowerBound!=null)&&((lowerBound.getValue()-value)>0);
	}

	public boolean contains(final float value)
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


	public List<? extends IFloatSet> split(final float[] values, final boolean[] isIntervalStart) {
		final List<IFloatSet> r = new ArrayList<IFloatSet>( values.length + 1 );
		final ArrayList<List<IFloatSet>> partsList = new ArrayList<List<IFloatSet>>(values.length+1);
		for(int i = 0 ; i <= values.length ; i++)
			partsList.add(new LinkedList<IFloatSet>());
		for (final ChildType child : _bridged.getChildren()) {
			final List<? extends IFloatSet> currentSplit = child.split(values, isIntervalStart);
			final Iterator<List<IFloatSet>> it = partsList.iterator();
			for(final IFloatSet elmt:currentSplit) {
				final List<IFloatSet> next = it.next();
				if(!elmt.isEmpty())
					next.add(elmt);
			}
		}

		for(final List<IFloatSet> splitList:partsList)
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
