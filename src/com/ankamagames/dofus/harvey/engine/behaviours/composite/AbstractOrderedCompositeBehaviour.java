/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours.composite;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import com.ankamagames.dofus.harvey.engine.behaviours.IOrderedRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractOrderedCompositeBehaviour
<
	Data,
	ParentType extends ICompositeRandomVariable<Data, ?, ?>,
	ChildType extends IOrderedRandomVariable<Data, ?>,
	Bridged extends IOrderedRandomVariable<Data, ?>
>
extends AbstractCompositeBehaviour<Data, ChildType, Bridged>
implements IOrderedRandomVariableBehaviour<Data, ParentType>
{
	protected TreeSet<ChildType> _subVariables;

	public AbstractOrderedCompositeBehaviour(final Collection<? extends ChildType> elmts, @Nullable final Comparator<? super ChildType> comparator,
			final Bridged bridged)
	{
		super(bridged);
		_subVariables = new TreeSet<ChildType>(comparator);
		_subVariables.addAll(elmts);
	}

	public AbstractOrderedCompositeBehaviour(final Collection<? extends ChildType> elmts,
			final Bridged bridged)
	{
		super(bridged);
		_subVariables = new TreeSet<ChildType>(elmts);
	}

	public AbstractOrderedCompositeBehaviour(@Nullable final Comparator<? super ChildType> comparator,
			final Bridged bridged)
	{
		super(bridged);
		_subVariables = new TreeSet<ChildType>(comparator);
	}

	public AbstractOrderedCompositeBehaviour(final Bridged bridged)
	{
		super(bridged);
		_subVariables = new TreeSet<ChildType>();
	}

	public AbstractOrderedCompositeBehaviour(final Collection<? extends ChildType> elmts, @Nullable final Comparator<? super ChildType> comparator)
	{
		super();
		_subVariables = new TreeSet<ChildType>(comparator);
		_subVariables.addAll(elmts);
	}

	public AbstractOrderedCompositeBehaviour(final Collection<? extends ChildType> elmts)
	{
		super();
		_subVariables = new TreeSet<ChildType>(elmts);
	}

	public AbstractOrderedCompositeBehaviour(@Nullable final Comparator<? super ChildType> comparator)
	{
		super();
		_subVariables = new TreeSet<ChildType>(comparator);
	}

	public AbstractOrderedCompositeBehaviour()
	{
		super();
		_subVariables = new TreeSet<ChildType>();
	}

	@Override
	public int compareTo(@Nullable final Data o)
	{
		if(_subVariables.first().compareTo(o)>0)
			return 1;
		if(_subVariables.last().compareTo(o)<0)
			return -1;
		return 0;
	}

	@Override
	public TreeSet<ChildType> getSubVariables()
	{
		return _subVariables;
	}

	@Override
	public int getProbabilityForLowerThan(final Data value,
			final @Nullable IRandomVariable<Data, ?> context)
	{
		final Iterator<ChildType> iterator = _subVariables.iterator();
		boolean isLower = true;
		int r = 0;
		while(iterator.hasNext()&&isLower)
		{
			final ChildType elmt = iterator.next();
			if(elmt.compareTo(value)<0)
			{
				r += elmt.getProbabilityForLowerThan(value, context);
			}
			else
				isLower = false;
		}
		return r;
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(final Data value,
			final @Nullable IRandomVariable<Data, ?> context)
	{
		final Iterator<ChildType> iterator = _subVariables.iterator();
		boolean isLowerOrEqual = true;
		int r = 0;
		while(iterator.hasNext()&&isLowerOrEqual)
		{
			final ChildType elmt = iterator.next();
			if(elmt.compareTo(value)<=0)
			{
				r += elmt.getProbabilityForLowerThanOrEqualTo(value, context);
			}
			else
				isLowerOrEqual = false;
		}
		return r;
	}

	@Override
	public int getProbabilityForGreaterThan(final Data value,
			final @Nullable IRandomVariable<Data, ?> context)
	{
		final Iterator<ChildType> iterator = _subVariables.descendingIterator();
		boolean isGreater = true;
		int r = 0;
		while(iterator.hasNext()&&isGreater)
		{
			final ChildType elmt = iterator.next();
			if(elmt.compareTo(value)>0)
			{
				r += elmt.getProbabilityForGreaterThan(value, context);
			}
			else
				isGreater = false;
		}
		return r;
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(final Data value,
			final @Nullable IRandomVariable<Data, ?> context)
	{
		final Iterator<ChildType> iterator = _subVariables.descendingIterator();
		boolean isGreaterOrEqual = true;
		int r = 0;
		while(iterator.hasNext()&&isGreaterOrEqual)
		{
			final ChildType elmt = iterator.next();
			if(elmt.compareTo(value)>=0)
			{
				r += elmt.getProbabilityForGreaterThanOrEqualTo(value, context);
			}
			else
				isGreaterOrEqual = false;
		}
		return r;
	}
}