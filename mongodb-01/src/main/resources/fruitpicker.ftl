<html>
	<head>
		<title>Fruit Picker</title>
	</head>
	<body>
		<form action="/favourite_fruit" method="POST">
			<p>What is your favourive fruit?</p>
			<#list fruits as fruit>
				<p>
					<input type="radio" name="fruit" value="${fruit}">${fruit}</input>
				</p>
			</#list>
			<input type="submit" value="Submit"/>
		</form>
	</body>
</html>