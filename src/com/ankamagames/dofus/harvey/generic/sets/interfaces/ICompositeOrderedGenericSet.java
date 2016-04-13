/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeOrderedSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeOrderedGenericSet<Data, ChildType extends IOrderedGenericSet<Data>>
	extends IOrderedGenericSet<Data>, ICompositeOrderedSet<IOrderedGenericSet<Data>, ChildType>
{}