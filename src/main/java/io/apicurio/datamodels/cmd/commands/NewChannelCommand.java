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

import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;

/**
 * A command used to create a new channel item in aa AsyncAPI document.
 * @author laurent.broudoux@gmail.com
 */
public class NewChannelCommand extends AbstractCommand {

   public String _newChannel;

   public boolean _channelExisted;
   public boolean _emptyChannels;

   public NewChannelCommand() {
   }

   public NewChannelCommand(String newChannel) {
      this._newChannel = newChannel;
   }

   @Override
   public void execute(Document document) {
      LoggerCompat.info("[NewChannelCommand] Executing.");
      AaiDocument adoc = (AaiDocument) document;

      if (adoc.channels != null && adoc.channels.isEmpty()) {
         this._emptyChannels = true;
      }
      if (this.isNullOrUndefined(adoc.channels.get(this._newChannel))) {
         AaiChannelItem channelItem = adoc.createChannelItem(this._newChannel);
         adoc.addChannelItem(channelItem);
         this._channelExisted = false;
      } else {
         this._channelExisted = true;
      }
   }

   @Override
   public void undo(Document document) {
      LoggerCompat.info("[NewChannelCommand] Reverting.");
      AaiDocument adoc = (AaiDocument) document;
      if (this._channelExisted) {
         LoggerCompat.info("[NewChannelCommand] channel already existed, nothing done so no rollback necessary.");
         return;
      }
      if (this._emptyChannels) {
         adoc.channels.clear();
      } else {
         adoc.channels.remove(this._newChannel);
      }
   }
}
