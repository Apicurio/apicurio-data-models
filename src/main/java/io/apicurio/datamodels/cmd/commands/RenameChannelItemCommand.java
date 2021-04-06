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

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.util.ModelUtils;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A command used to rename a channel item, along with all references to it.
 * @author c.desc2@gmail.com
 */
public class RenameChannelItemCommand extends AbstractCommand {

    public String _oldChannelName;
    public String _newChannelName;
    
    public boolean _channelExisted;
    public boolean _nullParams;
    @JsonIgnore
    public final Map<String, Object> _paramBin = new LinkedHashMap<>();
    @JsonIgnore
    public final Aai20NodeFactory _nodeFactory = new Aai20NodeFactory();

    RenameChannelItemCommand() {
    }

    RenameChannelItemCommand(String oldChannelName, String newChannelName) {
        this._oldChannelName = oldChannelName;
        this._newChannelName = newChannelName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[RenameChannelItemCommand] Executing.");
        final Aai20Document aai20Document = (Aai20Document) document;
        if (ModelUtils.isNullOrUndefined(aai20Document.channels)) {
            return;
        }
        if (aai20Document.channels.containsKey(_newChannelName)) {
            this._channelExisted = true; 
            return;
        } else {
            this._channelExisted = false;
        }
        
        AaiChannelItem aaiChannel = aai20Document.channels.remove(this._oldChannelName);

        // If we didn't find a aaiChannel with the "from" name, then nothing to do.
        if (this.isNullOrUndefined(aaiChannel)) {
            return;
        }

        // Now we have the aaiChannel - rename it!
        aaiChannel.rename(this._newChannelName);
        aai20Document.channels.put(this._newChannelName, aaiChannel);
        
        // Update the channel parameters
        this._nullParams = ModelUtils.isNullOrUndefined(aaiChannel.parameters);
        this._doParametersRename(aaiChannel, this._oldChannelName, this._newChannelName);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[RenameChannelItemCommand] Reverting.");
        final Aai20Document aai20Document = (Aai20Document) document;
        if (ModelUtils.isNullOrUndefined(aai20Document.channels)) {
            return;
        }
        if (this._channelExisted) {
            return;
        }
        AaiChannelItem aaiChannel = aai20Document.channels.remove(this._newChannelName);

        // If we didn't find a aaiChannel with the "from" name, then nothing to do.
        if (this.isNullOrUndefined(aaiChannel)) {
            return;
        }

        // Now we have the aaiChannel - rename it!
        aaiChannel.rename(this._oldChannelName);
        aai20Document.channels.put(this._oldChannelName, aaiChannel);
        
        if (this._nullParams) {
            NodeCompat.setProperty(aaiChannel, Constants.PROP_PARAMETERS, null);
        } else {
            // Update the channel parameters
            this._doParametersRename(aaiChannel, this._newChannelName, this._oldChannelName);
        }
    }

    /**
     * Does the work of renaming parameters for an aai channel.
     *
     * @param aaiChannel
     * @param from
     * @param to
     * @private
     */
    private void _doParametersRename(AaiChannelItem aaiChannel, String from, String to) {
        List<String> fromChannelParamNames = ModelUtils.detectPathParamNames(from);
        List<String> toChannelParamNames = ModelUtils.detectPathParamNames(to);
        if (fromChannelParamNames.size() != toChannelParamNames.size()) {
            // TODO uh oh - what to do here?
            LoggerCompat.warn("Renaming a channel with %o parameters to a new value that has %o parameters!",
                    fromChannelParamNames.size(), toChannelParamNames.size());
        }
        final List<String[]> parameterRenames = _processPathParameterRenames(fromChannelParamNames, toChannelParamNames);

        for (String[] namePair : parameterRenames) {
            if (ModelUtils.isNullOrUndefined(namePair[0])) {
                _createChannelParameter(aaiChannel, namePair[1]);
            } else if (ModelUtils.isNullOrUndefined(namePair[1])) {
                _removeChannelParameter(aaiChannel, namePair[0]);
            } else {
                _renameChannelParameter(aaiChannel, namePair[0], namePair[1]);
            }
        }
        if (ModelUtils.isDefined(aaiChannel.parameters) && aaiChannel.parameters.isEmpty()) {
            // Remove the empty node 
            NodeCompat.setProperty(aaiChannel, Constants.PROP_PARAMETERS, null);
        }
    }

    /**
     * Preprocesses the parameter renames mapping
     *
     * @param fromChannelParamNames the original channel parameter names
     * @param toChannelParamNames   the new channel parameter names
     * @return a collection of [oldName, newName] pairs, null values mark fields as inexistent on either side
     */
    private List<String[]> _processPathParameterRenames(List<String> fromChannelParamNames, List<String> toChannelParamNames) {
        final List<String[]> ret = new ArrayList<>();
        final List<String> knownParamNames = new ArrayList<>();
        final List<String> addedParamNames = new ArrayList<>();
        final List<String> removedParamNames = new ArrayList<>();
        fromChannelParamNames.forEach(name -> {
            if (!knownParamNames.contains(name)) {
                knownParamNames.add(name);
                if (!toChannelParamNames.contains(name)) {
                    removedParamNames.add(name);
                }
            }
        });
        toChannelParamNames.forEach(name -> {
            if (!knownParamNames.contains(name)) {
                knownParamNames.add(name);
                if (!fromChannelParamNames.contains(name)) {
                    addedParamNames.add(name);
                }
            }
        });
        int minIndex = Math.min(removedParamNames.size(), addedParamNames.size());
        int idx = 0;
        while (idx < minIndex) { // Rename mapping for recoverable parameters
            ret.add(new String[] { removedParamNames.get(idx), addedParamNames.get(idx) });
            idx++;
        }
        while (idx < removedParamNames.size()) { // If parameters have to be removed
            ret.add(new String[] { removedParamNames.get(idx), null });
            idx++;
        }
        while (idx < addedParamNames.size()) { // If parameters have to be created
            ret.add(new String[] { null, addedParamNames.get(idx) });
            idx++;
        }
        return ret;
    }

    /**
     * Create a channel parameter.
     *
     * @param channel
     * @param paramName
     */
    private void _createChannelParameter(AaiChannelItem channel, String paramName) {
        Map<String, AaiParameter> parameters = channel.parameters;
        if (ModelUtils.isNullOrUndefined(parameters)) {
            parameters = new LinkedHashMap<>();
            NodeCompat.setProperty(channel, Constants.PROP_PARAMETERS, parameters);
        }
        final AaiParameter parameter = _nodeFactory.createParameter(channel, paramName);
        Object restorable = _paramBin.remove(paramName);
        if (ModelUtils.isDefined(restorable)) {
            Library.readNode(restorable, parameter);
        }
        parameters.put(paramName, parameter);
    }

    /**
     * Rename a channel parameter.
     *
     * @param channel
     * @param fromParamName
     * @param toParamName
     */
    private void _renameChannelParameter(AaiChannelItem channel, String fromParamName, String toParamName) {
        if (ModelUtils.isDefined(channel.parameters)) {
            final AaiParameter param = channel.parameters.remove(fromParamName);
            if (ModelUtils.isDefined(param)) {
                param.rename(toParamName);
                channel.addParameter(toParamName, param);
            }
        }
    }

    /**
     * Remove a channel parameter.
     *
     * @param channel
     * @param paramName
     */
    private void _removeChannelParameter(AaiChannelItem channel, String paramName) {
        if (this.isNullOrUndefined(channel.parameters)) {
            return;
        }
        final AaiParameter removed = channel.parameters.remove(paramName);
        if (ModelUtils.isDefined(removed)) {
            _paramBin.put(paramName, Library.writeNode(removed));
        }
    }
}
