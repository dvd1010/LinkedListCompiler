<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<script src="resources/js/jquery-1.6.2.min.js"></script>
<script src="resources/js/jquery.bpopup.min.js"></script>
<script src="resources/js/llcompiler.js"></script>

<title>Linked List Home</title>
</head>
<body>
	<h2>Linked List Compiler</h2>
	<table>
		<tr height="50%">
			<td>
				<div>
					<strong>Sample Java Code to find middle element of a SINGLY LINKED LIST</strong>
					<br/>	
					<textarea name="textarea" id="code"  style="width: 700px; height: 400px;" rows="30">${codeTemplate}</textarea>
					<br/>	
				</div>
				</td>
			<td width="50%">
				<div>
					<div id="linkedListCode" style="display:none; color: white">
						<div style="color:white">
							<h3>Sample Linked List Implementation</h3>
						</div>
						<textarea readonly name="textarea" style="width: 700px; height: 600px;" rows="40">${sampleLLTemplate}</textarea>
					</div>
					<div id="nodeCode" style="display:none; color: white">
						<div style="color:white">
							<h3>Sample Node Implementation</h3>
						</div>
						<textarea readonly name="textarea" style="width: 700px; height: 460px;" rows="40">${nodeTemplate}</textarea>
					</div>
					<input type=button id="linkListImpl" class="linkListImpl" value="Sample Linked List Implementation" 
							style="width: 500px; height: 100px;">
					<br/><br/><br/><br/>
					<input type=button id="nodeImpl" class="nodeImpl" value="Sample Node Implementation"
					style="width: 500px; height: 100px;">
				</div>
			</td>
		</tr>
	</table>
	<div id="parent-ll" align="left">
		<strong>
			Size of Linked List
		</strong>
		<input type="number" required="required"  id="ll-length"  autofocus="autofocus" min="1" max="10">
		
		<input type=button id="linked" class="linked" value="Generate"><br/><br/> 
	</div>
	<br/>
	<div>
		<input type='button' id='submit-code' value='Submit Code With Linked List Data' style="display:none">
	</div>		
	<br/>
</body>

</html>