<@layout.box>
<@layout.boxDetail title="A5M2のER図取り込み確認 カラム差分">

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
      <th>物理カラム名</th>
      <td><#if confirmColumn.a5PhysicalColumnName??>${confirmColumn.a5PhysicalColumnName}</#if></td>
      <td><#if confirmColumn.dbPhysicalColumnName??>${confirmColumn.dbPhysicalColumnName}</#if></td>
    </tr>
    <tr>
      <th>論理カラム名</th>
      <td><#if confirmColumn.a5LogicalColumnName??>${confirmColumn.a5LogicalColumnName}</#if></td>
      <td><#if confirmColumn.dbLogicalColumnName??>${confirmColumn.dbLogicalColumnName}</#if></td>
    </tr>
    <tr>
      <th>型</th>
      <td><#if confirmColumn.a5ColumnType??>${confirmColumn.a5ColumnType}</#if></td>
      <td><#if confirmColumn.dbColumnType??>${confirmColumn.dbColumnType}</#if></td>
    </tr>
    <tr>
      <th>IS NOT NULL</th>
      <td><#if confirmColumn.a5IsNotNull??>${confirmColumn.a5IsNotNull}</#if></td>
      <td><#if confirmColumn.dbIsNotNull??>${confirmColumn.dbIsNotNull}</#if></td>
    </tr>
    <tr>
      <th>プライマリキー</th>
      <td><#if confirmColumn.a5PrimaryKey??>${confirmColumn.a5PrimaryKey}</#if></td>
      <td><#if confirmColumn.dbPrimaryKey??>${confirmColumn.dbPrimaryKey}</#if></td>
    </tr>
    <tr>
      <th>デフォルト値</th>
      <td><#if confirmColumn.a5ColumnDefault??>${confirmColumn.a5ColumnDefault}</#if></td>
      <td><#if confirmColumn.dbColumnDefault??>${confirmColumn.dbColumnDefault}</#if></td>
    </tr>
    <tr>
      <th>カラムコメント</th>
      <td><#if confirmColumn.a5ColumnComment??>${confirmColumn.a5ColumnComment}</#if></td>
      <td><#if confirmColumn.dbColumnDefault??>${confirmColumn.dbColumnComment}</#if></td>
    </tr>
    </tbody>
  </table>
  </div>
</section> 
</@layout.boxDetail>
</@layout.box>