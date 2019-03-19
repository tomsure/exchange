function upload(uploadForm,uploadUrl,fn) {

	var formData = new FormData(uploadForm[0]);
	$.ajax({
		url:uploadUrl,
		type: 'POST',
		data: formData,
		async: false,
		processData: false,
		contentType: false,
		success: function(data) {
			uploadForm.parent().parent().find('div:nth-child(3)').find('img').attr('src', data)
			uploadForm.find('input[type="file"]').attr('data-upload', 'true')
		}
	});
}

  