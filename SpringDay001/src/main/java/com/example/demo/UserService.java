package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.RegisterForm;
//User情報　Repositoryのインポート
import com.example.demo.UserRepository;

/**
 * ユーザー情報 Service
 */
@Service
public class UserService {

    /**Repositoryインスタンスの生成**/
    @Autowired
    UserRepository userRepository;
    
    /**
     * ユーザー情報 全検索
     * @return 検索結果
     */
    /**public List<User> findAll() {
        return userRepository.findAll();// ユーザーTBLの内容を全検索
    }**/
    
    
    
    /**
     * ユーザー情報 新規登録
     * @param user ユーザー情報
     */
    public void create(RegisterForm form) {
    	
    	User user = new User();
    	
    	//エンティティ(User.java)にフォーム(RegisterForm.java)のデータを詰める
    	user.setId(form.getId());
    	user.setName(form.getName());
    	user.setKana(form.getKana());
    	user.setPhone(form.getPhone());
    	user.setEmail(form.getEmail());
    	//user.setBirthday(form.getBirthday());
    	user.setPassword(form.getPassword());
    	
    	//レポジトリでDB更新処理
        userRepository.save(user);
    }

}

