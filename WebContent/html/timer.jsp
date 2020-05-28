<!DOCTYPE html>
<html>
<style>
body, html {
  height: 100%;
  margin: 0;
}

.bgimg {
  background-image: url('./images/start.jpg');
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
    <h1>STARTING GAME IN <span id="myText"></span></h1>
    <form action="Timer" method="GET">
		<input id="timer" name="timer" value="10"> 
		<input id="button" type="submit" value="Seconds">
	</form>
	<br>
    <hr>
    <p id="demo" style="font-size:30px"></p>
  </div>
  <div class="bottomleft">
  </div>
</div>

<script>
// THIS 3 LINES BELOW ARE NOT DETECTED BY KIUWAN AS VULNERABILITY
// const queryString = window.location.search;
// const urlParams = new URLSearchParams(queryString);
// const time = urlParams.get('timer');

var url = document.location.href;
var from = url.indexOf("timer=")+6;
if (from != 5) { //-1+6
	var time = decodeURI(url.substring(from));
	
	document.getElementById("myText").innerHTML = time;
	
	if(!isNaN(parseInt(time))){
		
		// Set the date we're counting down to
		var now = new Date(Date.now());
		now.setSeconds(now.getSeconds() + parseInt(time));
		var countDownDate = now.getTime();
		
		// Update the count down every 1 second
		var countdownfunction = setInterval(function() {
		
			// Get todays date and time
			var now = new Date().getTime();
		
			// Find the distance between now an the count down date
			var distance = countDownDate - now;
		
			// Time calculations for days, hours, minutes and seconds
			var days = Math.floor(distance / (1000 * 60 * 60 * 24));
			var hours = Math.floor((distance % (1000 * 60 * 60 * 24))/ (1000 * 60 * 60));
			var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
			var seconds = Math.floor((distance % (1000 * 60)) / 1000);
		
			// Output the result in an element with id="demo"
			document.getElementById("demo").innerHTML = days + "d " + hours + "h "
					+ minutes + "m " + seconds + "s ";
		
			// If the count down is over, write some text 
			if (distance < 0) {
				clearInterval(countdownfunction);
				document.getElementById("demo").innerHTML = "EXPIRED";
				window.location.href = "./Game"
			}
		}, 1000);
	}
}
</script>

</body>
</html>
