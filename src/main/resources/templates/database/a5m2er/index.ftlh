<@layout.default title="A5M2のER図">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

    <!-- Main content -->
    <section class="content">
      <div class="content-header">
        <div class="container-fluid">
          <div class="row mb-2">
            <div class="col-sm-6">
              <h1 class="m-0">A5M2のER図</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
              <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item active">DB</li>
                <li class="breadcrumb-item active">A5M2のER図</li>
              </ol>
            </div><!-- /.col -->
          </div><!-- /.row -->
        </div><!-- /.container-fluid -->
      </div>
      <div class="container-fluid">
        <@layout.search title="テーブル情報">
          <form action="/database/a5m2_er/" method="GET">
          <div class="card-body">
            <div class="form-group">
            <label for="project_name">プロジェクト名</label>
            <select name="projectId" class="form-control">
            　　<option value="">---</option>
            <#list searchInfo.projects as p>
              <option value="${p.id}" <#if searchInfo.projectId?? && p.id == searchInfo.projectId>selected="selected"</#if>>${p.projectName}</option>
            </#list>
            </select>
            </div>
            <div class="card-footer">
              <button type="submit" class="btn btn-primary">検索</button>
            </div>
          </div>
          </form>
        </@layout.search>
        
        <#if searchInfo.projectId??>
          <#if isInvolved>
            <a href="/database/a5m2_er/preInvolve?projectId=${searchInfo.projectId}" class="btn btn-primary pull-left iframe" data-cbox-width="90%" data-cbox-height="90%">A5M2のER図の取り込み確認</a>
          <#else>
            <a href="/database/a5m2_er/preInvolve?projectId=${searchInfo.projectId}" class="btn btn-primary pull-left iframe" data-cbox-width="90%" data-cbox-height="90%">A5M2のER図の取り込み確認</a>
            <div class="alert alert-warning alert-dismissible">
              データベースがまだ取り込まれていません
            </div>
          </#if>
        </#if>
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
</@layout.default>