let ws = null;

function connect(){
    ws = new WebSocket("ws://" + location.host + "/ws/chat");
    let channelID = location.href.split('/chat/')[1];
    wsEvent(channelID);
    wsGet();
}

function wsEvent(channelID) {
    ws.onopen = function (data){
        //소켓이 열리면 동작
        console.log("소켓열림");
        let msg = {
            type: "join",
            id: User.user_id,
            nick: User.user_nick,
            channelID: channelID
        };
        ws.send(JSON.stringify(msg));
    }
}

function wsSend(message,UserIn,channelID){
    let msg = {
        type: "message",
        text: message.trim(),
        id: UserIn.user_id,
        nick: UserIn.user_nick,
        channelID: channelID
    };
    ws.send(JSON.stringify(msg));
    //addChat(msg.text.replace(/(?:\r\n|\r|\n)/g,"<br>"));
}

function wsGet(){
    ws.onmessage = function (message){
        let msg = JSON.parse(message.data);
        let text = "";
        let id = "";
        let nick = "";
        switch (msg.type){
            case "message":
                text = msg.text;
                nick = msg.nick;
                id = msg.id;
                break;
        }
        let messageValue = {
            text: text,
            id: id,
            nick: nick
        }
        if(User.user_id == id){
            console.log("message실행")
            addChat(messageValue,"messager");
        }else if(User.user_id != id){
            console.log("sender실행")
            addChat(messageValue, "sender");
        }
    }
}

function addChat(value,type){
    const obj = document.getElementById("chat-ul");
    let clone_chat = $('#messager li').clone();

    let me = '';
        switch(type){
            case "messager":
                me =  `<div class="user">
                            <div class="messager right">${value.nick}(${value.id})</div>
                            <span class="list-unstyled right">${value.text}</span>
                            <div class="pop_user">
                            </div>
                        </div>`;
                $("textarea").val('');
                break;
            case "sender":
                me = `<div class="user">
                            <div class="sender left">${value.nick}(${value.id})</div>
                            <span class="list-unstyled left">${value.text}</span>
                            <div class="pop_user">
                            </div>
                        </div>`;
                break;
            case "bangjang":
                me = `<div class="user">
                            <div class="sender left bj">${value.nick}(${value.id})</div>
                            <span class="list-unstyled left">${value.text}</span>
                            <div class="pop_user">
                            </div>
                        </div>`
                break;
        }
    clone_chat.children().html(me);
    clone_chat.appendTo(obj);
    getmessage();
}

function test1(number){
    let channelID = location.href.split('/chat/')[1];
    let msg = {}
    switch (number){
        case 1:
            msg = {
                type: 'userlist',
                channelID: channelID,
            }
            ws.send(JSON.stringify(msg));
            break;
        case 2:
            msg ={
                type: 'channelList',
            }
            ws.send(JSON.stringify(msg));
            break;
    }
}
