function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    fetch('/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('登录成功');
                localStorage.setItem('token', data.data);
            } else {
                alert(data.message);
            }
        });
}

function register() {
    const username = document.getElementById('registerUsername').value;
    const password = document.getElementById('registerPassword').value;
    fetch('/user/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('注册成功');
            } else {
                alert(data.message);
            }
        });
}

function getUserInfo() {
    const token = localStorage.getItem('token');
    fetch('/user/userInfo', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
        },
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                document.getElementById('userInfo').textContent = `用户名: ${data.data.username}`;
            } else {
                alert(data.message);
            }
        });
}

function updatePwd() {
    const oldPwd = document.getElementById('oldPwd').value;
    const newPwd = document.getElementById('newPwd').value;
    const rePwd = document.getElementById('rePwd').value;
    const token = localStorage.getItem('token');
    fetch('/user/updatePwd', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify({ old_pwd: oldPwd, new_pwd: newPwd, re_pwd: rePwd }),
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('密码修改成功');
            } else {
                alert(data.message);
            }
        });
}