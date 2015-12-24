/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.interfaces;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.inetrfaces.IIEditableByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableByteRandomVariable
extends IByteRandomVariable, IEditableRandomVariable, IIEditableByteRandomVariable
{}