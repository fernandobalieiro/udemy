package org.acme.socket

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
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
        sendPublicMessage("User $username logged in")
    }

    @OnMessage
    fun onMessage(message: String, @PathParam("username") username: String) {
        val messageChat = mapper.readValue(message, Message::class.java)
        if (messageChat.destination.isBlank()) {
            sendPublicMessage(">> $username: ${messageChat.message}")
        } else {
            sendPrivateMessage(">> $username: ${messageChat.message}", username, messageChat.destination)
        }
    }

    @OnClose
    fun onClose(session: Session, @PathParam("username") username: String) {
        sessionMap.remove(username)
        sendPublicMessage("User $username logged out")
    }

    @OnError
    fun onError(session: Session, @PathParam("username") username: String, throwable: Throwable) {
        sessionMap.remove(username)
        throwable.printStackTrace()
        sendPublicMessage("User $username logged out due to an error")
    }

    private fun sendPrivateMessage(message: String, userFrom: String, usernameTo: String) {
        if (sessionMap[usernameTo] == null) {
            sessionMap[userFrom]?.let { session -> sendMessage(session, "User $usernameTo not found") }
        } else {
            sessionMap[usernameTo]?.let { session -> sendMessage(session, message) }
        }
    }

    private fun sendPublicMessage(message: String) {
        sessionMap.values.forEach { session ->
            sendMessage(session, message)
        }
    }

    private fun sendMessage(session: Session, message: String) {
        session.asyncRemote.sendObject(message) { result ->
            if (result.exception != null) {
                result.exception.printStackTrace()
            }
        }
    }

    private companion object {
        val mapper = jacksonObjectMapper()
    }
}
