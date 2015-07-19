<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#call').click(function ()
            {
                $.ajax({
                    type: "post",
                    url: "testme", //this is my servlet
                    data: "input=" +$('#ip').val()+"&output="+$('#op').val(),
                    success: function(msg){      
                            $('#output').append(msg);
                    }
                });
            });

        });
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    input:<input id="ip" type="text" name="" value="" /><br></br>
    output:<input id="op" type="text" name="" value="" /><br></br>
    <input type="button" value="Call Servlet" name="Call Servlet" id="call"/>
    <div id="output"></div>
</body>