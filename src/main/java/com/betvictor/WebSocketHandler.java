/*
 * Copyright 2012-2015 the original author or authors.
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

package com.betvictor;

import com.betvictor.data.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebSocketHandler extends TextWebSocketHandler {

	private static Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    private List<WebSocketSession> sessions;

    public void broadcastMessage(String message) {
        for(WebSocketSession session : sessions) {
            try {
                if(session.isOpen()){
                    session.sendMessage(new TextMessage(message));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


	@Autowired
	public WebSocketHandler() {
        sessions = new ArrayList<WebSocketSession>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		logger.debug("Opened new session in instance " + this);
        sessions.add(session);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws Exception {
		logger.debug("echoMessage");
		session.sendMessage(new TextMessage("echoMessage"));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception)
			throws Exception {
		session.close(CloseStatus.SERVER_ERROR);
	}

    public void broadcastDelete(Employee employee) {
        StringBuilder message = new StringBuilder();
        message.append(employee.toString());
        message.append(" | deleted");

        broadcastMessage(message.toString());
    }

    public void broadcastAdd(Employee employee) {
        StringBuilder message = new StringBuilder();
        message.append(employee.toString());
        message.append(" | added");

        broadcastMessage(message.toString());
    }

    public void broadcastUpdated(Employee from, Employee to) {
        StringBuilder message = new StringBuilder();
        message.append(from.toString());
        message.append(" | updated | ");
        message.append(to.toString());

        broadcastMessage(message.toString());
    }
}
