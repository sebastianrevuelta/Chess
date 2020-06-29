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
<h2>Vulnerable Chess Game</h2>
<h5>You can try XML Entity Injection (XXE) in this page ;-)</h5>
<h6>According to OWASP TOP TEN: it is risk #4, more info <a href="https://owasp.org/www-project-top-ten/OWASP_Top_Ten_2017/Top_10-2017_A4-XML_External_Entities_(XXE)">here</a></h6>
<div class="bgimg">
  <div class="topleft">
  </div>
  <div class="middle">
    <h3>Please choose your xml file<span id="myText"></span></h3>
    <form action="./LoadGame" method="post">
		<input id="xmlfile" name="xmlfile" value="c:/tmp/myMatch.xml"> 
		<button id="button" type="submit">Load XML</button>
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
