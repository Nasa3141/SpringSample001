package com.example.demo.repository;
/**
 * ユーザー情報 Repository
 * 
 * リポジトリはDB操作に用いる。
 * JpaRepository（インターフェース）を継承することで、DB操作に使えるメソッドがそのまま利用できる
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;

//@Mapper
@Repository
public interface UserRepository extends JpaRepository<Account, Integer> {
	//Accountはエンティティ、Integerは”@id”の型
}