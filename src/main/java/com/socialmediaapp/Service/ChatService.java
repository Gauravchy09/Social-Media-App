package com.socialmediaapp.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.socialmediaapp.Entity.Chat;
import com.socialmediaapp.Entity.User;

@Service
public interface ChatService {

    public Chat createChat(User reqUser, User user2);

    public Chat findChatById(int chatId) throws Exception;

    public List<Chat> findUserChat(int userId);

}
