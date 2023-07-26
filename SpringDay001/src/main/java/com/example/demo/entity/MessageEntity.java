package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Column;//javaxは最新のSpringBootのバージョンでjakartaに置き換えらえている（参考：http://kazuki-room.com/post-6362/）
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*ユーザー情報エンティティ*/
@Data//＠Dataを書くと、Getter,Setter省略可能
@Getter
@Setter
@Table(name="user")
@Entity
@SuppressWarnings("serial")
public class MessageEntity implements Serializable{
		
//ID
	@Id	//主キーを指定
	@Column(name = "id")
	//DBでオートインクリメントを使用している場合、このアノテーションを付けないとエラーとなる
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//ユーザーID
	@Column(name = "user_id")
	private Integer user_id;

//メッセージ
	@Column(name = "message")
	private String message;
		
	    
}




