package db_tool.application.view.database.a5m2er.analyze;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;

import db_tool.domain.model.Column;
import db_tool.domain.model.Table;

public class Entity implements A5M2ErItem{

	public String PName;
	
	public String LName;
	
	public String Comment;
	
	public String TableOption;
	
	public String Page;
	
	public String Left;
	
	public String Top;
	
	public Field field;
	
	public List<Field> fields = new ArrayList<>();
	
	public Index Index;
	
	public List<Index> indexes = new ArrayList<>();
	
	public String EffectMode;
	
	public String Color;
	
	public String BkColor;
	
	public String ModifiedDateTime;
	
	public String Position;
	
	public void add(String s) {
		if (s.startsWith("PName")) {
			this.PName = s.replaceAll("PName=", "");
		} else if (s.startsWith("LName")) {
			this.LName = s.replaceAll("LName=", "");
		} else if (s.startsWith("Comment")) {
			this.Comment = s.replaceAll("Comment=", "");
		} else if (s.startsWith("TableOption")) {
			this.TableOption = s.replaceAll("TableOption=", "");
		} else if (s.startsWith("Page")) {
			this.Page = s.replaceAll("Page=", "");
		} else if (s.startsWith("Left")) {
			this.Left = s.replaceAll("Left=", "");
		} else if (s.startsWith("Top")) {
			this.Top = s.replaceAll("Top=", "");
		} else if (s.startsWith("Field")) {
			this.field = new Field();
			this.field.addField(s.replaceAll("Field=", ""));
			this.fields.add(this.field);
		} else if (s.startsWith("Index")) {
			this.Index = new Index();
			this.Index.add(s.replaceAll("Index=", ""));
			this.indexes.add(this.Index);
		} else if (s.startsWith("EffectMode")) {
			this.EffectMode = s.replaceAll("EffectMode=", "");
		} else if (s.startsWith("Color")) {
			this.Color = s.replaceAll("Color=", "");
		} else if (s.startsWith("BkColor")) {
			this.BkColor = s.replaceAll("BkColor=", "");
		} else if (s.startsWith("ModifiedDateTime")) {
			this.ModifiedDateTime = s.replaceAll("ModifiedDateTime=", "");
		} else if (s.startsWith("Position")) {
			this.Position = s.replaceAll("Position=", "");
		} 
	}
	
	public String output() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Entity]\n");
		sb.append("PName=" + this.PName + "\n");
		sb.append("LName=" + this.LName + "\n");
		sb.append("Comment=" + this.Comment + "\n");
		sb.append("TableOption=" + (this.TableOption != null ? this.TableOption : "") + "\n");
		if (this.Page != null) {
			sb.append("Page=" + this.Page + "\n");
		}
		if (this.Left != null) {
			sb.append("Left=" + this.Left + "\n");
		}
		if (this.Top != null) {
			sb.append("Top=" + this.Top + "\n");
		}
		for(Field field : this.fields) {
			sb.append("Field=" + field.output());
		}
		for(Index index : this.indexes) {
			sb.append("Index=" + index.Index + "\n");
		}
		if (this.EffectMode != null) {
			sb.append("EffectMode=" + this.EffectMode + "\n");
		}
		if (this.Color != null) {
			sb.append("Color=" + this.Color + "\n");
		}
		if (this.BkColor != null) {
			sb.append("BkColor=" + this.BkColor + "\n");
		}
		if (this.ModifiedDateTime != null) {
			sb.append("ModifiedDateTime=" + this.ModifiedDateTime + "\n");
		}
		if (this.Position != null) {
			sb.append("Position=" + this.Position + "\n");
		}
		String s = sb.toString();
		sb.setLength(0);
		return s;
	}
	
	public void createAndDelete(Table table) {
		if (Strings.isEmpty(this.PName) && Strings.isNotEmpty(table.physicalTableName)) {
			this.PName = table.physicalTableName;
		}
		if (Strings.isEmpty(this.LName) && Strings.isNotEmpty(table.logicalTableName)) {
			this.LName = table.logicalTableName;
		}
		if (Strings.isEmpty(this.Comment) && Strings.isNotEmpty(table.tableComment)) {
			this.Comment = table.tableComment;
		}
	}
	
	public void createAndDelete(List<Column> columns) {
		Map<String, Field> newFieldMap = new LinkedHashMap<>();
		Map<String, Field> fieldMap = this.fields.stream()
				.collect(Collectors.toMap(e -> e.columnName, e -> e, (e1, e2) -> e1, LinkedHashMap::new));
		Map<String, Column> columnMap = columns.stream()
				.collect(Collectors.toMap(e -> e.physicalColumnName, e -> e, (e1, e2) -> e1, LinkedHashMap::new));
		
		for(Entry<String, Column> e : columnMap.entrySet()) {
			Integer primaryKey = 0;
			Field field = fieldMap.get(e.getKey());
			if (field != null) {
				field.createAndDelete(e.getValue(), primaryKey);
			} else {
				field = new Field();
				field.createAndDeleteAndUpdate(e.getValue(), primaryKey);
			}
			newFieldMap.put(e.getKey(), field);
			if (e.getValue().columnKey.equals(Column.PRIMARY_KEY)) {
				primaryKey++;
			}
		}
		this.fields = new ArrayList<>(newFieldMap.values());
	}
	
	public void createAndDeleteAndUpdate(Table table) {
		this.PName = table.physicalTableName;
		this.LName = table.logicalTableName;
		this.Comment = table.tableComment;
	}
	
	public void createAndDeleteAndUpdate(List<Column> columns) {
		Map<String, Field> newFieldMap = new LinkedHashMap<>();
		Map<String, Field> fieldMap = this.fields.stream()
				.collect(Collectors.toMap(e -> e.Field, e -> e, (e1, e2) -> e1, LinkedHashMap::new));
		Map<String, Column> columnMap = columns.stream()
				.collect(Collectors.toMap(e -> e.physicalColumnName, e -> e, (e1, e2) -> e1, LinkedHashMap::new));
		
		for(Entry<String, Column> e : columnMap.entrySet()) {
			Integer primaryKey = 0;
			Field field = fieldMap.get(e.getKey());
			if (field != null) {
				field.createAndDeleteAndUpdate(e.getValue(), primaryKey);
			} else {
				field = new Field();
				field.createAndDeleteAndUpdate(e.getValue(), primaryKey);
			}
			newFieldMap.put(e.getKey(), field);
			if (e.getValue().columnKey.equals(Column.PRIMARY_KEY)) {
				primaryKey++;
			}
		}
		this.fields = new ArrayList<>(newFieldMap.values());
	}

	@Override
	public String getKey() {
		return "Entity_" + this.PName;
	}
}
