package io.apicurio.datamodels.openapi.v3.models;

import java.util.List;

/**
 * @author vvilerio
 */
public interface IOas30HttpHeaderParent {

   /**
    * Creates a http header.
    * @param name
    */
   public Oas30Header createHttpHeader(String name);

   /**
    * Adds a http header.
    * @param name
    * @param httpHeader
    */
   public void addHttpHeader(String name, Oas30Header httpHeader);

   /**
    * Gets a single http header by name.
    * @param name
    */
   public Oas30Header getHttpHeader(String name);

   /**
    * Removes a single http header and returns it.  This may return null or undefined if none found.
    * @param name
    */
   public Oas30Header removeHttpHeader(String name);

   /**
    * Gets a list of all http headers.
    */
   public List<Oas30Header> getHttpHeaders();
}
