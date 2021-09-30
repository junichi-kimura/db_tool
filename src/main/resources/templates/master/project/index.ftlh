<@layout.default title="プロジェクト">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

    <!-- Main content -->
    <section class="content">
      <div class="content-header">
        <div class="container-fluid">
          <div class="row mb-2">
            <div class="col-sm-6">
              <h1 class="m-0">プロジェクト</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
              <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item active">マスタ</li>
                <li class="breadcrumb-item active">プロジェクト</li>
              </ol>
            </div><!-- /.col -->
          </div><!-- /.row -->
        </div><!-- /.container-fluid -->
      </div>
      <div class="container-fluid">
        <a href="/master/project/input" class="btn btn-primary pull-left iframe" data-cbox-width="80%">新規登録</a>
        <table class="table table-striped">
          <thead class="thead-dark">
            <th scope="col">id</th>
            <th scope="col">プロジェクト名</th>
          </thead>
          <tbody>
          <#list projects as project>
          <tr class="iframe" data-cbox-href="/master/project/input?id=${project.id}" data-cbox-width="80%">
            <th scope="row">${project.id}</th>
            <td>${project.projectName}</td>
          </tr>
          </#list>
          </tbody>
        </table>
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
</@layout.default>