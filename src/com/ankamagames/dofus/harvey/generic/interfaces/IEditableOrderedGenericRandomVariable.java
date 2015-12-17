/**
 *
 */
package com.ankamagames.dofus.harvey.generic.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableOrderedGenericRandomVariable<Data>
extends IEditableGenericRandomVariable<Data>, IOrderedGenericRandomVariable<Data>
{}