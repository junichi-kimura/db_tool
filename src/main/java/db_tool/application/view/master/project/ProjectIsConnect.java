package db_tool.application.view.master.project;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectIsConnect {

	@JsonProperty("status")
	public Boolean status;
	
	@JsonProperty("error")
	public String error;
	
	@JsonProperty("connect")
	public Boolean connect;
}
