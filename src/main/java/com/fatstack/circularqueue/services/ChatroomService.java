package com.fatstack.circularqueue.services;

import com.fatstack.circularqueue.models.Chatroom;
import com.fatstack.circularqueue.models.Message;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatroomService {
    private final RedisTemplate<String, Chatroom> redisTemplate;

    @Autowired
    public ChatroomService(RedisTemplate<String, Chatroom> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Chatroom addChatroom(String chatroomName) {
        var newRoom = new Chatroom();
        newRoom.setRoomName(chatroomName);
        newRoom.setMessages(new CircularFifoQueue<>(5));
        redisTemplate
                .opsForValue()
                .set(chatroomName, newRoom);
        return newRoom;
    }

    public Chatroom addMessage(Message message, String roomName) {
        var room = redisTemplate.opsForValue().get(roomName);
        var messages = room.getMessages();
        messages.add(message);
        room.setMessages(messages);
        redisTemplate.opsForValue().set(roomName, room);
        return room;
    }
}
