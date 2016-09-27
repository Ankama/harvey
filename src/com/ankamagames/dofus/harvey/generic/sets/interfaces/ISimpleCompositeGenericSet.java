/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISimpleCompositeGenericSet<Data, ChildType extends IElementaryGenericSet<Data>>
extends
ISimpleCompositeSet
<
	IGenericSet<Data>,
	ISimpleGenericSet<Data>,
	IElementaryGenericSet<Data>,
	ICompositeGenericSet<Data, ?>,
	ChildType
>,
ICompositeGenericSet<Data, ChildType>,
ISimpleGenericSet<Data>
{
}