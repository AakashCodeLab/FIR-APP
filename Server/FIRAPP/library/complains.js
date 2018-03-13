// JavaScript Document
function checkAddUserForm()
{
	with (window.document.frmAddUser) {
		if (isEmpty(txtUserName, 'Enter user name')) {
			return;
		} else if (isEmpty(txtPassword, 'Enter password')) {
			return;
		} else {
			submit();
		}
	}
}

function viewComplainDetail(compId)
{
	window.location.href = 'view.php?mod=super&view=viewByCompID&compId='+compId;
}


function closeComplain(compId)
{
	window.location.href = 'view.php?mod=employee&view=closeComplain&compId='+compId;
}


function changePassword(userId)
{
	window.location.href = 'index.php?view=modify&userId=' + userId;
}



