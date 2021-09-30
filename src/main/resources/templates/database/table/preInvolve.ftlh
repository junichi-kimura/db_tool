<@layout.box>
<@layout.boxDetail title="テーブル取り込み">

<div class="card-body">
<table class="table table-striped">
  <thead class="thead-dark">
    <th scope="col">テーブル名</th>
    <th scope="col">コメント</th>
  </thead>
  <tbody>
  <#list tableInvolves as tableInvolve>
  <tr>
    <td>${tableInvolve.tableName}</td>
    <td>${tableInvolve.tableComment}</td>
  </tr>
  </#list>
  </tbody>
</table>
</div>

<#if tableInvolves??>
<a href="/database/table/involve?projectId=${projectId}" class="btn btn-primary pull-left">取り込み</a>
</#if>

</@layout.boxDetail>
</@layout.box>