package db_tool.application.view.database.a5m2er.analyze;

public class Shape implements A5M2ErItem{

	public String Page;
	
	public String Left;
	
	public String Top;
	
	public String Width;
	
	public String Height;
	
	public String LineColor;
	
	public String LineWidth;
	
	public String BrushColor;
	
	public String BrushAlpha;
	
	public String ShapeType;
	
	public String Text;
	
	public String FontName;
	
	public String FontSize;
	
	public String FontBold;
	
	public String FontColor;
	
	public String TextAlign;
	
	public String ImageFileName;
	
	public void add(String s) {
		if (s.startsWith("Page")) {
			this.Page = s.replaceAll("Page=", "");
		} else if (s.startsWith("Left")) {
			this.Left = s.replaceAll("Left=", "");
		} else if (s.startsWith("Top")) {
			this.Top = s.replaceAll("Top=", "");
		} else if (s.startsWith("Width")) {
			this.Width = s.replaceAll("Width=", "");
		} else if (s.startsWith("Height")) {
			this.Height = s.replaceAll("Height=", "");
		} else if (s.startsWith("LineColor")) {
			this.LineColor = s.replaceAll("LineColor=", "");
		} else if (s.startsWith("LineWidth")) {
			this.LineWidth = s.replaceAll("LineWidth=", "");
		} else if (s.startsWith("BrushColor")) {
			this.BrushColor = s.replaceAll("BrushColor=", "");
		} else if (s.startsWith("BrushAlpha")) {
			this.BrushAlpha = s.replaceAll("BrushAlpha=", "");
		} else if (s.startsWith("ShapeType")) {
			this.ShapeType = s.replaceAll("ShapeType=", "");
		} else if (s.startsWith("Text=")) {
			this.Text = s.replaceAll("Text=", "");
		} else if (s.startsWith("FontName")) {
			this.FontName = s.replaceAll("FontName=", "");
		} else if (s.startsWith("FontSize")) {
			this.FontSize = s.replaceAll("FontSize=", "");
		} else if (s.startsWith("FontBold")) {
			this.FontBold = s.replaceAll("FontBold=", "");
		} else if (s.startsWith("FontColor")) {
			this.FontColor = s.replaceAll("FontColor=", "");
		} else if (s.startsWith("TextAlign")) {
			this.TextAlign = s.replaceAll("TextAlign=", "");
		} else if (s.startsWith("ImageFileName")) {
			this.ImageFileName = s.replaceAll("ImageFileName=", "");
		}
	}
	
	public String output() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Shape]\n");
		sb.append("Page=" + this.Page + "\n");
		sb.append("Left=" + this.Left + "\n");
		sb.append("Top=" + this.Top + "\n");
		sb.append("Width=" + this.Width + "\n");
		sb.append("Height=" + this.Height + "\n");
		sb.append("LineColor=" + this.LineColor + "\n");
		if (this.LineWidth != null) {
			sb.append("LineWidth=" + this.LineWidth + "\n");
		}
		if (this.BrushColor != null) {
			sb.append("BrushColor=" + this.BrushColor + "\n");
		}
		if (this.BrushAlpha != null) {
			sb.append("BrushAlpha=" + this.BrushAlpha + "\n");
		}
		sb.append("ShapeType=" + this.ShapeType + "\n");
		sb.append("Text=" + this.Text + "\n");
		sb.append("FontName=" + this.FontName + "\n");
		sb.append("FontSize=" + this.FontSize + "\n");
		if (this.FontBold != null) {
			sb.append("FontBold=" + this.FontBold + "\n");
		}
		sb.append("FontColor=" + this.FontColor + "\n");
		sb.append("TextAlign=" + this.TextAlign + "\n");
		sb.append("ImageFileName=" + this.ImageFileName + "\n");
		return sb.toString();
	}

	@Override
	public String getKey() {
		return "Shape_" 
				+ this.Page 
				+ "_" + this.Left 
				+ "_" + this.Top 
				+ "_" + this.Width
				+ "_" + this.Height;
	}
}
