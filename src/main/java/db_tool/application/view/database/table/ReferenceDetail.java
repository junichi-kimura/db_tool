package db_tool.application.view.database.table;

import com.miragesql.miragesql.annotation.Column;
import com.miragesql.miragesql.annotation.Transient;

import db_tool.domain.type.ReferentialType;
import lombok.Data;

@Data
public class ReferenceDetail {

	@Column(name="from_table_id")
	public Integer fromTableId;
	
	@Column(name="from_table_name")
	public String fromTableName;
	
	@Column(name="to_table_id")
	public Integer toTableId;
	
	@Column(name="to_table_Name")
	public String toTableName;
	
	@Column(name="referential_type")
	public Integer referentialType;
	
	@Column(name="referential_column_id")
	public Integer referentialColumnId;
	
	@Column(name="from_column_id")
	public Integer fromColumnId;
	
	@Column(name="from_column_name")
	public String fromColumnName;
	
	@Column(name="to_column_id")
	public Integer toColumnId;
	
	@Column(name="to_column_name")
	public String toColumnName;
	
	@Transient
	public ReferentialType getEnumReferentialType() {
		return ReferentialType.of(this.referentialType);
	}
}
