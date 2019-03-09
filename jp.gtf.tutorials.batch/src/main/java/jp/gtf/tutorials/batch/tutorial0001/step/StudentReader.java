/*
 * © 2019 Gtf Software, Inc. All Rights Reserved
 * Mail fxd@hotmail.co.jp
 * More information please visit https://gtf.jp
 */
package jp.gtf.tutorials.batch.tutorial0001.step;

import java.io.File;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.core.io.FileSystemResource;

/**
 * 学生読み取る用
 *
 * @author F
 */
public class StudentReader extends FlatFileItemReader<Student> {

    /**
     * Construct Method
     */
    public StudentReader() {
    }

    /**
     * アイテム読み取るクラスのインスタンスを作成<br>
     * CSVファイルからデータを取り込む、Studentクラスを返却
     *
     * @return StudentReader
     */
    public FlatFileItemReader createReader() {
        return new FlatFileItemReaderBuilder()
                .name("personItemReader")
                .resource(new FileSystemResource(new File("data/student.csv"))).encoding("UTF-8")
                .delimited()
                .names(new String[]{"studentName"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Student>() {
                    {
                        setTargetType(Student.class);
                    }
                })
                .build();
    }
}
