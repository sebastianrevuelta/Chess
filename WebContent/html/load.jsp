<!DOCTYPE html>
<html>
<style>
body, html {
  height: 100%;
  margin: 0;
}

.bgimg {
  background-image: url('./images/xml.jpg');
  height: 100%;
  background-position: center;
  background-size: cover;
  position: relative;
  color: white;
  font-family: "Courier New", Courier, monospace;
  font-size: 25px;
}

.topleft {
  position: absolute;
  top: 0;
  left: 16px;
}

.bottomleft {
  position: absolute;
  bottom: 0;
  left: 16px;
}

.middle {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

hr {
  margin: auto;
  width: 40%;
}
</style>
<body>

<div class="bgimg">
  <div class="topleft">
  </div>
  <div class="middle">
    <h1>CHOOSE YOUR XML FILE <span id="myText"></span></h1>
    <form action="./LoadGame" method="post">
		<input id="xmlfile" name="xmlfile" value="c:/tmp/myMatch.xml"> 
		<input id="button" type="submit" value="Send">
	</form>
	<script>
	<% if (request.getSession().getAttribute("error") != null){ %>
		alert("<%=request.getSession().getAttribute("error")%>")
		
	<% request.getSession().removeAttribute("error");}%>
</script>
	<br>
    <hr>
    <p id="demo" style="font-size:30px"></p>
  </div>
  <div class="bottomleft">
  </div>
</div>

</body>
</html>
