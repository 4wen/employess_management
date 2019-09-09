<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/4/26
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>员工列表</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <!--引入bootstrap样式-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</head>
<body>

    <!--搭建显示页面-->
    <div class="container">
        <!--标题-->
        <div class="row">
            <div class="col-md-12">
                <h1>员工管理系统</h1>
            </div>
        </div>

        <!--新增，删除 按钮-->
        <div class="row">
            <div>
                <div class="col-md-4 col-md-offset-8">
                    <button class=" btn btn-primary">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                    </button>
                    <button class="btn btn-danger">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                    </button>
                </div>
            </div>

        </div>

        <!--显示表格数据-->
        <div class="row">
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-hover">
                        <tr>
                            <th>ID</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>email</th>
                            <th>所属部门</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${pageInfo.list}" var="emp">
                            <tr>
                                <th>${emp.empId}</th>
                                <th>${emp.empName}</th>
                                <th>${emp.gender == "M"?"男":"女"}</th>
                                <th>${emp.email}</th>
                                <th>${emp.department.deptName }</th>
                                <th>
                                    <button class=" btn btn-info">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑
                                    </button>
                                    <button class="btn btn-primary">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                    </button>
                                </th>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>


        <!--显示分页信息-->
        <div class="row">
            <!-- 分页文字信息 -->
            <div class="col-md-6">
                <p>当前第${pageInfo.pageNum}页,共${pageInfo.pages}页,共${pageInfo.total }条记录</p>
            </div>
            <!-- 分页条信息 -->
            <div class="col-md-6">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li><a href="${APP_PATH}/emp/getemps?pn=1">首页</a></li>
                        <!-- 如果是首页则禁止点击前一页 -->
                        <c:if test="${pageInfo.isFirstPage }">
                            <li class="disabled"><a href="#" aria-label="Previous"><span
                                    aria-hidden="true">&laquo;</span></a></li>
                        </c:if>
                        <c:if test="${!pageInfo.isFirstPage }">
                            <li><a href="${APP_PATH}/emp/getemps?pn=${pageInfo.prePage}"
                                   aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                        </c:if>

                        <!-- 显示连续的页面并使得当前页面为高亮 -->
                        <c:forEach items="${pageInfo.navigatepageNums }" var="pageNum">
                            <c:if test="${pageNum==pageInfo.pageNum }">
                                <li class="active"><a href="#">${pageNum }</a></li>
                            </c:if>
                            <c:if test="${pageNum!=pageInfo.pageNum }">
                                <li><a href="${APP_PATH}/emp/getemps?pn=${pageNum}">${pageNum }</a></li>
                            </c:if>
                        </c:forEach>
                        <!-- 如果是末页则禁止点击后一页 -->
                        <c:if test="${pageInfo.isLastPage }">
                            <li class="disabled"><a href="#" aria-label="Next"><span
                                    aria-hidden="true">&raquo;</span></a></li>
                        </c:if>
                        <c:if test="${!pageInfo.isLastPage }">
                            <li><a href="${APP_PATH}/emp/getemps?pn=${pageInfo.nextPage}"
                                   aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                        </c:if>
                        <li><a href="${APP_PATH}/emp/getemps?pn=${pageInfo.pages}">末页</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>



</body>
</html>
