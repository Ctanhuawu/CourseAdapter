package parser

import bean.Course
import bean.CourseBaseBean
import bean.CourseDetailBean

abstract class Parser(val source: String) {

    private val _baseList: ArrayList<CourseBaseBean> = arrayListOf()
    private val _detailList: ArrayList<CourseDetailBean> = arrayListOf()

    abstract fun generateCourseList(): List<Course>

    private fun convertCourse() {
        generateCourseList().forEach { course ->
            var id = Common.findExistedCourseId(_baseList, course.name)
            if (id == -1) {
                id = _baseList.size
                _baseList.add(
                    CourseBaseBean(
                        id = id, courseName = course.name,
                        color = "",
                        tableId = 0
                    )
                )
            }
            _detailList.add(
                CourseDetailBean(
                    id = id, room = course.room,
                    teacher = course.teacher, day = course.day,
                    step = course.endNode - course.startNode + 1,
                    startWeek = course.startWeek, endWeek = course.endWeek,
                    type = course.type, startNode = course.startNode,
                    tableId = 0
                )
            )
        }
    }

    fun saveCourse() {
        convertCourse()
        println("成功导入 ${_baseList.size} 门课程")
        _baseList.forEach {
            println(it)
        }
        _detailList.forEach {
            println(it)
        }
    }

}