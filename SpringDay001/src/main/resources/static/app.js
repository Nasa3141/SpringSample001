//処理を予約する
$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
    //$("#img_fileData").change(() => handleFileSelect());//画像ファイル選択されたら、handleFileSelectメソッドを実行する
    //$("mv_fileData").change(() => handleFileSelect());
    //$("#upload_form").submit(() => fileUpload());// アップロードボタンを押下したら、fileUploadメソッドを実行する     
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

//接続
function connect() {
    stompClient.activate();
}

//切断
function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

//送信
function sendName() {
    stompClient.publish({
        destination: "/send/message",
        body: JSON.stringify({'name': $("#name").val(), 'statement': $("#statement").val()})
    });
}

//メッセージ表示
function showMessage(message) {
    $("#message").append( "<tr><td>" + message.name + ": " + message.statement + "</td></tr>");
}

/*********************
 * ファイルアップロード
 *********************/
//動画ファイルの要素取得　　　　★後で実装する*********************************
//const mv_fileInput = document.getElementById('mv__fileData');
//const mv_files = img_fileInput.files;
//************************************************************* 


//選択ファイル確認
function handleFileSelect() {
	const sizeLimit = 1024 * 1024 * 1;　// 制限サイズ（1MB）
	const file = document.getElementById('img_fileData');//画像ファイルの要素取得
	const selectfiles = file.files;//ファイル一覧を取得

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

//アップロード
/**function fileUpload() {
	// 要素規定の動作をキャンセルする
        event.preventDefault();
        //const sizeLimit = 1024 * 1024 * 1;　// 制限サイズ（1MB）
		const inputfile = document.querySelector("#img_fileData");//画像ファイルの要素取得
		
		// フォームデータを作成
		const file = inputfile.files[0];
		const formData = new FormData();
		// imgというフィールド名でファイルを追加
		formData.append("img", file);
		// 送信
		fetch("http://localhost:8080/user/index", { method: "POST", body: formData })
			.then((res)=>{
			        return( res.json() );
			      })
			      .then((json)=>{
			        // 通信が成功した際の処理
			      })
			      .catch((error)=>{
			        // エラー処理
			      });
}**/

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