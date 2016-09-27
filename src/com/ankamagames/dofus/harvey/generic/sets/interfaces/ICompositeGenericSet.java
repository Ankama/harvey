/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeGenericSet<Data, ChildType extends IGenericSet<Data>>
extends ICompositeSet<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, ICompositeGenericSet<Data, ?>, ChildType>, IGenericSet<Data>
{}