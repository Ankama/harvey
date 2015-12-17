/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.interfaces;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IEditableBasicCollection;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.inetrfaces.IIEditableByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableByteRandomVariable
extends IByteRandomVariable, IEditableBasicCollection, IIEditableByteRandomVariable
{}