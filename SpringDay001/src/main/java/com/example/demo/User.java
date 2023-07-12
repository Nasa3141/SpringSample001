package com.example.demo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import jakarta.persistence.Column;	
//javaxは最新のSpringBootのバージョンでjakartaに置き換えらえている（参考：http://kazuki-room.com/post-6362/）
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*ユーザー情報エンティティ*/
@Data//＠Dataを書くと、Getter,Setter省略可能
@Getter
@Setter
@Table(name="user")
@Entity
public class User implements Serializable{
	
//ID
	@Id	//主キーを指定
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//名前
	@Column(name = "name")
	private String name;
	
//名前（ふりがな）
	@Column(name = "kana")
	private String kana;
	
//電話番号
	@Column(name = "phone")
	private String phone;

//メールアドレス
    @Column(name = "email")
    private String email;

//生年月日
    @Column(name = "birthday")
    private LocalDate birthday;

//パスワード
    @Column(name = "password")
    private String password;
    
}

