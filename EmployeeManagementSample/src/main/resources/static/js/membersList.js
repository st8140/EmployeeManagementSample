'use strict';

let membersData = null;
let table = null;

jQuery(function($) {
	
	// DataTable初期化
	createDataTables();
	
	// 社員登録
	$('#regist').click(function() {
		if (window.confirm('社員登録を行います。\nよろしいですか？')) {
			document.forms['form'].submit();
			//insertMembers();
		}
	});
	
	/**
	 * 行新規
	 */ 
	
		// 初期表示行数を取得
		let rowIndex = $('#r-table tbody > tr').length;
	
	$('#generate-row').click(function() {	
		
		let formRow = $('#r-table tbody tr:first').clone();
		formRow.find('input').val('');
		//formRow.find('tr').removeAttr('th:name');
		formRow.prev('td').attr('th:name', rowIndex);
		$('#r-table tbody').append(formRow);
		rowIndex++;
	});
});

	/**
	 * 行複製
	 */
	 $('#clone-row').click(function() {	
		
		let formRow = $('#r-table tbody tr:last').clone();
		$('#r-table tbody').append(formRow);
	});

	// 行削除
	function deleteRow(btn) {
		if ($('#r-table tbody tr').length > 1) {
			let row = btn.parentNode.parentNode;
			row.parentNode.deleteRow(row.sectionRowIndex);
		} else {		
			alert('これ以上削除できません。');
		}
	}

/** 登録処理 */
function insertMembers() {
	
	// バリデーション結果クリア
	removeValidResult();
	
	// フォームの値を取得
	let formData = $('#form').serializeArray();
	
	// department_idを取得
	const departmentId = $(location).attr('pathname').substring(9,10);
	
	$.ajax({
		type : "POST",
		cache : false,
		url : `/members/${departmentId}/registration/rest`,
		data : formData,
		dataType : 'json',
	}).done(function(data) {
		if (data.result === 90) {
			$.each(data.errors, function(key, value) {
				reflectValidResult(key, value)
				console.log(reflectValidResult(key, value));
			});
		} else if (data.result === 0) {
			alert('社員を登録しました');
			window.location.href = '/members/membersList';
		}
	}).fail(function(jqXHR, textStatus, errorRhrown) {
		alert('エラーのため、登録に失敗しました');
	}).always(function() {
		
	});
}

/** バリデーション結果をクリア */
function removeValidResult() {
	$('.is-invalid').removeClass('.is-invalid');
	$('.invalid-feedback').remove();
	$('.text-danger').remove();
}

/** バリデーション結果の反映 */
function reflectValidResult(key, value) {
	
	// エラーメッセージ追加
	$('input[id=' + key + ']').addClass('is-invalid');
	$('input[id=' + key + ']')
		.after('<div class="invalid-feedback">' + value + '</div>');
}


/** リスト表示処理 */
function loadList() {
	$.ajax({
		type : "GET",
		url : '/employee/getList',
		dataType : 'json',
	}).done(function(data, textStatus, jqXHR) {
		if (!data) {
			alert('該当するデータがありませんでした');
			return;
		}
		employeeData = data;
		createDataTables();
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert('エラーが発生したため一覧表示に失敗しました');
	}).always(function() {
		
	});
}


/** DataTables作成 */
function createDataTables() {
	// すでに作成されている場合
	if (table != null) {
		// table破棄
		table.destroy();
	}
	
	// 作成
	table = $('#members-list-table').DataTable({
		// 日本語化
		language: {
			url: '/webjars/datatables-plugins/i18n/Japanese.json'
		},
		// データセット
		data: membersData,
	})	
}