package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.MessageEntity;

@Repository
public interface ChatRepository extends JpaRepository<MessageEntity, Integer> {
//MessageEntityはエンティティ、Integerは”@id”の型
}
