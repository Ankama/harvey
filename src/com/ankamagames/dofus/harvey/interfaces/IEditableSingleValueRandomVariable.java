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
public interface IEditableSingleValueRandomVariable<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>>
	extends ISingleValueRandomVariable<Data, ParentType>, IEditableRandomVariable<Data, ParentType>
{
}
