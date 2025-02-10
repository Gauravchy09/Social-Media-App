package com.socialmediaapp.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmediaapp.Entity.Chat;
import com.socialmediaapp.Entity.User;
import com.socialmediaapp.Repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, User user2) {
        Chat isExist = chatRepository.findChatByUsersId(user2,reqUser);
        if(isExist != null) {
            return isExist;
        }
        Chat chat = new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimestamp(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(int chatId) throws Exception {
        Optional<Chat> chat = chatRepository.findById(chatId);
        if(chat.isEmpty()) {
            throw new Exception("chat not found with id: "+chatId);
        }
        return chat.get();    
    }

    @Override
    public List<Chat> findUserChat(int userId) {
        return chatRepository.findByUsersId(userId);
    }

}
