/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wirez.core.client.notification;

import org.wirez.core.api.command.Command;
import org.wirez.core.api.notification.Notification;
import org.wirez.core.api.util.UUID;

import java.util.Iterator;

public class CanvasCommandAllowedNotification implements Notification<Command> {

    final String notificationUUID;
    final String canvasUUID;
    final Command command;
    final boolean isAllowed;

    public CanvasCommandAllowedNotification(String canvasUUID, Command command, boolean isAllowed) {
        this.notificationUUID = UUID.uuid();
        this.canvasUUID = canvasUUID;
        this.command = command;
        this.isAllowed = isAllowed;
    }

    @Override
    public String getNotificationUUID() {
        return notificationUUID;
    }

    @Override
    public Type getType() {
        return isAllowed ? Type.INFO : Type.ERROR;
    }

    @Override
    public String getSource() {
        return canvasUUID;
    }

    @Override
    public Command getContext() {
        return command;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    @Override
    public String toString() {
        return "CanvasCommandAllowedNotification [canvasUUID=" + canvasUUID + ", command=" +  command.toString() + ", allowed=" + isAllowed + "]";
    }

}
