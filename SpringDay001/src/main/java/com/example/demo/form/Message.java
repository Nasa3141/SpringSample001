package com.example.demo.form;

import lombok.Data;

/********************************
 * ■チャット機能
 * 
 * ユーザーが画面から入力したメッセージを保持するクラス
 *********************************/
@Data
public class Message {

	private String name;
	private String statement;

	public Message(String name, String statement) {
		this.name = name;
		this.statement = statement;
	}
}