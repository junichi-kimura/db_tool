<@layout.box>
<@layout.boxDetail title="コード">

<div class="card">
<div class="card-body">
<div class="card-header">${mirageSqlEntity.fileName}</div>
<textarea id="codeMirror" style="display:none;">
${mirageSqlEntity.tableAnnotation}
class ${mirageSqlEntity.className} {
${mirageSqlEntity.columns}
}
</textarea>

<div class="CodeMirror cm-s-monokai"></div>
</div>
</div>
</div>

<script>
CodeMirror.fromTextArea(document.getElementById("codeMirror"), {
	mode: "text/x-java",
	indentWithTabs: true,
	smartIndent: true,
	lineNumbers: true,
	lineWrapping: true,
	matchBrackets: true,
	autofocus: true,
	theme: "ambiance",
}).setSize(null, 400);
</script>

</@layout.boxDetail>
</@layout.box>