<%@ page language="java" import="java.net.InetAddress" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Moderna vs Pfizer!</title>
    <!-- <base href="/index.jsp"> -->
    <meta name = "viewport" content = "width=device-width, initial-scale = 1.0">
    <meta name="keywords" content="docker-compose, docker, stack">
    <meta name="author" content="Tutum dev team">
    <link rel='stylesheet' href="css/style.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  </head>
  <body>
    <div id="content-container">
      <div id="content-container-center">
        <h3>Which vaccine have you gotten?</h3>
        <form id="choice" name='form' method="post" action="voteServlet">
          <button id="a" type="submit" name="vote" class="a" value="a">Moderna</button>
          <button id="b" type="submit" name="vote" class="b" value="b">Pfizer</button>
        </form>
        <div id="tip">
          (Tip: you can change your vote)
        </div>
        <div id="hostname">
          <%
            String containerHost = InetAddress.getLocalHost().getHostName();
          %>
          Processed by container ID <%= containerHost %> <br>
          <% 
            String arch = System.getProperty("os.arch");
          %>
          Running in Tomcat on architecture: <%= arch %>
        </div>
      </div>
    </div>
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js"></script>

<!-- 
    <script>
      var vote = "a";

      if(vote == "a"){
        $(".a").prop('disabled', true);
        $(".a").html('Moderna <i class="fa fa-check-circle"></i>');
      }
      if(vote == "b"){
        $(".b").prop('disabled', true);
        $(".b").html('Pfizer <i class="fa fa-check-circle"></i>');
      }

    </script> -->

  </body>
</html>
