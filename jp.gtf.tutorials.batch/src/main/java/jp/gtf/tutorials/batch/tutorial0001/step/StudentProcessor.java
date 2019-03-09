/*
 * © 2019 Gtf Software, Inc. All Rights Reserved
 * Mail fxd@hotmail.co.jp
 * More information please visit https://gtf.jp
 */
package jp.gtf.tutorials.batch.tutorial0001.step;

import org.springframework.batch.item.ItemProcessor;

/**
 * Student処理プロセス<br>
 * 学生IDを採番して、返却する。
 *
 * @author F
 */
public class StudentProcessor implements ItemProcessor<Student, Student> {

    private int studentId = 0;

    @Override
    public Student process(Student i) throws Exception {
        i.setStudentId(String.format("%05d", studentId++));
        return i;
    }

}
