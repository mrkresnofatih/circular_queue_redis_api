package com.fatstack.circularqueue.controllers;

import com.fatstack.circularqueue.models.Chatroom;
import com.fatstack.circularqueue.models.Message;
import com.fatstack.circularqueue.services.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatrooms")
public class ChatroomController {
    private final ChatroomService chatroomService;

    @Autowired
    public ChatroomController(ChatroomService chatroomService) {
        this.chatroomService = chatroomService;
    }

    @PostMapping("/new_room/{chatroomName}")
    public ResponseEntity<Chatroom> addRoom(@PathVariable String chatroomName) {
        var newRoom = chatroomService.addChatroom(chatroomName);
        return new ResponseEntity<>(newRoom, HttpStatus.OK);
    }

    @PostMapping("/new_message/{roomName}")
    public ResponseEntity<Chatroom> addMessage(@RequestBody Message message, @PathVariable String roomName){
        var res = chatroomService.addMessage(message, roomName);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
