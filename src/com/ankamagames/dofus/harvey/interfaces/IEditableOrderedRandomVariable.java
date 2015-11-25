/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableOrderedRandomVariable<Data>
extends IEditableRandomVariable<Data>, IOrderedRandomVariable<Data>
{}