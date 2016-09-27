/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateGenericSet<Data>
extends
IDegenerateSet<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, IDegenerateGenericSet<Data>>,
IElementaryGenericSet<Data>
{
	@Nullable Data getValue();
	
	@Override
	Iterator<? extends IDegenerateGenericSet<Data>> iterator();
	
	@Override
	IDegenerateGenericSet<Data> getSimpleSet();
}