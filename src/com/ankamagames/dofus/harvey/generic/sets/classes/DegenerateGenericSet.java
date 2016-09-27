/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSet;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.CommonDegenerateGenericSetBridge;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.DegenerateGenericSetBridge;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class DegenerateGenericSet<Data>
extends AbstractDegenerateSet<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, IDegenerateGenericSet<Data>>
implements IDegenerateGenericSet<Data>
{
	DegenerateGenericSetBridge<Data, DegenerateGenericSet<Data>> _bridge;
	CommonDegenerateGenericSetBridge<Data> _degenerateBridge;

	public static <Data> DegenerateGenericSet<Data> makeSet(@Nullable final Data value)
	{
		return new DegenerateGenericSet<Data>(value);
	}

	private DegenerateGenericSet(@Nullable final Data value)
	{
		_bridge = new DegenerateGenericSetBridge<Data, DegenerateGenericSet<Data>>(this);
		_degenerateBridge = new CommonDegenerateGenericSetBridge<Data>(value);
	}

	@Override
	protected DegenerateGenericSetBridge<Data, DegenerateGenericSet<Data>> getBridge() {
		return _bridge;
	}

	@Override
	public IGenericSet<Data> getAsSet() {
		return this;
	}

	@Override
	public ISimpleGenericSet<Data> getAsSimpleSet() {
		return this;
	}

	@Override
	public IElementaryGenericSet<Data> getAsElementarySet() {
		return this;
	}

	@Override
	public IDegenerateGenericSet<Data> getAsDegenerateSet() {
		return this;
	}

	@Override
	public int hashCode()
	{
		final Data value = getValue();
		if(value!=null)
			return value.hashCode();
		return 0;
	}

	@Override
	public @Nullable Data getValue()
	{
		return _degenerateBridge.getValue();
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		return _degenerateBridge.contains(value);
	}

	@Override
	public DegenerateGenericSet<Data> getSimpleSet()
	{
		return this;
	}

	@Override
	public Iterator<Data> getDataIterator() {
		return _degenerateBridge.getDataIterator();
	}

	@Override
	public String toString()
	{
		return _degenerateBridge.toString();
	}

	@Override
	public List<Data> sample(final int numberOfSample)
	{
		return sample();
	}

	@Override
	public List<Data> sample()
	{
		return Arrays.asList(getValue());
	}
}