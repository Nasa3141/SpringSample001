$(function () {       
    //接続ボタンクリック時
     $("#connect").on('click', function(e) {
		 e.preventDefault();//★これないとチャット送信できない
          stompClient.activate();
     });
	
	//切断ボタンクリック時
     $("#disconnect").on('click', function(e) {
          e.preventDefault();//★これないとチャット送信できない
          stompClient.deactivate();
          setConnected(false);
          console.log("Disconnected");
     });
     
    //送信ボタンクリック時
     $("#send").on('click', function(e) {
		 e.preventDefault();//★これないとチャット送信できない
		 stompClient.publish({
			 destination: "/send/message",
			 body: JSON.stringify({'name': $("#name").val(), 'statement': $("#statement").val()})
			 });
     });
    //画像ファイル選択時
   	$("#img_fileData").change(() => handleFileSelect());
   	//動画ファイル選択時
    $("#mv_fileData").change(() => handleFileSelect()); 
});

/*********************
 * チャット
 *********************/
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/websocket'
});
stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    
    stompClient.subscribe('/receive/message', (message) => {
        showMessage(JSON.parse(message.body));
    });
};
stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};
stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

//接続設定メソッド
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#message").html("");
}

//メッセージ表示
function showMessage(message) {
    $("#message").append( "<tr><td>" + message.name + ": " + message.statement + "</td></tr>");
}

//選択ファイル確認
function handleFileSelect() {
	const sizeLimit = 1024 * 1024 * 1;　// 制限サイズ（1MB）
	const file = document.getElementById('img_fileData');//画像ファイルの要素取得
	const selectfiles = file.files;//ファイル一覧を取得

//動画ファイルの要素取得　　　　★後で実装する*********************************
//const mv_fileInput = document.getElementById('mv__fileData');
//const mv_files = img_fileInput.files;
//************************************************************* 
	
	//選択されたファイル一覧から、ループ処理で中身を一つずつ取り出している
	for (let i = 0; i < selectfiles.length; i++) {
    	console.log(selectfiles[i]);　// 1つ1つのファイルデータはfiles[i]で取得できる  
    	
      // ファイルサイズが制限以上の場合はエラー表示
	    if (selectfiles[i].size > sizeLimit) {
	      alert('ファイルサイズは1MB以下にしてください'); // エラーメッセージを表示
	      fileInput.value = ''; // inputの中身をリセット
	      return;// この時点で処理を終了する
	      }
  	}
}

/*
*　ファイルアップロード処理
*
*CSRFトークンを取得してから、送信処理を行う
*/
/**async function executeApi() {
	
    //CSRFトークンを取得
    let csrfToken = $('input[name="_csrf"]').val();

	//画像ファイルの要素取得
	const inputfile = document.querySelector("#img_fileData");	
	const file = inputfile.files[0];
	// フォームデータを作成
	const formData = new FormData();
	formData.append("img", file);// imgというフィールド名でファイルを追加
    
    //以下処理の実行は、CSRFトークンを取得するまで待機する
    let response = "";
    await fetch("/upload", {
        method: 'POST'
    ,   body: formData
    ,   headers: {
			'X-CSRF-TOKEN': csrfToken,
            'X-XSRF-TOKEN' : csrfToken,
        }
    })
    .then(response =>  response.text())
    .then(data => {
        response = data;
    });

    console.log(response);
}

//無限スクロール機能
/**document.querySelectorAll('.scroll').forEach(elm => {
	elm.onscroll = function () {
		if (this.scrollTop + this.clientHeight >= this.scrollHeight) {
			//スクロールが末尾に達した
			if (parseInt(this.dataset.lastnum) < parseInt(this.dataset.max)) {
				//未ロードの画像がある場合
				this.dataset.lastnum = parseInt(this.dataset.lastnum) + 1;
				let img = document.createElement('img');
				img.src =  this.dataset.lastnum +'.jpg';
				this.appendChild(img);
			}
		}
	};
});**/