<!-- 与传统应用的视图层不同 ，这个jsp返回的是xml的数据，因此contentType的值是text/xml-->   
<%@ page contentType="text/xml;charset=UTF-8" language="java" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

  
<words>   
     <c:forEach var="nsrwjbm" items="${nsrxxs}">   
         <nsrwjbm>${nsrwjbm}</nsrwjbm>   
     </c:forEach> 
       
</words>  
