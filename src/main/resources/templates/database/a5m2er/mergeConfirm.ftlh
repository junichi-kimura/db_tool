<@layout.box>
<@layout.boxDetail title="A5M2のER図取り込み確認">

<section class="content">
  <div class="container-fluid">
  <@layout.search title="ER図ダウンロード">
  <form action="mergeDownload" method="GET">
  <input type="hidden" name="projectId" value="${form.projectId}" />
  <input type="hidden" name="fileName" value="${form.fileName}" />
  <div class="card-body">
    <div class="form-group">
    <label for="project_name">DBからの取り込み方法${downloadForm.mergeType}</label>
    <@u.enumSingleSelect path="downloadForm.mergeType" enumName="DatabaseMergeType" name="mergeType" selectedValue=downloadForm.mergeType id="database_merge_type" />
    <div class="card-footer">
      <button type="submit" class="btn btn-primary">A5M2のER図をダウンロードする</button>
    </div>
  </div>
  </form>
  </@layout.search>
  <#list confirmOutput.confirmTables as confirmTable>
  <table class="table">
    <thead class="thead-dark">
      <th></th>
      <th>A5M2</th>
      <th><- DB</th>
    </thead>
    <tbody>
    <tr class="iframe <#if !confirmTable.equal>table-warning</#if>" data-cbox-href="/database/a5m2_er/diffTable?projectId=${form.projectId}&fileName=${form.fileName}&tableName=<#if confirmTable.a5PhysicalTableName??>${confirmTable.a5PhysicalTableName}<#else>${confirmTable.dbPhysicalTableName}</#if>" data-cbox-width="50%">
      <th class="w-25">テーブル名 / 論理名</th>
      <td class="w-50"><#if confirmTable.a5PhysicalTableName??>${confirmTable.a5PhysicalTableName}</#if> / <#if confirmTable.a5LogicalTableName??>${confirmTable.a5LogicalTableName}</#if></td>
      <td class="w-50"><#if confirmTable.dbPhysicalTableName??>${confirmTable.dbPhysicalTableName}</#if> / <#if confirmTable.dbLogicalTableName??>${confirmTable.dbLogicalTableName}</#if></td>
    </tr>
    <#list confirmTable.confirmColumns as confirmColumn>
    <tr class="iframe <#if !confirmColumn.equal>table-warning</#if>" data-cbox-href="/database/a5m2_er/diffColumn?projectId=${form.projectId}&fileName=${form.fileName}&tableName=<#if confirmTable.a5PhysicalTableName??>${confirmTable.a5PhysicalTableName}<#else>${confirmTable.dbPhysicalTableName}</#if>&columnName=<#if confirmColumn.a5PhysicalColumnName??>${confirmColumn.a5PhysicalColumnName}<#else>${confirmColumn.dbPhysicalColumnName}</#if>" data-cbox-width="50%">
      <th class="w-25">カラム名 / 論理名</th>
      <td class="w-50"><#if confirmColumn.a5PhysicalColumnName??>${confirmColumn.a5PhysicalColumnName}</#if> / <#if confirmColumn.a5LogicalColumnName??>${confirmColumn.a5LogicalColumnName}</#if></td>
      <td class="w-50"><#if confirmColumn.dbPhysicalColumnName??>${confirmColumn.dbPhysicalColumnName}</#if> / <#if confirmColumn.dbLogicalColumnName??>${confirmColumn.dbLogicalColumnName}</#if></td>
    </tr>
    </#list>
    </tbody>
  </table>
  </#list>
  
  </div>
</section>  
</@layout.boxDetail>
</@layout.box>