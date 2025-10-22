---
title: OpenAPI definition
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.30"

---

# OpenAPI definition

Base URLs:

# Authentication

# course-controller

<a id="opIdgetCourseById"></a>

## GET getCourseById

GET /api/courses/{id}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|path|string| 是 |none|

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

<a id="opIdupdateCourse"></a>

## PUT updateCourse

PUT /api/courses/{id}

> Body 请求参数

```json
{
  "id": "string",
  "code": "string",
  "title": "string",
  "instructor": {
    "id": "string",
    "name": "string",
    "email": "string"
  },
  "schedule": {
    "dayOfWeek": "string",
    "startTime": "string",
    "endTime": "string",
    "expectedAttendance": 0
  },
  "capacity": 0,
  "enrolled": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|path|string| 是 |none|
|body|body|[Course](#schemacourse)| 是 |none|

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

<a id="opIddeleteCourse"></a>

## DELETE deleteCourse

DELETE /api/courses/{id}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|path|string| 是 |none|

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

<a id="opIdgetAllCourses"></a>

## GET getAllCourses

GET /api/courses

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

<a id="opIdcreateCourse"></a>

## POST createCourse

POST /api/courses

> Body 请求参数

```json
{
  "id": "string",
  "code": "string",
  "title": "string",
  "instructor": {
    "id": "string",
    "name": "string",
    "email": "string"
  },
  "schedule": {
    "dayOfWeek": "string",
    "startTime": "string",
    "endTime": "string",
    "expectedAttendance": 0
  },
  "capacity": 0,
  "enrolled": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[Course](#schemacourse)| 是 |none|

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

# student-controller

<a id="opIdgetStudentById"></a>

## GET getStudentById

GET /api/students/{id}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|path|string| 是 |none|

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

<a id="opIdupdateStudent"></a>

## PUT updateStudent

PUT /api/students/{id}

> Body 请求参数

```json
{
  "id": "string",
  "studentId": "string",
  "name": "string",
  "major": "string",
  "grade": 0,
  "email": "string",
  "createdAt": "2019-08-24T14:15:22Z"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|path|string| 是 |none|
|body|body|[Student](#schemastudent)| 是 |none|

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

<a id="opIddeleteStudent"></a>

## DELETE deleteStudent

DELETE /api/students/{id}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|path|string| 是 |none|

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

<a id="opIdgetAllStudents"></a>

## GET getAllStudents

GET /api/students

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

<a id="opIdcreateStudent"></a>

## POST createStudent

POST /api/students

> Body 请求参数

```json
{
  "id": "string",
  "studentId": "string",
  "name": "string",
  "major": "string",
  "grade": 0,
  "email": "string",
  "createdAt": "2019-08-24T14:15:22Z"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[Student](#schemastudent)| 是 |none|

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

# enrollment-controller

<a id="opIdgetAllEnrollments"></a>

## GET getAllEnrollments

GET /api/enrollments

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

<a id="opIdenrollStudent"></a>

## POST enrollStudent

POST /api/enrollments

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|studentId|query|string| 是 |none|
|courseId|query|string| 是 |none|

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

<a id="opIdgetByStudent"></a>

## GET getByStudent

GET /api/enrollments/student/{studentId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|studentId|path|string| 是 |none|

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

<a id="opIdgetByCourse"></a>

## GET getByCourse

GET /api/enrollments/course/{courseId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|courseId|path|string| 是 |none|

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

<a id="opIddeleteEnrollment"></a>

## DELETE deleteEnrollment

DELETE /api/enrollments/{id}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|path|string| 是 |none|

> 返回示例

> 200 Response

```
{"property1":{},"property2":{}}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» **additionalProperties**|object|false|none||none|

# 数据模型

<h2 id="tocS_Course">Course</h2>

<a id="schemacourse"></a>
<a id="schema_Course"></a>
<a id="tocScourse"></a>
<a id="tocscourse"></a>

```json
{
  "id": "string",
  "code": "string",
  "title": "string",
  "instructor": {
    "id": "string",
    "name": "string",
    "email": "string"
  },
  "schedule": {
    "dayOfWeek": "string",
    "startTime": "string",
    "endTime": "string",
    "expectedAttendance": 0
  },
  "capacity": 0,
  "enrolled": 0
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|id|string|false|none||none|
|code|string|true|none||none|
|title|string|true|none||none|
|instructor|[Instructor](#schemainstructor)|true|none||none|
|schedule|[ScheduleSlot](#schemascheduleslot)|true|none||none|
|capacity|integer(int32)|false|none||none|
|enrolled|integer(int32)|false|none||none|

<h2 id="tocS_Instructor">Instructor</h2>

<a id="schemainstructor"></a>
<a id="schema_Instructor"></a>
<a id="tocSinstructor"></a>
<a id="tocsinstructor"></a>

```json
{
  "id": "string",
  "name": "string",
  "email": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|id|string|true|none||none|
|name|string|true|none||none|
|email|string|false|none||none|

<h2 id="tocS_ScheduleSlot">ScheduleSlot</h2>

<a id="schemascheduleslot"></a>
<a id="schema_ScheduleSlot"></a>
<a id="tocSscheduleslot"></a>
<a id="tocsscheduleslot"></a>

```json
{
  "dayOfWeek": "string",
  "startTime": "string",
  "endTime": "string",
  "expectedAttendance": 0
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|dayOfWeek|string|true|none||none|
|startTime|string|true|none||none|
|endTime|string|true|none||none|
|expectedAttendance|integer(int32)|false|none||none|

<h2 id="tocS_Student">Student</h2>

<a id="schemastudent"></a>
<a id="schema_Student"></a>
<a id="tocSstudent"></a>
<a id="tocsstudent"></a>

```json
{
  "id": "string",
  "studentId": "string",
  "name": "string",
  "major": "string",
  "grade": 0,
  "email": "string",
  "createdAt": "2019-08-24T14:15:22Z"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|id|string|false|none||none|
|studentId|string|true|none||none|
|name|string|true|none||none|
|major|string|true|none||none|
|grade|integer(int32)|true|none||none|
|email|string|true|none||none|
|createdAt|string(date-time)|false|none||none|

