package com.socialmediaapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialmediaapp.Entity.Reels;


@Repository
public interface ReelsRepository extends JpaRepository<Reels , Integer>{

    public List<Reels> findByUserId(int userId);
}
