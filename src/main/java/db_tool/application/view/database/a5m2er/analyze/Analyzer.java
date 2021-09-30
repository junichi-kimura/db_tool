package db_tool.application.view.database.a5m2er.analyze;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

import db_tool.domain.model.Column;
import db_tool.domain.model.Table;
import db_tool.domain.type.DatabaseMergeType;

public class Analyzer {

	public static void main(String...args) {
		String path = System.getProperty("user.home") + File.separator + ".db_tool" + File.separator + "BPR_jyukunavi_ER_20191018.a5er";
		File f = new File(path);
		Analyzer analyzer = new Analyzer(f);
		analyzer.analyze();
	}
	
	private File file;
	public Analyzer(File file) {
		this.file = file;
	}
	
	public void analyze() {
		try(Scanner scanner = new Scanner(this.file)) {
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.startsWith("#") || line.isEmpty()) {
					// コメントもしくは空行によりスキップ
					continue;
				}
				this.add(line);
			}
			this.EOL();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public A5M2ERItemType itemType;
	public Manager manager;
	public Entity entity;
	public List<Entity> entities = new ArrayList<>();
	public Relation relation;
	public List<Relation> relations = new ArrayList<>();
	public Shape shape;
	public List<Shape> shapes = new ArrayList<>();
	
	public Map<String, A5M2ErItem> erItemMap = new LinkedHashMap<>(); 
	
	public void add(String line) {
		if (line.startsWith("[")) {
			this.itemType = A5M2ERItemType.of(line);
			if (this.entity != null) {
				this.entities.add(this.entity);
				this.erItemMap.put(this.entity.getKey(), this.entity);
				this.entity = null;
			}
			if (this.relation != null) {
				this.relations.add(this.relation);
				this.erItemMap.put(this.relation.getKey(), this.relation);
				this.relation = null;
			}
			if (this.shape != null) {
				this.shapes.add(this.shape);
				this.erItemMap.put(this.shape.getKey(), this.shape);
				this.shape = null;
			}
			if (this.itemType == A5M2ERItemType.Manager && this.manager == null) {
				this.manager = new Manager();
			}
			if (this.itemType == A5M2ERItemType.Entity && this.entity == null) {
				this.entity = new Entity();
			}
			if (this.itemType == A5M2ERItemType.Relation && this.relation == null) {
				this.relation = new Relation();
			}
			if (this.itemType == A5M2ERItemType.Shape && this.shape == null) {
				this.shape = new Shape();
			}
			return;
		}
		if (this.itemType == A5M2ERItemType.Manager) {
			this.manager.add(line);
			return;
		}
		
		if (this.itemType == A5M2ERItemType.Entity) {
			this.entity.add(line);
			return;
		}
		
		if (this.itemType == A5M2ERItemType.Relation) {
			this.relation.add(line);
			return;
		}
		
		if (this.itemType == A5M2ERItemType.Shape) {
			this.shape.add(line);
			return;
		}
	}
	
	public void EOL() {
		if (this.entity != null) {
			this.entities.add(this.entity);
			this.erItemMap.put(this.entity.getKey(), this.entity);
			this.entity = null;
		}
		if (this.relation != null) {
			this.relations.add(this.relation);
			this.erItemMap.put(this.relation.getKey(), this.relation);
			this.relation = null;
		}
		if (this.shape != null) {
			this.shapes.add(this.shape);
			this.erItemMap.put(this.shape.getKey(), this.shape);
			this.shape = null;
		}
	}
	
	public void mergeTables(List<Table> tables, DatabaseMergeType mergeType) {
		Map<String, Entity> entityMap = new HashMap<>();
		for(Entry<String, A5M2ErItem> erItem : this.erItemMap.entrySet()) {
			if (erItem.getKey().startsWith("Entity_")) {
				Entity entity = (Entity) erItem.getValue();
				entityMap.put(erItem.getKey(), entity);
			}
		}
		Map<String, Table> tableMap = tables.stream()
				.collect(Collectors.toMap(e -> e.physicalTableName, e -> e));
		for(Entry<String, Table> e : tableMap.entrySet()) {
			Entity entity = entityMap.get("Entity_" + e.getKey());
			if (entity != null) {
				switch(mergeType) {
				case CREATE_DELETE:
					entity.createAndDelete(e.getValue());
					break;
				case CREATE_UPDATE_DELETE:
					entity.createAndDeleteAndUpdate(e.getValue());
					break;
				}
			} else {
				entity = new Entity();
				entity.createAndDeleteAndUpdate(e.getValue());
			}
			this.erItemMap.put(entity.getKey(), entity);
		}
	}
	
	public void mergeColumns(String tableName, List<Column> columns, DatabaseMergeType mergeType) {
		Entity entity = null;
		for(Entry<String, A5M2ErItem> e : this.erItemMap.entrySet()) {
			if (e.getKey().equals("Entity_" + tableName)) {
				entity = (Entity) e.getValue();
			}
		}
		if (entity == null) {
			throw new IllegalArgumentException("存在しないテーブルです");
		}
		switch(mergeType) {
		case CREATE_DELETE:
			entity.createAndDelete(columns);
			break;
		case CREATE_UPDATE_DELETE:
			entity.createAndDeleteAndUpdate(columns);
			break;
		}
		this.erItemMap.put(entity.getKey(), entity);
	}
	
	@SuppressWarnings("resource")
	public File output(File file) {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(
	                new BufferedWriter(
	                new OutputStreamWriter(
	                new FileOutputStream
	                  (file),"Shift-JIS")));
			printWriter.print(this.manager.output() + "\n");
			for(Entry<String, A5M2ErItem> e : this.erItemMap.entrySet()) {
				printWriter.print(e.getValue().output() + "\n");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}
		return file;
	}
	
	public static enum A5M2ERItemType {
		Manager("[Manager]"),
		Entity("[Entity]"),
		Relation("[Relation]"),
		Shape("[Shape]"),;
		
		private String item;
		
		A5M2ERItemType(String item) {
			this.item = item;
		}
		
		public static A5M2ERItemType of(String s) {
			for(A5M2ERItemType itemType : A5M2ERItemType.values()) {
				if (itemType.item.equals(s)) {
					return itemType;
				}
			}
			return null;
		}
	}
}
