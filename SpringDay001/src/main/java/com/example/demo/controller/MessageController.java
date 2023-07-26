package com.example.demo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/********************
 * チャット機能専用のコントローラー
 * 
 ********************/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.util.HtmlUtils;
import com.example.demo.service.ChatService;
import com.example.demo.form.Message;
import com.example.demo.form.UploadForm;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.example.demo.entity.MyUserDetails;

@Controller
public class MessageController {

	/**ChatServiceのインスタンス設定**/
	 //@Autowired
	 //ChatService chatService;
	 
	 
	 /** チャット画面を表示 **/
	/**@GetMapping("/user/chat")
	String index(HttpServletResponse httpServletResponse,@AuthenticationPrincipal MyUserDetails user) {
		//セッションからユーザ情報を取得
		System.out.println(user.getUsername()); // userId
        System.out.println(user.getPassword()); //password
		return "user/chat";
	 }**/
	 
	/** チャット画面を表示 **/
	@GetMapping("/user/chat")
	String index(Model model,UploadForm uploadform) {
		//アップロードファイルをモデルにセット
		model.addAttribute("uploadForm", uploadform);
		return "user/chat";
	 }
	 
	 
	/** メッセージ送受信処理**/
	//"/message"宛先へのメッセージを処理するためのマッピング（"/message"当てに届いたメッセージを"/receive/message"宛てに送る）
    @MessageMapping("/message")
    @SendTo("/receive/message")
    public Message send(Message message) throws Exception {
      Thread.sleep(1000); //1秒間スリープ
      //名前とメッセージが格納されたMessageオブジェクトを作成して返す（戻り値は、"/receive/message"の全てのサブスクライバー（閲覧者）に送られる）
      return new Message(HtmlUtils.htmlEscape(message.getName()), HtmlUtils.htmlEscape(message.getStatement()));
    }
    
    
    /**public Message send(
    		HttpServletResponse httpServletResponse,
    		@AuthenticationPrincipal MyUserDetails user, 
    		Message message) throws Exception {
      Thread.sleep(1000); //1秒間スリープ
      //名前とメッセージが格納されたMessageオブジェクトを作成して返す（戻り値は、"/receive/message"の全てのサブスクライバー（閲覧者）に送られる）

      System.out.println(user.getUsername()); // userId
      return new Message(user.getUsername(), HtmlUtils.htmlEscape(message.getStatement()));
      
      //メッセージ登録サービスのcreateメソッドを呼び出し、DBに新規ユーザ情報を登録
	  //chatService.create(messageForm);
    }**/
    

    /**
     * ファイルアップロード処理
     * @param uploadForm
     * @return
     */
    @PostMapping("/user/chat")
    public String handleImageUpload(@RequestParam("imageFile") MultipartFile imageFile) {
    	//送信されたファイルデータがある場合は、アップロード実行
    	if (!imageFile.isEmpty()) {
    		uploadAction(imageFile);//アップロード実行処理
    	}
    	return "/user/chat";
    }
    /**
     * アップロード実行処理
     * @param multipartFile
     */
    private void uploadAction(MultipartFile multipartFile) {
        //ファイル名取得
        String fileName = multipartFile.getOriginalFilename();

        //格納先のフルパス ※事前に格納先フォルダ「UploadTest」をCドライブ直下に作成しておく
        Path filePath = Paths.get("C:/UploadTest/" + fileName);
        try {
            //アップロードファイルをバイト値に変換
            byte[] bytes  = multipartFile.getBytes();
            //バイト値を書き込む為のファイルを作成して指定したパスに格納
            OutputStream stream = Files.newOutputStream(filePath);
            //ファイルに書き込み
            stream.write(bytes);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
