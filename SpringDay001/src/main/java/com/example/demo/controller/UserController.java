package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.form.RegisterForm;
import com.example.demo.service.SignupService;


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
	 SignupService signupService;
	 
/**
 * 【アカウント機能】
 */
	/** アカウント登録（入力画面）を表示**/
	@GetMapping("/user/input")
	public String displayInput(Model model,RegisterForm form) {  
		  model.addAttribute("form",form);
		  return "user/input";
	 }
	
	/** アカウント登録（確認画面）を表示**/
	@PostMapping("/user/input")
	public String displayConfirm(
				 @Validated @ModelAttribute RegisterForm registerform,
				 BindingResult result,Model model){
		  
		 //入力エラーがある場合は、入力画面でエラー箇所を表示
		 if (result.hasErrors()) {
			 List<String> errorList = new ArrayList<String>();
	         for (ObjectError error : result.getAllErrors()) {
	        	 errorList.add(error.getDefaultMessage());
	        	 }
	         model.addAttribute("validationError", errorList);
			 return "user/input";
	      }
		  //エラーがない場合は、確認画面を表示
		  return "user/confirm";
	  }
	  
	/** アカウント登録（完了画面）を表示**/
	 @PostMapping("/user/confirm")
	 public String register(RegisterForm form,Model model){ 
	
		  //アカウント登録サービスのcreateメソッドを呼び出し、DBに新規ユーザ情報を登録
		  signupService.create(form);
		  //完了画面を表示
		  return "user/complete";
	  }
	
/**
 * 【ログイン・ログアウト機能】
 */
	 /** ログイン画面を表示 **/
	 @GetMapping("/user/login")
	 public String displayLogin() {
		 return "user/login";
	  }
	 
	 /** ログイン（トップ画面を表示） **/
	 @GetMapping("/user/top")
	 public String showTop() {
		 return "user/top";
	 }
	 
	 /** ログアウト（ログイン画面を表示） **/
	 @GetMapping("/user/logout")
	 public String displayLogout() {
		 return "user/login";
	  }
	 
	 /**
	  * 【チャット機能】→MessageControllerで別途記載
	  **/
	 
}
