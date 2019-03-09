/*
 * © 2019 Gtf Software, Inc. All Rights Reserved
 * Mail fxd@hotmail.co.jp
 * More information please visit https://gtf.jp
 */
package jp.gtf.tutorials.batch.tutorial0001;

import javax.sql.DataSource;
import jp.gtf.tutorials.batch.tutorial0001.flow.Tutorial0001Flow;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tutorial0001Flowのテストクラス
 *
 * @author F
 */
@SpringBatchTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Tutorial0001Flow.class)
public class Tutorial0001FlowTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Before
    public void clearDB() throws Exception {
        jdbcTemplate.getJdbcTemplate().update("delete from STUDENT");
    }

    @Test
    public void testJob() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }

}
