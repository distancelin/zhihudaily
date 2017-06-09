
var DEFAULT_IMAGE_URI = "file:///android_asset/default_pic_content_image_loading_light.png";
var DEFAULT_LOADING_IMAGE_URI = "file:///android_asset/default_pic_content_image_download_light.png";
var DEFAULT_IMAGE_URI_DARK = "file:///android_asset/default_pic_content_image_loading_dark.png";
var DEFAULT_LOADING_IMAGE_URI_DARK = "file:///android_asset/default_pic_content_image_download_dark.png";
function onImageClick(pImage) { // 图片点击事件
    console.log("点击了图片");
	if (pImage.src == DEFAULT_LOADING_IMAGE_URI) { // 点击图片开始加载
	    pImage.src=DEFAULT_IMAGE_URI;// 图片加载中
		BihuDaily.clickToLoadImage(pImage.getAttribute("zhimg-src"));
	}else if(pImage.src == DEFAULT_LOADING_IMAGE_URI_DARK){
	    pImage.src=DEFAULT_IMAGE_URI_DARK;// 图片加载中
    	BihuDaily.clickToLoadImage(pImage.getAttribute("zhimg-src"));
	}else { // 打开查看图片界面
	console.log("准备查看图片");
		BihuDaily.openImage(pImage.getAttribute("zhimg-src"));
	}
}