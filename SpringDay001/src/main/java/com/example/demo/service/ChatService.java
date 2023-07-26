package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MessageEntity;
import com.example.demo.form.MessageForm;
import com.example.demo.repository.ChatRepository;

@Service
public class ChatService {

	/**Repositoryインスタンスの生成**/
    @Autowired
    ChatRepository chatRepository;
    
    /**
     * メッセージ登録
     * @param user ユーザー情報
     */
    public void create(MessageForm form) {
	
    	MessageEntity message = new MessageEntity();    	
    	//エンティティ(message)にフォーム(MessageForm.java)のデータを詰める
    	message.setId(form.getId());
    	//message.setUser_id();//ユーザーIDは画面入力しない。取得する★
    	message.setMessage(form.getStatement());
    	
    	//レポジトリでDB更新処理
        chatRepository.save(message);
    }
}
