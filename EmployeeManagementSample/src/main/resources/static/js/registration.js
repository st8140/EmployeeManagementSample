'use strict';

let employeeData = null;
let table = null;

jQuery(function($) {
	
	// DataTable初期化
	createDataTables();
	
	// 社員登録
	$('#regist').click(function() {
		// getElementsByClassName('form');
		if (window.confirm('社員登録を行います。\nよろしいですか？')) {
			//document.forms['form'].submit();
			 insertEmployees();
		}
	});
	
	// 行新規
	$('#generate-row').click(function() {	
		let formRow = $('#r-table tbody tr:first').clone();
		formRow.find('input').val('');
		$('#r-table tbody').append(formRow);
	});
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
function insertEmployees() {
	
	// バリデーション結果クリア
	removeValidResult();
	
	// フォームの値を取得
	let formData = $('#form').serializeArray();
	
	$.ajax({
		type : "POST",
		cache : false,
		url : '/employee/registration/rest',
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
			window.location.href = '/employee/registration';
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
	table = $('#employee-list-table').DataTable({
		// 日本語化
		language: {
			url: '/webjars/datatables-plugins/i18n/Japanese.json'
		},
		// データセット
		data: employeeData,
		// マッピング
		column: [
			{ data: 'employee_id'},
			{ data: 'employee_name'},
			{ 
				data: 'gender',
				render: function( data, type, row) {
					let gender = '';
					if (data === 1) {
						gender = '男性';
					} else {
						gender = '女性';
					}
					return gender;
			 	}
			 },
			 { data: 'employee_phone'},
			 { data: 'employee_email'},
			 { 
				data: 'date_of_entry',
				render: function ( data, type, row) {
					var date = new Date(data);
					let year = date.getFullYear();
					let month = date.getMonth() +1;
					var date = data.getDate();
					return year + '/' + month + '/' + date;
				}
			 },
			 {
				data: 'employee_id',
				render: function( data, type, row) {
					let url = '<a href="/employee/' + data + '">編集</a>';
					return url;
				}
			 },
		]
	})	
}