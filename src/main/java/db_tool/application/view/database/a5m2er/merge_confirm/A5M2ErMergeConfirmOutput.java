package db_tool.application.view.database.a5m2er.merge_confirm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db_tool.domain.model.Column;
import db_tool.application.view.database.a5m2er.analyze.Entity;
import db_tool.application.view.database.a5m2er.analyze.Field;
import db_tool.application.view.database.a5m2er.analyze.TableColumns;

public class A5M2ErMergeConfirmOutput {

	private Map<String, ConfirmTable> confirmTableMap = new HashMap<>();
	
	public List<ConfirmTable> getConfirmTables() {
		return new ArrayList<ConfirmTable>(confirmTableMap.values());
	}
	
	public void addA5(Entity entity) {
		ConfirmTable confirmTable;
		if (confirmTableMap.containsKey(entity.PName)) {
			confirmTable = confirmTableMap.get(entity.PName);
		} else {
			confirmTable = new ConfirmTable();
		}
		confirmTable.setA5(entity);
		for(Field field : entity.fields) {
			confirmTable.addA5Column(field);
		}
		confirmTableMap.put(entity.PName, confirmTable);
	}
	
	public void addDb(TableColumns tableColumns) {
		ConfirmTable confirmTable;
		if (confirmTableMap.containsKey(tableColumns.physicalTableName)) {
			confirmTable = confirmTableMap.get(tableColumns.physicalTableName);
		} else {
			confirmTable = new ConfirmTable();
		}
		confirmTable.setDb(tableColumns);
		for(Column column : tableColumns.columns) {
			Integer primaryKey = 0;
			confirmTable.addDbColumn(column, primaryKey);
			if (column.columnKey.contentEquals(Column.PRIMARY_KEY)) {
				primaryKey++;
			}
		}
		confirmTableMap.put(tableColumns.physicalTableName, confirmTable);
	}
}
