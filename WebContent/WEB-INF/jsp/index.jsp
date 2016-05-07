<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Тест JSP" stylesheet="CSS/boot/bootstrap.min.css">
		
		
    	<div class="form-group">
        <c:choose>
            <c:when test="${not empty user}">
                <A class="btn btn-default" role="button" href="logout.html"> ${user.login}&nbsp;&mdash; exit</A>
            </c:when>
            <c:otherwise>
                <A class="btn btn-default" role="button" href="login-form.jsp">enter</A>
            </c:otherwise>
        </c:choose>
        </div>
        <div class="form-group">
        <c:if test="${user.role eq 'manager'}">
        
        </c:if>
            <TABLE class="table table-bordered">
                <TR>                
                    <TD>Name</TD>
                    <TD>Rate</TD>
                    <TD>Narrow</TD>
                    <TD>Count</TD>
                    <TD>SumCost</TD>
                    <TH>&nbsp;</TH>
                </TR>
                <c:forEach var="specialty" items="${specialties}">
                    <c:if test="${user.role ne 'manager'}"><TR></c:if>
                	<c:if test="${user.role eq 'manager'}"><TR class="hov" id="editSpecialty.html?id=${specialty.id}"></c:if>
                        <c:if test="${user.role eq 'manager'}">                        
                        </c:if>
                        <TD>${specialty.name}</TD>
                        <TD>${specialty.rate}</TD>
                        <c:if test="${specialty.narrow}">
                        <TD><INPUT type="checkbox" class="form-control" name="narrow-t" value="${specialty.narrow}" disabled checked></TD>
                        </c:if>
                        <c:if test="${!specialty.narrow}">
                        <TD><INPUT type="checkbox" class="form-control" name="narrow-t" value="${specialty.narrow}" disabled></TD>
                        </c:if>
                        <TD>${specialty.count} </TD>
                        <TD>${specialty.sumcost} </TD>
                        <TD><A class="btn btn-default" role="button" href="doctors.html?SpecialtyName=${specialty.name}">View Doctors</A></TD>       
                    </TR>
                </c:forEach>
            </TABLE>
            
        <c:if test="${user.role eq 'manager'}">
            <P>
                <A class="btn btn-default" role="button" href="editSpecialty.html">Add Specialty</A>              
            </P>
        </c:if>
        </div>
         <c:if test="${user.role eq 'admin'}">
            <P>
                <A class="btn btn-default" role="button" href="editUsers.html">View Users</A>
            </P>
        </c:if>
        
        <c:if test="${user.role eq 'manager'}">
        
        </c:if>
        
       
</u:html>