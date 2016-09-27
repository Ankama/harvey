/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IGenericSet<Data>
extends IIGenericSet<Data, IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>>
{}