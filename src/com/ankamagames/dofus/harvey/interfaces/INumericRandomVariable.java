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
public interface INumericRandomVariable<Data extends Number, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>>
	extends IOrderedRandomVariable<Data, ParentType>
{
}
