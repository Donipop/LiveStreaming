var ws = null;

function connect(){
    ws = new WebSocket("ws://" + location.host + "/ws/chat");
    wsEvent();
}

function wsEvent() {
    ws.onopen = function (data){
        //소켓이 열리면 동작
        console.log("소켓열림");
        wsSend("안녕하세요 도니입니다");
    }
}
function  wsSend(msg){
    ws.send(msg);
}
