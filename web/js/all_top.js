
//不同端加载不同的css
const sUserAgent = navigator.userAgent.toLowerCase();
const bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
const bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
const bIsMidp = sUserAgent.match(/midp/i) == "midp";
const bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
const bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
const bIsAndroid = sUserAgent.match(/android/i) == "android";
const bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
const bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
let linkNode;
if (!(bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM)) {
    //当是电脑端的时候加载pc 的css
    linkNode = document.createElement("link");
    linkNode.setAttribute("rel", "stylesheet");
    linkNode.setAttribute("type", "text/css");
    linkNode.setAttribute("href", "css/all_computer.css");
    document.head.appendChild(linkNode);
} else {
    //当是手机端的时候加载手机端的css
    var oMeta = document.createElement('meta');
    oMeta.name = 'viewport';
    oMeta.content = 'width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no';
    document.getElementsByTagName('head')[0].appendChild(oMeta);
    linkNode = document.createElement("link");
    linkNode.setAttribute("rel", "stylesheet");
    linkNode.setAttribute("type", "text/css");
    linkNode.setAttribute("href", "css/all_mobile.css");
    document.head.appendChild(linkNode);
}
