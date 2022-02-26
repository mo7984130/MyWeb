script1 = document.createElement("script");
script1.type = "text/javascript";
script1.src = "/web/js/jquery-3.5.1.min.js";
document.head.appendChild(script1);
linkNode1 = document.createElement("link");
linkNode1.setAttribute("rel", "stylesheet");
linkNode1.setAttribute("type", "text/css");
linkNode1.setAttribute("href", "/web/css/all_computer.css");
document.head.appendChild(linkNode1);
linkNode2 = document.createElement("link");
linkNode2.setAttribute("rel", "stylesheet");
linkNode2.setAttribute("type", "text/css");
linkNode2.setAttribute("href", "/web/css/bootstrap.min.css");
document.head.appendChild(linkNode2);
script2 = document.createElement("script");
script2.type = "text/javascript";
script2.src = "/web/js/bootstrap.min.js";
document.head.appendChild(script2);

function format(string , strings) {
    for (let i = 0 ; i < strings.length ; i++) {
        string = string.replace("%s",strings[i]);
    }
    return string;
}

function checkLogin(){
    let userId = null;
    let userName = null;
    let password = null;
    let cookies = document.cookie;
    cookies = cookies.split(";");

    for (let i = 0; i < cookies.length; i++){
        cookies[i] = cookies[i].split("=");

        while (cookies[i][0].charAt(0) ===' ') {
            cookies[i][0] = cookies[i][0].substring(1,cookies[i][0].length);
        }

        if (cookies[i][0] === "userName"){
            userName = cookies[i][1];
        }else if (cookies[i][0] === "password"){
            password = cookies[i][1];
        }else if (cookies[i][0] === "userId"){
            userId = cookies[i][1];
        }

    }

    let action = "/web/CheckLogin?userId=%s&userName=%s&password=%s";
    action = format(action , [userId , userName , password])

    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("get", action , true);
    xmlHttp.send();
    xmlHttp.onreadystatechange = checkResult;

    function checkResult(){
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            debugger
            if (xmlHttp.responseText.indexOf("0") !== -1){

                alert("您还未登录，请先登陆!")
                location.assign("/web/html/login/login.jsp");

            }
        }
    }

}

checkLogin();

function upload(id , action , type){
    let files = document.getElementById(id);

    if (files.files.length === 0){
        alert("请选择文件!");
        return;
    }

    action = "/web/" + action;

    let formData = new FormData();
    for (let i = 0 ; i<files.files.length ; i++) {

        if (type==="image"){
            let suffix = "bmp dib rle emf gif jpg jpeg jpe jif pcx dcx pic png tga tif tiff xif wmf jfif";
            let suffixes = suffix.split(" ");
            let name = files.files[i].name;
            let isCheckSuccess = false;
            for (let i2 = 0 ;i2<suffixes.length;i2++){
                if (name.indexOf(suffixes[i2]) !== -1){
                    isCheckSuccess = true;
                }
            }
            if (!isCheckSuccess){
                alert("请选择正确的文件格式!")
                return;
            }
        }

        formData.append("file",files.files[i]);

    }

    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("post", action , true);
    xmlHttp.send(formData);
    xmlHttp.onreadystatechange = checkFunction;

    function checkFunction(){
        if(xmlHttp.readyState===4 && xmlHttp.status===200){
            if (xmlHttp.responseText.indexOf("1") !== -1){
                alert("上传成功!");
            }else if (xmlHttp.responseText.indexOf("0") !== -1){
                alert("上传失败，文件已存在!");
            }
        }
    }

}