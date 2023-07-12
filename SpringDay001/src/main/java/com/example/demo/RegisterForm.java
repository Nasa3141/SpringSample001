package com.example.demo;

//import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
//import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
/**
 * 
 * フォーム：ユーザーが画面から入力した値を保持するクラス
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm implements Serializable{
	//@NotBlank(message = "エラー：IDを入力してください")
	private Integer id;//ID
	
	//@NotBlank(message = "エラー：名前を入力してください")
	private String name;//名前
	
	//@NotBlank(message = "エラー：フリガナを入力してください")
	private String kana;//フリガナ
	
	//@NotBlank(message = "エラー：電話番号を入力してください")
	private String phone;//電話番号

	private String email;//メールアドレス
	
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;//生年月日
	
	//@NotBlank(message = "エラー：パスワードを入力してください")
	//@Length(min=6)
	private String password;//パスワード
}
