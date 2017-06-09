/**
 *  每张图片加载完成时调用此方法
 *  pOldUrl:网络地址        pNewUrl:图片下载完成保存在本地地址
 *
 */
function onImageLoadingComplete(pOldUrl, pNewUrl) {
    console.log("pOldUrl:"+pOldUrl);
    console.log("pNewUrl:"+pNewUrl);
	var allImage = document.querySelectorAll("img");
	allImage = Array.prototype.slice.call(allImage, 0);
	allImage.forEach(function(image) {
		if (image.getAttribute("zhimg-src") == pOldUrl || image.getAttribute("zhimg-src") == decodeURIComponent(pOldUrl)) {
			image.src = pNewUrl;
		}
	});
}