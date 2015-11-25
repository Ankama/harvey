/**
 *
 */
package com.ankamagames.dofus.harvey.engine.editors;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IHasEditor<Data, Editor extends IRandomVariableEditor<Data>>
{
	Editor getEditor();
}
