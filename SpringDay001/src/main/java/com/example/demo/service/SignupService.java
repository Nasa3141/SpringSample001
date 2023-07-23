package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.form.RegisterForm;
import com.example.demo.repository.UserRepository;

/**
 * SignupService　・・・・・アカウント登録
 */
@Service
public class SignupService {
	
	//パスワードエンコーダー
	@Autowired
	private PasswordEncoder passwordEncoder;

    /**Repositoryインスタンスの生成**/
    @Autowired
    UserRepository userRepository;
    
    /**
     * アカウント登録
     * @param user ユーザー情報
     */
    public void create(RegisterForm form) {
	
    	Account account = new Account();    	
    	
    	//UUIDには、ランダムに生成した一意のパスワードを設定する
        UUID uuid = UUID.randomUUID();// UUIDを生成
    	account.setUuid(uuid.toString());// UUIDをStringに変換して格納
    	
    	//エンティティ(account.java)にフォーム(RegisterForm.java)のデータを詰める
    	account.setId(form.getId());
    	account.setName(form.getName());
    	account.setKana(form.getKana());
    	account.setPhone(form.getPhone());
    	account.setEmail(form.getEmail());
    	account.setBirthday(form.getBirthday());
    	
    	String hashedPass = passwordEncoder.encode(form.getPassword());//パスワードはハッシュ化
    	account.setPassword(hashedPass);
    	
    	//レポジトリでDB更新処理
        userRepository.save(account);
    }

}

