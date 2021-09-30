package db_tool.application.view.database.table;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.CaseFormat;
import com.miragesql.miragesql.naming.Inflection;

import db_tool.domain.model.Column;
import db_tool.domain.model.Table;
import db_tool.domain.type.ColumnType;

public class MirageSqlEntity {

	private Table table;
	
	private List<Column> columns;
	
	public MirageSqlEntity(Table table, List<Column> columns) {
		this.table = table;
		this.columns = columns;
	}
	
	public String getFileName() {
		return this.getClassName() + ".java";
	}
	
	public String getTableAnnotation() {
		StringBuilder sb = new StringBuilder();
		sb.append("@Data\n");
		sb.append("@Table(name=\"" + table.physicalTableName + "\")");
		return sb.toString();
	}
	
	public String getClassName() {
		String className = Inflection.singularize(this.table.getPhysicalTableName());
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, className);
	}
	
	public String getColumns() {
		StringBuilder sb = new StringBuilder();
		for(Column c : this.columns) {
			if (StringUtils.equals(c.columnKey, Column.PRIMARY_KEY)) {
				sb.append("\t@PrimaryKey(generationType=GenerationType.IDENTITY)\n");
			}
			sb.append("\t@Column(name=\"" + c.physicalColumnName + "\")\n");
			String javaObject = getJavaObject(c);
			String uppperCamelColumnName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, c.physicalColumnName);
			sb.append("\tpublic " + javaObject + " " + uppperCamelColumnName + ";\n");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public String getJavaObject(Column c) {
		String columnName = c.columnType;
		ColumnType columnType = ColumnType.of(columnName);
		if (columnType != null) {
			return columnType.getJavaObject();
		}
		return columnName;
	}
}
