-- ======================================
-- schema.sql - 课程系统数据库结构
-- ======================================

DROP TABLE IF EXISTS enrollment;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS course;

-- ------------------------
-- 课程表
-- ------------------------
CREATE TABLE course (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        code VARCHAR(50) NOT NULL UNIQUE,
                        title VARCHAR(255),
                        instructor VARCHAR(100),
                        schedule VARCHAR(100),
                        capacity INT NOT NULL
);

-- ------------------------
-- 学生表
-- ------------------------
CREATE TABLE student (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         student_id VARCHAR(50) NOT NULL UNIQUE,
                         name VARCHAR(100) NOT NULL,
                         major VARCHAR(100) NOT NULL,
                         grade INT NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ------------------------
-- 选课表
-- ------------------------
CREATE TABLE enrollment (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            student_id BIGINT NOT NULL,
                            course_id BIGINT NOT NULL,
                            enrolled_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            UNIQUE (student_id, course_id),
                            FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
                            FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
);
