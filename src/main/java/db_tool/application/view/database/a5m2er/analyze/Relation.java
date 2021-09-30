package db_tool.application.view.database.a5m2er.analyze;

import java.util.ArrayList;
import java.util.List;

public class Relation implements A5M2ErItem{

	public String Entity1;
	
	public String Entity2;
	
	public String RelationType1;
	
	public String RelationType2;
	
	public String Fields1;
	
	public String Fields2;
	
	public String Cardinarity1;
	
	public String Cardinarity2;
	
	public List<String> Positions = new ArrayList<>();
	
	public String Dependence;
	public String Caption;
	public String PName;
	public String ModifiedDateTime;
	public String LineMode;
	public String Bar1;
	public String Bar2;
	public String Bar3;
	public String TermPos1;
	public String TermPos2;
	
	public void add(String s) {
		if (s.startsWith("Entity1")) {
			this.Entity1 = s.replaceAll("Entity1=", "");
		} else if (s.startsWith("Entity2")) {
			this.Entity2 = s.replaceAll("Entity2=", "");
		} else if (s.startsWith("RelationType1")) {
			this.RelationType1 = s.replaceAll("RelationType1=", "");
		} else if (s.startsWith("RelationType2")) {
			this.RelationType2 = s.replaceAll("RelationType2=", "");
		} else if (s.startsWith("Fields1")) {
			this.Fields1 = s.replaceAll("Fields1=", "");
		} else if (s.startsWith("Fields2")) {
			this.Fields2 = s.replaceAll("Fields2=", "");
		} else if (s.startsWith("Cardinarity1")) {
			this.Cardinarity1 = s.replaceAll("Cardinarity1=", "");
		} else if (s.startsWith("Cardinarity2")) {
			this.Cardinarity2 = s.replaceAll("Cardinarity2=", "");
		} else if (s.startsWith("Position")) {
			this.Positions.add(s.replaceAll("Position=", ""));
		} else if (s.startsWith("Dependence")) {
			this.Dependence = s.replaceAll("Dependence=", "");
		} else if (s.startsWith("Caption")) {
			this.Caption = s.replaceAll("Caption=", "");
		} else if (s.startsWith("PName")) {
			this.PName = s.replaceAll("PName=", "");
		} else if (s.startsWith("ModifiedDateTime")) {
			this.ModifiedDateTime = s.replaceAll("ModifiedDateTime=", "");
		} else if (s.startsWith("LineMode")) {
			this.LineMode = s.replaceAll("LineMode=", "");
		} else if (s.startsWith("Bar1")) {
			this.Bar1 = s.replaceAll("Bar1=", "");
		} else if (s.startsWith("Bar2")) {
			this.Bar2 = s.replaceAll("Bar2=", "");
		} else if (s.startsWith("Bar3")) {
			this.Bar3 = s.replaceAll("Bar3=", "");
		} else if (s.startsWith("TermPos1")) {
			this.TermPos1 = s.replaceAll("TermPos1=", "");
		} else if (s.startsWith("TermPos2")) {
			this.TermPos2 = s.replaceAll("TermPos2=", "");
		}
	}
	
	public String output() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Relation]\n");
		sb.append("Entity1=" + this.Entity1 + "\n");
		sb.append("Entity2=" + this.Entity2 + "\n");
		sb.append("RelationType1=" + this.RelationType1 + "\n");
		sb.append("RelationType2=" + this.RelationType2 + "\n");
		sb.append("Fields1=" + this.Fields1 + "\n");
		sb.append("Fields2=" + this.Fields2 + "\n");
		sb.append("Cardinarity1=" + this.Cardinarity1 + "\n");
		sb.append("Cardinarity2=" + this.Cardinarity2 + "\n");
		for(String position : this.Positions) {
			sb.append("Position=" + position + "\n");
		}
		sb.append("Dependence=" + this.Dependence + "\n");
		sb.append("Caption=" + this.Caption + "\n");
		sb.append("PName=" + this.PName + "\n");
		if (this.ModifiedDateTime != null) {
			sb.append("ModifiedDateTime=" + this.ModifiedDateTime + "\n");
		}
		sb.append("LineMode=" + this.LineMode + "\n");
		sb.append("Bar1=" + this.Bar1 + "\n");
		sb.append("Bar2=" + this.Bar2 + "\n");
		sb.append("Bar3=" + this.Bar3 + "\n");
		sb.append("TermPos1=" + this.TermPos1 + "\n");
		sb.append("TermPos2=" + this.TermPos2 + "\n");
		return sb.toString();
	}

	@Override
	public String getKey() {
		return "Relation_" + this.Entity1 + "_" + this.Entity2;
	}
}
