package org.acme.socket

import java.util.concurrent.ConcurrentHashMap
import javax.enterprise.context.ApplicationScoped
import javax.websocket.OnClose
import javax.websocket.OnError
import javax.websocket.OnMessage
import javax.websocket.OnOpen
import javax.websocket.Session
import javax.websocket.server.PathParam
import javax.websocket.server.ServerEndpoint

@ApplicationScoped
@ServerEndpoint("/chat/{username}")
class ChatSocket {

    private val sessionMap = ConcurrentHashMap<String, Session>()

    @OnOpen
    fun onOpen(session: Session, @PathParam("username") username: String) {
        sessionMap[username] = session
        sendMessage("User $username logged in")
    }

    @OnMessage
    fun onMessage(message: String, @PathParam("username") username: String) {
        sendMessage(">> $username: $message")
    }

    @OnClose
    fun onClose(session: Session, @PathParam("username") username: String) {
        sessionMap.remove(username)
        sendMessage("User $username logged out")
    }

    @OnError
    fun onError(session: Session, @PathParam("username") username: String, throwable: Throwable) {
        sessionMap.remove(username)
        throwable.printStackTrace()
        sendMessage("User $username logged out due to an error")
    }

    private fun sendMessage(message: String) {
        sessionMap.values.forEach { session ->
            session.asyncRemote.sendObject(message) { result ->
                if (result.exception != null) {
                    result.exception.printStackTrace()
                }
            }
        }
    }
}
