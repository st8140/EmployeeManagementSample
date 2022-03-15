'use strict';

//クリアするsearchフィールド
const searchInput = document.getElementsByClassName('search-input');
const genderRadio = document.getElementsByClassName('gender-radio');

//クリアボタン
const clearBtn = document.getElementById('search-clear');

//削除confirm
const DELETE_MESSAGE = '本当に削除しますか？'

$('.delete-btn').click(function () {
	if(!confirm(DELETE_MESSAGE)) {
		return false;
	}
});

/**
* 退職confirm
*/
const RETIREMENT_MESSAGE = "この作業は修正できません。本当によろしいですか?"

$('.retirement-btn').click(function () {
	if(!confirm(RETIREMENT_MESSAGE)) {
		return false;
	}
});

const eachBody = $('#each-body');
const departmentId = $(location).attr('pathname').substring(9,10);

//全社員表示
$('#all').click(function () {
	window.location.pathname = `/members/${departmentId}/membersList`;
	//$('input[type="radio"]').attr('checked', false);
	$('input[id="all"]').prop('checked', true);
	
});

//在籍社員表示
$('#enrollment').click(function () {
	window.location.pathname = `/members/${departmentId}/enrollmentList`;
	//$('input[type="radio"]').attr('checked', false);
	$('input[id="enrollment"]').prop('checked', true);
});

//退職社員表示
$('#retirement').click(function () {
	window.location.pathname = `/members/${departmentId}/retirementList`;
	//$('input[type="radio"]').attr('checked', false);
	$('input[id="retirement"]').prop('checked', true);
});


/**

function toAll() {
	$.ajax({
		url: `/members/${departmentId}/membersList`,
		type: 'GET',
		timeout: 60000,
		data: null
	})
	
	//Ajax通信成功の場合
    .done(function() {
		$('input[type="radio"]').prop('checked', false);
    })
    //Ajax通信失敗の場合
    .fail(function() {
        alert('fail!');
    })
    //Ajax通信の成功・失敗に関わらず最後に処理
    .always(function() {
        alert('end');
    });
}

function toEnrollment() {
	$.ajax({
		url: `/members/${departmentId}/enrollmentList`,
		type: 'GET',
		timeout: 60000,
		data: null
	})
	
	//Ajax通信成功の場合
    .done(function() {
	location.reload();
    })
}

function toRetirement() {
	$.ajax({
		url: `/members/${departmentId}/retirement`,
		type: 'GET',
		timeout: 60000,
		data: null
	});
}

*/

//検索条件クリア
clearBtn.addEventListener('click', () => {
	for (let i = 0; i < searchInput.length; i++) {
		searchInput[i].value = null;
	}
	
	for (let i = 0; i < genderRadio.length; i++) {
		genderRadio[i].checked = false;
	}
});
	
	all.addEventListener('click', () => {
		eachBody.innerHTML =
				'<tr id="each-body" th:each="item: ${employeeJoinMembersList}"></tr>';					
	});
	
	enrollment.addEventListener('click', () => {
		eachBody.innerHTML =
				'<tr id="each-body" th:each="item: ${findEnrollmentEmployeeList}"></tr>';					
	});


