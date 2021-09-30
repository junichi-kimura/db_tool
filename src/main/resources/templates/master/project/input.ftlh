<@layout.box>
<@layout.boxDetail title="プロジェクト入力">
<form action="register" method="POST">
<#if form.id??>
<input type="hidden" name="id" value="${form.id}" />
<@u.showErrors path="form.id" />
</#if>
<div class="card-body">
  <div class="form-group">
    <label for="project_name">プロジェクト名</label>
    <@u.formInput path="form.projectName" fieldType="text" class="form-control" id="project_name" name="projectName" value=form.projectName />
  </div>
  <div class="form-group">
    <label for="database_type">データベース種類</label>
    <@u.enumSingleSelect path="form.databaseType" enumName="DatabaseType" name="databaseType" selectedValue=form.databaseType id="database_type" />
  </div>
  <div class="form-group">
    <label for="project_host_name">ホスト名</label>
    <@u.formInput path="form.databaseHostName" fieldType="text" class="form-control" id="project_host_name" name="databaseHostName" value=form.databaseHostName />
  </div>
  <div class="form-group">
    <label for="database_name">データベース名</label>
    <@u.formInput path="form.databaseName" fieldType="text" class="form-control" id="database_name" name="databaseName" value=form.databaseName />
  </div>
  <div class="form-group">
    <label for="database_schema">データベーススキーマ</label>
    <@u.formInput path="form.databaseSchema" fieldType="text" class="form-control" id="database_schema" name="databaseSchema" value=form.databaseSchema />
  </div>
  <div class="form-group">
    <label for="database_user">ユーザー</label>
    <@u.formInput path="form.databaseUser" fieldType="text" class="form-control" id="database_user" name="databaseUser" value=form.databaseUser />
  </div>
  <div class="form-group">
    <label for="database_pass">パスワード</label>
    <@u.formInput path="form.databasePass" fieldType="text" class="form-control" id="database_pass" name="databasePass" value=form.databasePass />
  </div>
  <div class="form-group">
    <label for="database_port">ポート</label>
    <@u.formInput path="form.databasePort" fieldType="text" class="form-control" id="database_port" name="databasePort" value=form.databasePort />
  </div>
  <div class="card-footer">
    <button type="submit" class="btn btn-primary">登録</button>
    <button type="button" class="btn btn-outline-info js-is-connect">DB接続確認</button>
  </div>
</div>
</form>

<script>
$('.js-is-connect').on('click', function() {
	$.ajax({
		url: '/master/project/is_connect',
		type: 'POST',
		data: {
			'databaseType': $('select[name="databaseType"]').val(),
			'databaseHostName': $('input[name="databaseHostName"]').val(),
			'databaseName': $('input[name="databaseName"]').val(),
			'databaseUser': $('input[name="databaseUser"]').val(),
			'databasePass': $('input[name="databasePass"]').val(),
			'databasePort': $('input[name="databasePort"]').val()
		},
		dataType: 'json'
	}).done(function(data){
		if (data.status) {
			if (data.connect) {
				alert('接続できました');
			} else {
				alert('接続できませんでした');
			}
		} else {
			alert(data.error);
		}
	});
});
</script>
</@layout.boxDetail>
</@layout.box>