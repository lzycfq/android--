function ShowDiv(show_div, bg_div) {
	document.getElementById(show_div).style.display = 'block';

	var bgdiv = document.getElementById(bg_div);

	// bgdiv.style.height = $(document).height();
	$("#" + bg_div).height($(document).height());
};
//关闭弹出层加用,号可以连续写弹窗和关闭
function CloseDiv(show_div, bg_div) {
	document.getElementById(show_div).style.display = 'none';

};

