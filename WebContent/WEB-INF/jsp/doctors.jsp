<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Тест JSP" stylesheet="CSS/boot/bootstrap.min.css">
     
        <u:test objects="${doctors}" role="${user.role}" back="index.html" creator="editDoctor.html?SpecialtyName=${SpecialtyName}" editor="editDoctor.html?id="></u:test>
      
</u:html>