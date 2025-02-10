package com.socialmediaapp.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.socialmediaapp.Entity.Message;
import com.socialmediaapp.Entity.User;

@Service
public interface MessageService {

    public Message creatMessage(User user, int chatId, Message req) throws Exception;

    public List<Message> findChatsMessages(int chatId) throws Exception;


}
