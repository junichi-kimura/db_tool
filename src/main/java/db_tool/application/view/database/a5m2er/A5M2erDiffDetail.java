package db_tool.application.view.database.a5m2er;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class A5M2erDiffDetail {

	@NotNull
	public Long projectId;
	
	@NotBlank
	public String fileName;
	
	@NotBlank
	public String tableName;
	
	public String columnName;
}
