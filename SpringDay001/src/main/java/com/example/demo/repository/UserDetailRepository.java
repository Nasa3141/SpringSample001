package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;
/**
 * ログイン機能用Repository
 * 　→ログイン情報をDBと照合して、一致するユーザ情報を返す
 */

@Repository
//MyUserDetailsはエンティティ、Integerは”@id”の型
public interface UserDetailRepository extends JpaRepository<Account, Integer> {
	//ユーザー名で検索をして一致するユーザ情報を返す　★後からID検索に変更する
	Account findByEmail(String email);
}
