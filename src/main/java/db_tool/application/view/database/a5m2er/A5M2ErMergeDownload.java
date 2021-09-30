package db_tool.application.view.database.a5m2er;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import db_tool.domain.type.DatabaseMergeType;
import lombok.Data;

@Data
public class A5M2ErMergeDownload {

	@NotNull
	public Long projectId;
	
	@NotBlank
	public String fileName;
	
	@NotNull
	public Integer mergeType;
	
	public DatabaseMergeType getEnumMergeType() {
		return DatabaseMergeType.of(this.mergeType);
	}
}
