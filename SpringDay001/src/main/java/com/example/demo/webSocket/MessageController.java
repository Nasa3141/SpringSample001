package com.example.demo.webSocket;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.util.HtmlUtils;

import com.example.demo.form.RegisterForm;
import com.example.demo.webSocket.UploadForm;

@Controller
public class MessageController {

	 /** チャット画面を表示 **/
	@GetMapping("/user/index")
	String index(Model model,UploadForm uploadform) {
		//アップロードファイルをモデルにセット
		model.addAttribute("uploadForm", uploadform);
		return "user/index";
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
    

    /**
     * ファイルアップロード処理
     * @param uploadForm
     * @return
     */
    @PostMapping("/upload")
    String upload(UploadForm uploadForm) {
        //フォームで渡されてきたアップロードファイルを取得
        MultipartFile multipartFile = uploadForm.getMultipartFile();

        //アップロード実行処理メソッド呼び出し
        uploadAction(multipartFile);

        //リダイレクト
        return "redirect:/";
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
    
	 /** 無限スクロールのテスト用
	 @GetMapping("/user/scroll")
	 public String showscroll() {
		 return "user/scroll";
	 }**/
    
}
