package com.socialmediaapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialmediaapp.Entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message , Integer> {

    public List<Message> findByChatId(int chatId);
}   
