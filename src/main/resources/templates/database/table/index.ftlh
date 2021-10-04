<@layout.default title="テーブル情報">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

    <!-- Main content -->
    <section class="content">
      <div class="content-header">
        <div class="container-fluid">
          <div class="row mb-2">
            <div class="col-sm-6">
              <h1 class="m-0">テーブル情報</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
              <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item active">DB</li>
                <li class="breadcrumb-item active">テーブル情報</li>
              </ol>
            </div><!-- /.col -->
          </div><!-- /.row -->
        </div><!-- /.container-fluid -->
      </div>
      <div class="container-fluid">
        <@layout.search title="テーブル情報">
          <form action="/database/table/" method="GET">
          <div class="card-body">
            <div class="form-group">
            <label for="project_name">プロジェクト名</label>
            <@u.formSingleSelect path="searchInfo.projectId" options=searchInfo.projects optionValue="projectName" id="project_id" class="form-control" name="projectId" selectedValue=searchInfo.projectId />
            </div>
            <div class="form-group">
            <label for="project_name">テーブル名</label>
            <@u.formInput path="searchInfo.tableName" fieldType="text" class="form-control" id="table_name" name="tableName" value=searchInfo.tableName />
            </div>
            <div class="form-group">
            <label for="project_name">カラム名</label>
            <@u.formInput path="searchInfo.columnName" fieldType="text" class="form-control" id="column_name" name="columnName" value=searchInfo.columnName />
            </div>
            <div class="card-footer">
              <button type="submit" class="btn btn-primary">検索</button>
            </div>
          </div>
          </form>
        </@layout.search>
        
        <#if searchInfo.projectId??>
        <a href="/database/table/preInvolve?projectId=${searchInfo.projectId}" class="btn btn-primary pull-left iframe" data-cbox-width="80%">取り込み確認</a>
        <table class="table table-striped">
          <thead class="thead-dark">
            <th scope="col">テーブル物理名</th>
            <th scope="col">コメント</th>
          </thead>
          <tbody>
          <#if tables??>
          <#list tables as table>
          <tr class="iframe" data-cbox-href="/database/table/detail?table_id=${table.id}" data-cbox-width="90%" data-cbox-height="90%">
            <td>${table.physicalTableName}</td>
            <td>${table.tableComment}</td>
          </tr>
          </#list>
          </#if>
          </tbody>
        </table>
        </#if>
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
</@layout.default>