package com.example.demo.form;

import org.springframework.format.annotation.DateTimeFormat;
//import com.example.demo.validation.CheckMailAddress;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/********************************
 * ■アカウント登録機能
 * 
 * ユーザーが画面から入力した値を保持するクラス
 *********************************/

@Getter
@Setter
@SuppressWarnings("serial")
public class RegisterForm implements Serializable{
	
	//ID：自動採番（画面入力不要）
	private Integer id;
	
	//名前
	@NotBlank(message = "エラー：名前を入力してください")
	@Size(min = 2, max = 30,message = "エラー：名前は2文字以上30文字以内で入力してください")
	@Pattern(regexp =  "^[^ -~｡-ﾟ]*$",message = "エラー：名前は全角で入力してください")						//全角チェック
	private String name;
	
	//フリガナ
	@NotBlank(message = "エラー：フリガナを入力してください")
	@Pattern(regexp = "^[ァ-ヶー　]*$",message = "エラー：フリガナは全角カタカナで入力してください")							//全角カナチェック
	private String kana;
	
	//電話番号
	@NotBlank(message = "エラー：電話番号を入力してください")
	@Pattern(regexp = "^[0-9]{2,4}-[0-9]{2,4}-[0-9]{4}$|^$",message = "エラー：電話番号はハイフンを入れて、正しい形式で入力してください")//電話番号チェック
	private String phone;
	
	//メールアドレス
	@Email(message = "エラー：メールアドレスの形式に誤りがあります")
    //@CheckMailAddress
	private String email;
	
	//生年月日
	@DateTimeFormat(pattern = "yyyy-MM-dd",fallbackPatterns = "エラー：誕生日は正しい形式で入力してください")
	private LocalDate birthday;
	
	//パスワード
	@NotBlank(message = "エラー：パスワードを入力してください")
	@Size(min=8, max=20,message = "エラー：パスワードは8文字～20文字で入力してください")									//文字数チェック
	@Pattern(regexp = "^[a-zA-Z0-9 ]*$",message = "エラー：パスワードは半角英数字で入力してください")					//半角英数字チェック
	private String password;
	
	//確認用パスワード
	@NotBlank(message = "エラー：確認用パスワードを入力してください")
	@Size(min=8, max=20,message = "エラー：パスワードは8文字～20文字で入力してください")									//文字数チェック
	@Pattern(regexp = "^[a-zA-Z0-9 ]*$",message = "エラー：パスワードは半角英数字で入力してください")	
	private String confirmpass;
	
	@AssertTrue(message = "エラー：パスワードと確認用パスワードが一致しません")//確認用パスワードの一致確認
	public boolean isPasswordValid() {
		if(password == null || confirmpass == null) {
			return false;
		}
		return password.equals(confirmpass);
	}
	
	//UUID
    private String uuid;
}
