<@layout.box>
<@layout.boxDetail title="A5M2のER図取り込み">

<form class="upload" action="upload" method="POST" enctype="multipart/form-data">
<input type="hidden" name="projectId" value="${projectId}" />
<div class="card-body">
  <div class="form-group">
    <label for="a5m2er_file">A5M2のERファイル</label>
    <div class="input-group">
      <div class="custom-file">
      <input type="file" id="a5m2er_file" class="a5m2er" name="a5m2er" accept=".a5er" />
      <label class="custom-file-label" for="a5m2er_file">A5M2のERファイル</label>
      </div>
    </div>
  </div>
  
  <div class="card-footer">
    <button type="button" class="btn btn-primary js-upload">アップロード</button>
  </div>
</div>
</form>

<form class="analyze" action="mergeConfirm" method="GET" style="display:none;">
  <input id="fileName" type="hidden" name="fileName" />
  <input type="hidden" name="projectId" value="${projectId}" />
  <input type="hidden" name="mergeType" value="1" />
  <button type="submit" class="btn btn-primary">A5M2のER図の取り込み確認</button>
</form>

<script>
$('.js-upload').on('click', function() {
	if ($('input[name="a5m2er"]').prop('files').length == 0) {
		alert('ファイルを添付してください');
		return;
	}
	var formData = new FormData();
	formData.append('file', $('input[name="a5m2er"]').prop('files')[0]);
	$.ajax({
		url: '/database/a5m2_er/upload',
		type: 'post',
		data: formData,
		cache: false,
		processData: false,
		contentType: false
	}).done(function(data) {
		if (data.status) {
			alert('アップロードされました');
			$('.upload').hide();
			$('.analyze').show();
			$('#fileName').val(data.fileName);
		} else {
			alert(data.error);
		}
	});
});
</script>

</@layout.boxDetail>
</@layout.box>