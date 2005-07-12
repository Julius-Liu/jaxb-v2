/*
 * @(#)$Id: InfosetScanner.java,v 1.4 2005-07-12 22:39:39 kohsuke Exp $
 *
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */
package com.sun.xml.bind.unmarshaller;

import javax.xml.bind.Binder;

import com.sun.xml.bind.v2.runtime.unmarshaller.LocatorEx;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * Visits a DOM-ish API and generates SAX events.
 * 
 * <p>
 * This interface is not tied to any particular DOM API.
 * Used by the {@link Binder}.
 * 
 * <p>
 * Since we are parsing a DOM-ish tree, I don't think this
 * scanner itself will ever find an error, so this class
 * doesn't have its own error reporting scheme.
 * 
 * <p>
 * This interface <b>MAY NOT</b> be implemented by the generated
 * runtime nor the generated code. We may add new methods on
 * this interface later. This is to be implemented by the static runtime
 * only.
 * 
 * @author
 *     Kohsuke Kawaguchi (kohsuke.kawaguchi@sun.com)
 * @since 2.0
 */
public interface InfosetScanner<XmlNode> {
    /**
     * Parses the given DOM-ish element/document and generates
     * SAX events.
     * 
     * @throws ClassCastException
     *      If the type of the node is not known to this implementation.
     * 
     * @throws SAXException
     *      If the {@link ContentHandler} throws a {@link SAXException}.
     *      Do not throw an exception just because the scanner failed
     *      (if that can happen we need to change the API.)
     */
    void scan( XmlNode node ) throws SAXException;
    
    /**
     * Sets the {@link ContentHandler}.
     * 
     * This handler receives the SAX events.
     */
    void setContentHandler( ContentHandler handler );
    ContentHandler getContentHandler();
    
    /**
     * Gets the current element we are parsing.
     * 
     * <p>
     * This method could
     * be called from the {@link ContentHandler#startElement(String, String, String, Attributes)}
     * or {@link ContentHandler#endElement(String, String, String)}.
     * 
     * <p>
     * Otherwise the behavior of this method is undefined.
     * 
     * @return
     *      never return null.
     */
    XmlNode getCurrentElement();

    LocatorEx getLocator();
}
