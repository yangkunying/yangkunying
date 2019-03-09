/*
 * © 2019 Gtf Software, Inc. All Rights Reserved
 * Mail fxd@hotmail.co.jp
 * More information please visit https://gtf.jp
 */
package jp.gtf.tutorials.batch.tutorial0001.flow;

import javax.sql.DataSource;
import jp.gtf.tutorials.batch.config.BatchConfiguration;
import jp.gtf.tutorials.batch.config.DatabaseConfiguration;
import jp.gtf.tutorials.batch.tutorial0001.step.Student;
import jp.gtf.tutorials.batch.tutorial0001.step.StudentProcessor;
import jp.gtf.tutorials.batch.tutorial0001.step.StudentReader;
import jp.gtf.tutorials.batch.tutorial0001.step.StudentWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * バッチフロー
 *
 * @author F
 */
@Configuration
@Import(value = {BatchConfiguration.class, DatabaseConfiguration.class})
public class Tutorial0001Flow {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;

    @Bean
    public Job importDBFromCSV() {
        return jobBuilderFactory.get("Tutorial0001_IMPORT_CSV_TO_DB")
                .flow(step01())
                .end()
                .build();
    }

    @Bean
    public Step step01() {
        return stepBuilderFactory.get("step1")
                .<Student, String>chunk(10)
                .reader(new StudentReader().createReader())
                .processor(new StudentProcessor())
                .writer(new StudentWriter(dataSource).createWriter())
                .build();
    }
}
