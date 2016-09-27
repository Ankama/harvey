/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleGenericSet<Data>
extends ISimpleSet<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>>, IGenericSet<Data>
{}