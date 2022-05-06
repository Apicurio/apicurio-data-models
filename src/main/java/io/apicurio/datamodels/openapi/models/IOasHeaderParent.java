package io.apicurio.datamodels.openapi.models;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author vvilerio
 */
public interface IOasHeaderParent {

   /**
    * Creates a header.
    */
   public OasHeader createHeader(String name);

   /**
    * Adds a header.
    * @param name
    * @param header
    */
   public void addHeader(String name, OasHeader header);
   
   /**
    * Renames a header
    * @param from
    * @param to
    * @param object
    */
   public void renameHeader(String from, String to, Consumer<OasHeader> headerConsumer);
   
   /**
    * Restore a deleted header in its original position
    * @param index
    * @param headerName
    * @param header
    */
   public void restoreHeader(Integer index, String headerName, OasHeader header);

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
