/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.SingleValueIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractDegenerateSet
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	DegenerateSet extends IDegenerateSet<Set, SimpleSet, ElementarySet, DegenerateSet>
>
extends AbstractElementarySet<Set, SimpleSet, ElementarySet>
implements IDegenerateSet<Set, SimpleSet, ElementarySet, DegenerateSet>
{
	protected abstract AbstractDegenerateSetBridge<Set, SimpleSet, ElementarySet, DegenerateSet, ? extends AbstractDegenerateSet<Set, SimpleSet, ElementarySet, DegenerateSet>> getBridge();

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		return getBridge().isEqual(obj);
	}

	@Override
	public boolean equals(final Set set)
	{
		return getBridge().isEqual(set);
	}

	@Override
	public boolean isEmpty()
	{
		return false;
	}

	@Override
	public boolean isDegenerate()
	{
		return true;
	}

	@Override
	public boolean isElementary()
	{
		return true;
	}

	@Override
	public boolean isSimple()
	{
		return true;
	}

	@Override
	public double size()
	{
		return 1;
	}

	@Override
	public boolean contains(final Set set)
	{
		return getBridge().contains(set);
	}

	@Override
	public boolean isContainedBy(final Set set)
	{
		return getBridge().isContainedBy(set);
	}

	@Override
	public boolean isIntersecting(final Set set)
	{
		return isContainedBy(set);
	}

	@Override
	public Set unite(final Set set)
	{
		return getBridge().unite(set);
	}

	@Override
	public Set intersect(final Set set)
	{
		return getBridge().intersect(set);
	}

	@Override
	public Set subtract(final Set set)
	{
		return getBridge().subtract(set);
	}

	@Override
	public Set symmetricSubtract(final Set set)
	{
		return getBridge().symmetricSubtract(set);
	}

	@Override
	public Iterator<DegenerateSet> iterator()
	{
		return new SingleValueIterator<DegenerateSet>(getAsDegenerateSet());
	}

	/**
	 * please, don't forget to override...
	 */
	@Override
	public abstract String toString();
}