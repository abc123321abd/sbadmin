Waves.init();
Waves.attach('.float-button-light', ['waves-button', 'waves-float', 'waves-light']);

function changeIframe(title,url,ele) {

    var ifr=$("#page-content");
    ifr.attr('src',url);


    if(url!=""){
        //去掉所有的active
        var oldEle=$(".active-sub");
        oldEle.find(".active-link").removeClass("active-link");
        oldEle.removeClass("active-sub");


        $(ele.parentElement.parentElement.parentElement).addClass("active-sub")
        $(ele.parentElement).addClass("active-link");

    }

}

function resizeIframe(){
    var curHeight = $("#mainnav").height() - 48;
    var ifr=$("#page-content");
    ifr.height(curHeight);
    console.log("resize iframe...");
}
$(function(){

    window.onresize=function(){

        resizeIframe();

    }

    resizeIframe();



});
