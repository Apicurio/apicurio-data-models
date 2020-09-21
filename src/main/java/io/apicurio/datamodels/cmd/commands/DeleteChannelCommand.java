/*
 * Copyright 2020 Red Hat
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
 * A command used to delete a channel in AsyncAPI document.
 * @author laurent.broudoux@gmail.com
 */
public class DeleteChannelCommand extends AbstractCommand {

   public String _channel;

   @JsonDeserialize(using= MarshallCompat.NullableJsonNodeDeserializer.class)
   public Object _oldChannel;

   DeleteChannelCommand() {
   }

   DeleteChannelCommand(String channel) {
      this._channel = channel;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[DeleteChannelCommand] Executing for channel: %s", this._channel);
      AaiDocument doc = (AaiDocument) document;
      this._oldChannel = null;
      if (doc.channels == null || doc.channels.isEmpty()) {
         return;
      }

      this._oldChannel = Library.writeNode(doc.channels.remove(this._channel));
   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[DeleteChannelCommand] Reverting.");
      AaiDocument doc = (AaiDocument) document;
      if (this.isNullOrUndefined(this._oldChannel)) {
         return;
      }

      AaiChannelItem channelItem = doc.createChannelItem(this._channel);
      Library.readNode(this._oldChannel, channelItem);
      doc.addChannelItem(channelItem);
   }
}
