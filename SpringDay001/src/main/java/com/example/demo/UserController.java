package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

//User情報　Entityのインポート
//import com.example.demo.User;
//User情報　Serviceのインポート
//import com.example.demo.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**********************
 ユーザー情報 Controller
 
 ■　memo:
 Stringbootでは、Controller＞Service＞Repositoryの3階層で構成
 
 ・プレゼンテーション層（Controller）・・・リクエストの受付／バリデーション／レスポンスの返却
 ・ビジネス層（Service）・・・それ以外
 ・インテグレーション層（Repository）・・・データベース操作や外部API
 
 ※参考：https://springhack.com/web%E3%82%A2%E3%83%97%E3%83%AA%E3%82%B1%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%E3%81%AE3%E5%B1%A4%E6%A7%8B%E9%80%A0/
**********************/
@Controller
@SessionAttributes(types=RegisterForm.class)
public class UserController {
	/**
	 *  オブジェクトをHTTPセッションに追加する
     */
    @ModelAttribute("registerForm")
    public RegisterForm setForm(){
        return new RegisterForm();
    }
    
/**ユーザー情報Service**/
  @Autowired
  UserService userService;
  
/** アカウント登録画面を表示**/
  @GetMapping("/user/input")
  public String displayInput(
		  Model model,
		  RegisterForm form) {
	  
	  model.addAttribute("form",form);
	  return "user/input";
  }
  //Model →ControllerからHTML画面へデータを受け渡すための機能（インターフェース）

/** 確認画面を表示**/
  @PostMapping("/user/input")
  public String displayConfirm(
		  @ModelAttribute("registerForm") @Validated RegisterForm registerForm,
		  BindingResult result,
		  Model model){
	  
	/**入力チェック**/
	  //エラーがある場合は、自画面"input.html"を表示（エラー箇所を表示）
	  /**if (result.hasErrors()) {
		  return "user/input";
      }**/
	  //エラーがない場合は、確認画面"confirm.html"を表示
	  return "user/confirm";
  }
  
/** アカウント登録**/
  @PostMapping("/user/confirm")
  public String register(
		   RegisterForm form,
		  Model model){ 

	  userService.create(form);//サービスクラスのcreateメソッドを呼び出し
	  return "user/complete";//DB登録が終わったら完了画面を表示
  }

  
/** ログイン画面を表示 **/
  @GetMapping("/user/login")
  public String displayLogin() {
	  return "user/login";
  }
 
/** トップ画面（ログイン後の画面）を表示 **/
  @PostMapping("/user/login")
  public String testPage() {
      return "user/top";
  }
  
}
