<%@ page import="spms.vo.Member" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ�� ���</title>
</head>
<body>
<h1>ȸ�����</h1>
<p><a href='add'>�ű�ȸ��</a></p>
<%
ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
for(Member member: members){
%>
<%=member.getNo()%>,
<a href='update?no=<%=member.getNo()%>'><%=member.getName()%></a>,
<%=member.getEmail()%>,
<%=member.getCreateDate()%>
<a href='delete?no=<%=member.getNo()%>'>[����]</a><br>
<%} %>

</body>
</html>