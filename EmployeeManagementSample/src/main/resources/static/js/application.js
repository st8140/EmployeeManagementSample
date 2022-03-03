'use strict';

const searchInput = document.getElementsByClassName('search-input');
const genderRadio = document.getElementsByClassName('gender-radio');

const clearBtn = document.getElementById('search-clear');

const DELETE_MESSAGE = '本当に削除しますか？'

$('.delete-btn').click(function () {
	if(!confirm(DELETE_MESSAGE)) {
		return false;
	}
});

clearBtn.addEventListener('click', () => {
	for (let i = 0; i < searchInput.length; i++) {
		searchInput[i].value = null;
	}
	
	for (let i = 0; i < genderRadio.length; i++) {
		genderRadio[i].checked = false;
	}
});

console.log('a');