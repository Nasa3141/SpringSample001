package com.example.demo.form;

import lombok.Data;

/********************************
 * ■チャット機能
 * 
 * ユーザーが画面から入力したメッセージを保持するクラス
 *********************************/

@Data
public class MessageForm {

	private Integer id;
	private String name;
	private String statement;

	public MessageForm(Integer id,String name, String statement) {
		this.id = id;
		this.name = name;
		this.statement = statement;
	}
}