package db_tool.domain.type;

import lombok.Getter;

public enum DatabaseMergeType implements DefaultType<Integer> {
	CREATE_DELETE(1, "作成&削除"),
	CREATE_UPDATE_DELETE(2, "作成&更新&削除");
	
	@Getter
	private Integer id;
	
	@Getter
	private String name;
	
	DatabaseMergeType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static DatabaseMergeType of(Integer id) {
		for(DatabaseMergeType databaseMergeType : DatabaseMergeType.values()) {
			if (databaseMergeType.getId().equals(id)) {
				return databaseMergeType;
			}
		}
		return null;
	}

	@Override
	public Integer getValue() {
		return this.id;
	}
	
	
}
