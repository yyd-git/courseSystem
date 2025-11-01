-- ======================================
-- data.sql - 初始化测试数据
-- ======================================

-- --- 课程 ---
INSERT INTO course (code, title, instructor, schedule, capacity) VALUES
                                                                     ('C001', '计算机网络基础', '张老师', '周一 8:00-10:00', 50),
                                                                     ('C002', '数据库系统原理', '李教授', '周三 10:00-12:00', 40),
                                                                     ('C003', '人工智能导论', '王博士', '周五 14:00-16:00', 35);

-- --- 学生 ---
INSERT INTO student (student_id, name, major, grade, email) VALUES
                                                                ('S1001', '刘畅', '计算机科学与技术', 2022, 'liuchang@example.com'),
                                                                ('S1002', '陈可', '软件工程', 2023, 'chenke@example.com'),
                                                                ('S1003', '孙琪', '信息安全', 2021, 'sunqi@example.com');

-- --- 选课 ---
INSERT INTO enrollment (student_id, course_id, enrolled_at) VALUES
                                                                (1, 1, CURRENT_TIMESTAMP),
                                                                (1, 2, CURRENT_TIMESTAMP),
                                                                (2, 1, CURRENT_TIMESTAMP),
                                                                (3, 3, CURRENT_TIMESTAMP);
