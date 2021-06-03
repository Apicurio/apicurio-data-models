package io.apicurio.datamodels.openapi.v3.models;

import io.apicurio.datamodels.openapi.models.OasHeader;

import java.util.List;

/**
 * @author vvilerio
 */
public interface IOasHeaderParent {

   /**
    * Creates a http header.
    */
   public OasHeader createHttpHeader(String name);

   /**
    * Adds a http header.
    * @param name
    * @param header
    */
   public void addHttpHeader(String name, OasHeader header);

   /**
    * Gets a single http header by name.
    * @param name
    */
   public OasHeader getHttpHeader(String name);

   /**
    * Removes a single http header and returns it.  This may return null or undefined if none found.
    * @param name
    */
   public OasHeader removeHttpHeader(String name);

   /**
    * Gets a list of all http headers.
    */
   public List<OasHeader> getHttpHeaders();
}
