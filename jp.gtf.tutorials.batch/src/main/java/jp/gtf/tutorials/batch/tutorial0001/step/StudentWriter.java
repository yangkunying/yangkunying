/*
 * © 2019 Gtf Software, Inc. All Rights Reserved
 * Mail fxd@hotmail.co.jp
 * More information please visit https://gtf.jp
 */
package jp.gtf.tutorials.batch.tutorial0001.step;

import javax.sql.DataSource;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;

/**
 * 学生データをDBに書き込む
 *
 * @author F
 */
public class StudentWriter {

    private final DataSource dataSource;

    /**
     * Default Construct Method
     *
     * @param dataSource Datasource
     */
    public StudentWriter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Create DB Writer<br>
     *
     * @return Writer
     */
    public JdbcBatchItemWriter<Student> createWriter() {
        JdbcBatchItemWriterBuilder<Student> writerBuilder = new JdbcBatchItemWriterBuilder<Student>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO STUDENT (STUDENT_ID, STUDENT_NAME) VALUES (:studentId, :studentName)")
                .dataSource(dataSource);
        JdbcBatchItemWriter<Student> writer = writerBuilder.build();
        writer.afterPropertiesSet();
        return writer;
    }
}
