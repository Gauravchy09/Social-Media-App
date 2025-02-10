package com.socialmediaapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialmediaapp.Entity.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

    public List<Story> findByUserId(int userId);
}
