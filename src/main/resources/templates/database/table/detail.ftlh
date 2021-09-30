<@layout.box>
<@layout.boxDetail title="テーブル詳細">

<div class="card">
<div class="card-body">
<div class="card-header">テーブル情報</div>
<table class="table table-striped">
  <tbody>
  <tr>
    <th class="w-25">物理テーブル名</th>
    <td class="w-75">${table.physicalTableName}</td>
  </tr>
  <tr>
    <th class="w-25">論理テーブル名</th>
    <td class="w-75">${table.logicalTableName}</td>
  </tr>
  <tr>
    <th class="w-25">テーブルコメント</th>
    <td class="w-75"><#if table.tableComment??>${table.tableComment}</#if></td>
  </tr>
  <tr>
    <th class="w-25">メモ</th>
    <td class="w-75"><#if table.note??>${table.note}</#if></td>
  </tr>
  <tr>
    <th class="w-25">MirageSQL用コード生成</th>
    <td class="w-75"><a class="iframe" data-cbox-href="/database/table/mirageSql?tableId=${table.id}" data-cbox-width="80%">コード生成</a></td>
  </tr>
  </tbody>
</table>
<div class="card-header">カラム情報</div>
<table class="table table-striped">
  <thead>
  <tr>
    <th>物理カラム名</th>
    <th>論理カラム名</th>
    <th>型(最大長)</th>
    <th>NOT NULL</th>
    <th>デフォルト値</th>
    <th>プライマリキー</th>
    <th>extra</th>
    <th>メモ</th>
  </tr>
  </thead>
  <tbody>
  <#list columns as column>
  <tr>
    <td>${column.physicalColumnName}</td>
    <td>${column.logicalColumnName}</td>
    <td>${column.columnType}</td>
    <td><#if !column.isNullable>〇</#if></td>
    <td><#if column.columnDefault??>${column.columnDefault}</#if></td>
    <td><#if column.isPrimaryKey()>〇</#if></td>
    <td><#if column.extra??>${column.extra}</#if></td>
    <td><#if column.note??>${column.note}</#if></td>
  </tr>
  </#list>
  </tbody>
</table>
</div>
</div>

<div class="card">
<div class="card-body">
  <div class="card-header">インデックスキー</div>
  <div class="card-body p-0">
  <table class="table table-striped">
    <thead>
    <tr>
      <th>インデックス名</th>
      <th>ユニーク</th>
      <th>カラム名</th>
      <th>メモ</th>
    </tr>
    </thead>
    <tbody>
    <#list indexes as index>
    <tr>
      <td>${index.indexName}</td>
      <td><#if index.isUnique>〇</#if></td>
      <td>${index.indexColumnName}</td>
      <td><#if index.note??>${index.note}</#if></td>
    </tr>
    </#list>
    </tbody>
  </table>
  </div>
</div>
</div>

<div class="card">
<div class="card-body">
  <div class="card-header">参照外部キー</div>
  <div class="card-body p-0">
  <table class="table table-striped">
    <thead>
    <tr>
      <th>外部キー種類</th>
      <th>fromテーブル</th>
      <th>toテーブル</th>
      <th>fromカラム</th>
      <th>toカラム</th>
    </tr>
    </thead>
    <tbody>
    <#list referenceDetails as referenceDetail>
    <tr>
      <td>${referenceDetail.enumReferentialType.getName()}</td>
      <td><#if referenceDetail.fromTableName??>${referenceDetail.fromTableName}</#if></td>
      <td><#if referenceDetail.toTableName??><a href="/database/table/detail?table_id=${referenceDetail.toTableId}">${referenceDetail.toTableName}</a></#if></td>
      <td><#if referenceDetail.fromColumnName??>${referenceDetail.fromColumnName}</#if></td>
      <td><#if referenceDetail.toColumnName??>${referenceDetail.toColumnName}</#if></td>
    </tr>
    </#list>
    </tbody>
  </table>
  </div>
</div>
</div>

<div class="card">
<div class="card-body">
  <div class="card-header">被参照外部キー</div>
  <div class="card-body p-0">
  <table class="table table-striped">
    <thead>
    <tr>
      <th>外部キー種類</th>
      <th>fromテーブル</th>
      <th>toテーブル</th>
      <th>fromカラム</th>
      <th>toカラム</th>
    </tr>
    </thead>
    <tbody>
    <#list referencedDetails as referencedDetail>
    <tr>
      <td>${referencedDetail.enumReferentialType.getName()}</td>
      <td><#if referencedDetail.fromTableName??><a href="/database/table/detail?table_id=${referencedDetail.fromTableId}">${referencedDetail.fromTableName}</a></#if></td>
      <td><#if referencedDetail.toTableName??>${referencedDetail.toTableName}</#if></td>
      <td><#if referencedDetail.fromColumnName??>${referencedDetail.fromColumnName}</#if></td>
      <td><#if referencedDetail.toColumnName??>${referencedDetail.toColumnName}</#if></td>
    </tr>
    </#list>
    </tbody>
  </table>
  </div>
</div>
</div>

</@layout.boxDetail>
</@layout.box>