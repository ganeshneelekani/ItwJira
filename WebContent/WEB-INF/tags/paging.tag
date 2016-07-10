<%@ tag import="org.springframework.util.StringUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="pagedListHolder" required="true" type="org.springframework.beans.support.PagedListHolder" %>
<%@ attribute name="pagedLink" required="true" type="java.lang.String" %>

<link type="text/css" rel="stylesheet" href="css/pagination.css"  />


<c:if test="${pagedListHolder.pageCount > 1}">
    <c:if test="${!pagedListHolder.firstPage}">
        <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage()-1)) %> " class="btn btn-info btn-small">&lt;</a></span>
    </c:if>
    <c:if test="${pagedListHolder.firstLinkedPage > 0}">
        <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", "0") %>" class="btn btn-success btn-small">1</a></span>
    </c:if>
    <c:if test="${pagedListHolder.firstLinkedPage > 1}">
        <span class="pagingDots">...</span>
    </c:if>
    <c:forEach begin="${pagedListHolder.firstLinkedPage}" end="${pagedListHolder.lastLinkedPage}" var="i">
        <c:choose>
            <c:when test="${pagedListHolder.page == i}">
                <span class="pagingItemCurrent"><a href="javascript:this(void)" class="btn btn-danger btn-small">${i+1}</a></span>
            </c:when>
            <c:otherwise>
                <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(jspContext.getAttribute("i"))) %>" class="btn btn-success btn-small">${i+1}</a></span>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${pagedListHolder.lastLinkedPage < pagedListHolder.pageCount - 2}">
        <span class="pagingDots">...</span>
    </c:if>
    <c:if test="${pagedListHolder.lastLinkedPage < pagedListHolder.pageCount - 1}">
        <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPageCount()-1)) %>" class="btn btn-success btn-small">${pagedListHolder.pageCount}</a></span>
    </c:if>
    <c:if test="${!pagedListHolder.lastPage}">
        <span class="pagingItem"><a href="<%= StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage()+1)) %>" class="btn btn-info btn-small">&gt;</a></span>
    </c:if>
</c:if>