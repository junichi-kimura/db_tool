<@layout.box>
<@layout.boxDetail title="A5M2のER図取り込み確認 テーブル差分">

<section class="content">
  <div class="container-fluid">
  <table class="table">
    <thead class="thead-dark">
      <th class="w-20"></th>
      <th class="w-40">A5M2</th>
      <th class="w-40">DB</th>
    </thead>
    <tbody>
    <tr>
      <th>物理テーブル名</th>
      <td><#if confirmTable.a5PhysicalTableName??>${confirmTable.a5PhysicalTableName}</#if></td>
      <td><#if confirmTable.dbPhysicalTableName??>${confirmTable.dbPhysicalTableName}</#if></td>
    </tr>
    <tr>
      <th>論理テーブル名</th>
      <td><#if confirmTable.a5LogicalTableName??>${confirmTable.a5LogicalTableName}</#if></td>
      <td><#if confirmTable.dbLogicalTableName??>${confirmTable.dbLogicalTableName}</#if></td>
    </tr>
    <tr>
      <th>テーブルコメント</th>
      <td><#if confirmTable.a5Comment??>${confirmTable.a5Comment}</#if></td>
      <td><#if confirmTable.dbComment??>${confirmTable.dbComment}</#if></td>
    </tr>
    </tbody>
  </table>
  </div>
</section> 
</@layout.boxDetail>
</@layout.box>