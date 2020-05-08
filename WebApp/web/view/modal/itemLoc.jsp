<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">


<script src="/WebApp/js/jquery.seat-charts.js"></script>
<link href="/WebApp/css/jquery.seat-charts.css" rel="stylesheet">


<script>
	var colName = {1:'A', 2:'B', 4:'C', 5:'D', 7:'E', 8:'F', 10:'G', 11:'H', 13:'I', 14:'J', 16:'K', 17:'L'}; 
	//var seatLabel = 1;
	// var itMap = "";
	var itemPosition = null;
	var itMap1;
	var itMap2;
	var itMap = [];
	$(document).ready( function(){
				var sc = $('#seat-map').seatCharts(
						{
							map : [ 'aa_aa_aa_aa_aa_aa', 
									'aa_aa_aa_aa_aa_aa', 
									'aa_aa_aa_aa_aa_aa', 
									'aa_aa_aa_aa_aa_aa', 
									'aa_aa_aa_aa_aa_aa', 
									'aa_aa_aa_aa_aa_aa', 
									'aa_aa_aa_aa_aa_aa', 
									'aa_aa_aa_aa_aa_aa', 
									'aa_aa_aa_aa_aa_aa', 
									'aa_aa_aa_aa_aa_aa',],
										
							legend : {
								node : $('#legend'),
								items : [
									[ 'a', 'available',   'Available' ],
									[ 'f', 'unavailable', 'Unavailable']
								]
							},
							seats: {

							},
							naming : {
								top : false,
								getId  : function(character, row, column) {
									var positionID = colName[column];
									var temp = 11-row;
									if(temp < 10){
										positionID += '0'+temp;
									}else{
										positionID += temp;
									}
									return positionID;
								},
								getLabel : function (character, row, column) {
									var positionID = colName[column];
									var temp = 11-row;
									if(temp < 10){
										positionID += '0'+temp;
									}else{
										positionID += temp;
									}
									return positionID;
									
									
								},
							},
							click : function() {
								if (this.status() == 'available') {
									console.log(this.settings.id);
									itemPosition = this.settings.id;
									$('#setLoc').html('Selected : ' + itemPosition);
									sc.find('a.available').status('unavailable');
									return 'selected';
								} else if (this.status() == 'selected') {
									sc.find('a').status('available');
									$('#setLoc').html('');
									return 'available';
								} else if (this.status() == 'unavailable') {
									//seat has been already booked
									return 'unavailable';
								} else {
									return this.style();
								}
							}
						});
				// 이미 아이템 들어가 있는 좌석 
				$(function()  {
						$.ajax({
							type : "post",
							url : "getItemLoc.pc",

		 					success : function(data) { 
		 						
		 						
								for (var i = 0; i < data.length; i++) {
									
									console.log(data[i]);
									sc.get(data[i]).status('unavailable');
								}
								///////////////test/////////
								sc.get('L10').status('available');
								sc.get('K08').status('available');
								///////////////////////////
							
							},
							error : function(request, status, error) { 
								console.log("code:" + request.status + "\n"
										+ "message:" + request.responseText
										+ "\n" + "error:" + error);
							}
					});
				
				});
				
				//Make all available 'c' seats unavailable
				/*
				sc.find('E10').status('unavailable');
				sc.find('2_6').status('unavailable');
				*/
				/*
				Get seats with ids 2_6, 1_7 (more on ids later on),
				put them in a jQuery set and change some css
				 */
				//sc.get([ '2_6', '1_7' ]).node().css({
				//	color : '#ffcfcf'
				//});
				

			});
</script>
</head>
<body>

	<div id="seat-map" class="seatCharts-container"></div>
	<span id="setLoc" style="font-weight: bold; margin-left:10px;"> </span>



</body>
</html>
