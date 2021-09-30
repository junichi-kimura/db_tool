package db_tool.domain.type;

import lombok.Getter;

public enum ReferentialType implements DefaultType<Integer>{
	PHYSICAL(1, "物理外部キー"),
	LOGICAL(1, "論理外部キー");
	
	@Getter
	private Integer id;
	
	@Getter
	private String name;
	
	ReferentialType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public Integer getValue() {
		return this.id;
	}

	public static ReferentialType of(Integer id) {
		for(ReferentialType referentialType : ReferentialType.values()) {
			if (referentialType.getId().equals(id)) {
				return referentialType;
			}
		}
		return null;
	}
}
