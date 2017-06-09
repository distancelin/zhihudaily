var DEFAULT_IMAGE_URI = "file:///android_asset/default_pic_content_image_loading_light.png";
function onLoaded() { // 加载图片，调用BihuDaily的loadImage方法
console.log("js调用了onloaded");
	var allImage = document.querySelectorAll("img");
	allImage = Array.prototype.slice.call(allImage, 0);
	allImage.forEach(function(image) {
		if (image.src == DEFAULT_IMAGE_URI) {
		    console.log("js调用了onloaded");
			BihuDaily.loadImage(image.getAttribute("zhimg-src"));
			console.log("js调用了onloaded，并准备调用安卓的loadimage");
		}
	});
}