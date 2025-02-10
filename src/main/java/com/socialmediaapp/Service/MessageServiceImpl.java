package com.socialmediaapp.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmediaapp.Entity.Chat;
import com.socialmediaapp.Entity.Message;
import com.socialmediaapp.Entity.User;
import com.socialmediaapp.Repository.ChatRepository;
import com.socialmediaapp.Repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Message creatMessage(User user, int chatId, Message req) throws Exception {

        Chat chat = chatService.findChatById(chatId);
        Message message = new Message();
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setTimestamp(LocalDateTime.now());
        message.setUser(user);
        message.setChat(chat);
        Message savedMessage = messageRepository.save(message);
        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);
        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(int chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);
        return messageRepository.findByChatId(chat.getId());
    }

}
