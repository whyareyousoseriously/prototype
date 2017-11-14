
/*
 * function:show or hidden div; showDiv(divId_array,showLocality_array)
 * @param  divId_array{the id of div that you want to show or hidden }
 * 			showLoacality_array{the location of div that you want to control to show or hidden in divId_array,
 * 								the 0 or null represent show them all, negative represent hidden them all }
 * for example: showDiv('div3,div1,div2','2,3') means that to show the div1 which the location is 2, same that to 
 * show the div2 which the location is 3, the div3 will be hiddened because of the location of it is 1, 1 don't exist the
 * showLocality_array.
 * == 函数：显示或隐藏层 showDiv(divId_array,showLocality_array)
 * 参数说明： divId_array 所有要操作的层ID，用逗号分隔 showLocality_array
 * 所有要显示的层在参数DivIdArray所对应的位置。为“0”或空时全部显示，设为负数则全部隐藏。
 * 例如：showDiv('div3,div1,div2','2,3')为显示第二个(div1)和第三个层(div2)，第一个层(div3)将被隐藏。
 * ========================
 */
function showDiv(divId_array, showLocality_array) {
	var div_array = new Array();
	var locality_array = new Array();
	var intLocality;// showLocality_array数组长度
	var intDiv;// div_array 数组长度
	var intTemp = 1;// 临时变量
	var intTmp = 1;// 临时变量

	// 初始化
	if (divId_array) {
		div_array = divId_array.split(",");
	} else {
		intDiv = 0;
	}
	if (showLocality_array) {
		locality_array = showLocality_array.split(",");
		intLocality = locality_array.length;

		for (var n = 0; n < intLocality; n++) {
			intTemp *= locality_array[n];

		}
		if (intTemp <= 0) {
			intLocality = intTemp;
		} else {
			intLocality = div_array.length;

			while (intLocality > 0) {
				intLocality -= 1;
				document.getElementById("" + div_array[intLocality] + "").style.display = "none";
			}
			intLocality = 1;
		}
	} else {
		intLocality = 0;
	}

	// 显示指定层
	if (intDiv != 0 && intLocality > 0) {
		intDiv = div_array.length;
		intLocality = locality_array.length;

		// 排序locality_array 数组
		for (var n = 0; n < intLocality; n++) {
			intTemp = locality_array[n];

			for (var m = n + 1; m < intLocality; m++) {
				if (locality_array[n] > locality_array[m]) {
					locality_array[n] = locality_array[m];
					locality_array[m] = intTemp;
					intTemp = locality_array[n];
				}
			}
		}

		intLocality = locality_array.length;
		intTemp = 0;

		//
		while (intTemp < intLocality) {
			if ((locality_array[intTemp] - 1) < div_array.length) {
				intTmp = locality_array[intTemp] - 1;
				document.getElementById("" + div_array[intTmp] + "").style.display = "block";
			}

			intTemp += 1;
		}
	} else if (intLocality == 0) {// 显示所有层
		intLocality = div_array.length;

		while (intLocality > 0) {
			intLocality -= 1;
			document.getElementById("" + div_array[intLocality] + "").style.display = "block";
		}
	} else {// 隐藏所有层
		intLocality = div_array.length;

		while (intLocality > 0) {
			intLocality -= 1;
			document.getElementById("" + div_array[intLocality] + "").style.display = "none";
		}
	}
}