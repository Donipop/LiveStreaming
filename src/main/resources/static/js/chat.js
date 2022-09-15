let ws = null;

function connect(){
    ws = new WebSocket("ws://" + location.host + "/ws/chat");
    wsEvent();
    wsGet();
}

function wsEvent() {
    ws.onopen = function (data){
        //소켓이 열리면 동작
        console.log("소켓열림");
       // wsSend("안녕하세요 도니입니다");
    }
}

function wsSend(message,UserIn){
    let msg = {
        type: "message",
        text: message.trim(),
        id: UserIn.user_id,
        nick: UserIn.user_nick
    };

    ws.send(JSON.stringify(msg));
    //console.log(msg);
    addChat(msg.text.replace(/(?:\r\n|\r|\n)/g,"<br>"));
}

function wsGet(){
    ws.onmessage = function (message){
        let msg = JSON.parse(message.data);
        let text = "";
        let id = "";
        let nick = "";
        //console.log(msg);

        switch (msg.type){
            case "message":
                text = msg.text;
                nick = msg.nick;
                id = msg.id;
                break;
        }
        //console.log(text);
    }
}

function addChat(value){
    const obj = document.getElementById("chat-ul");
    let clone_chat = $('#messager li').clone();

    let me = `<div class="messager right">Messager(Messager123)</div>` +
        `<span class="list-unstyled right">${value}</span>`;
    clone_chat.children().html(me);

    clone_chat.appendTo(obj);
    //console.log(value);
    reti();
    $("textarea").val('');
}