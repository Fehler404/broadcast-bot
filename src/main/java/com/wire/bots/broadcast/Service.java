//
// Wire
// Copyright (C) 2016 Wire Swiss GmbH
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program. If not, see http://www.gnu.org/licenses/.
//

package com.wire.bots.broadcast;

import com.wire.bots.broadcast.model.Config;
import com.wire.bots.broadcast.resource.BroadcastResource;
import com.wire.bots.broadcast.tasks.ListBroadcastsTask;
import com.wire.bots.broadcast.tasks.ListMessagesTask;
import com.wire.bots.sdk.MessageHandlerBase;
import com.wire.bots.sdk.Server;
import io.dropwizard.setup.Environment;

public class Service extends Server<Config> {
    public static void main(String[] args) throws Exception {
        new Service().run(args);
    }

    @Override
    protected MessageHandlerBase createHandler(Config config, Environment env) {
        return new BroadcastMessageHandler(repo, config);
    }

    @Override
    protected void onRun(Config config, Environment env) {
        addResource(new BroadcastResource(repo, config), env);

        addTask(new ListMessagesTask(config), env);
        addTask(new ListBroadcastsTask(config), env);
    }
}
