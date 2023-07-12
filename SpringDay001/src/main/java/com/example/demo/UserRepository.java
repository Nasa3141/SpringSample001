package com.example.demo;
/**
 * ユーザー情報 Repository
 * 
 * リポジトリはDB操作に用いる。
 * JpaRepository（インターフェース）を継承することで、DB操作に使えるメソッドがそのまま利用できる
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.apache.ibatis.annotations.Mapper;
import com.example.demo.User;

//@Mapper
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	//Userはエンティティ、Integerは”@id”の型

	//ユーザー名で検索をして一致するユーザ情報を返す　★後からID検索に変更する
	User findByName(String username);
}