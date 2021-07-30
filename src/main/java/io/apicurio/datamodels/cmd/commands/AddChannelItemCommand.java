/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.MarshallCompat;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to add a new channelItem in a document. Source for the new
 * channelItem must be provided.  This source will be converted to an AAI
 * channelItem object and then added to the data model.
 * @author laurent.broudoux@gmail.com
 */
public class AddChannelItemCommand extends AbstractCommand {

   public boolean _channelItemExists;
   public boolean _emptyChannelItems;

   public String _newChannelItemName;

   @JsonDeserialize(using= MarshallCompat.NullableJsonNodeDeserializer.class)
   public Object _newChannelItemObj;


   AddChannelItemCommand() {
   }

   AddChannelItemCommand(String channelItemName, Object from) {
      this._newChannelItemName = channelItemName;
      this._newChannelItemObj = from;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[AddChannelItemCommand] Executing.");
      AaiDocument doc = (AaiDocument) document;
      if (doc.channels != null && doc.channels.isEmpty()) {
         this._emptyChannelItems = true;
      }

      if (!this.isNullOrUndefined(doc.channels.get(this._newChannelItemObj))) {
         LoggerCompat.info("[AddPathItemCommand] AddChannelItemCommand with name %s already exists.", this._newChannelItemName);
         this._channelItemExists = true;
      } else {
         AaiChannelItem channelItem = doc.createChannelItem(this._newChannelItemName);
         doc.addChannelItem(channelItem);
         Library.readNode(this._newChannelItemObj, channelItem);
         this._channelItemExists = false;
      }
   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[AddChannelItemCommand] Reverting.");
      if (this._channelItemExists) {
         return;
      }
      AaiDocument doc = (AaiDocument) document;
      if (this._emptyChannelItems) {
         doc.channels.clear();
      } else {
         doc.channels.remove(this._newChannelItemName);
      }
   }
}
