package com.fatstack.circularqueue.models;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.io.Serializable;

public class Chatroom implements Serializable {
    private String roomName;
    private CircularFifoQueue<Message> messages;

    public Chatroom(String roomName, CircularFifoQueue<Message> messages) {
        this.roomName = roomName;
        this.messages = messages;
    }

    public Chatroom() {
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public CircularFifoQueue<Message> getMessages() {
        return messages;
    }

    public void setMessages(CircularFifoQueue<Message> messages) {
        this.messages = messages;
    }
}
