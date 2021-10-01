package db_tool.application.repository;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.miragesql.miragesql.test.ExecutedSQLInfo;
import com.miragesql.miragesql.test.MirageTestContext;

import db_tool.application.repository.ProjectRepository;
import db_tool.domain.model.Project;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProjectRepositoryTest extends BaseRepositoryTest<Project, ProjectRepository>{

	@Test
	public void findByIdTest() {
		repository.findById(1L);
		ExecutedSQLInfo sqlInfo = MirageTestContext.getExecutedSQLInfo(0);
		Assert.assertEquals(sqlInfo.getSql(), "SELECT * FROM projects WHERE id = ?");
		Assert.assertEquals(sqlInfo.getParams()[0], 1L);
	}
}
