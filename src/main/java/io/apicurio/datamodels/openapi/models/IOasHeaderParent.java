package io.apicurio.datamodels.openapi.models;

import io.apicurio.datamodels.openapi.models.OasHeader;

import java.util.List;

/**
 * @author vvilerio
 */
public interface IOasHeaderParent {

   /**
    * Creates a  header.
    */
   public OasHeader createHeader(String name);

   /**
    * Adds a  header.
    * @param name
    * @param header
    */
   public void addHeader(String name, OasHeader header);

   /**
    * Gets a single  header by name.
    * @param name
    */
   public OasHeader getHeader(String name);

   /**
    * Removes a single  header and returns it.  This may return null or undefined if none found.
    * @param name
    */
   public OasHeader removeHeader(String name);

   /**
    * Gets a list of all  headers.
    */
   public List<OasHeader> getHeaders();
}
