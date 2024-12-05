// 导入request.js请求工具
import request from '@/utils/request'

// 提供调用注册接口的函数
export const userRegisterService = (registerData) => {
    // 借助于UrlSearchParams完成传参
    const parems = new URLSearchParams()
    for(let key in registerData){
        parems.append(key, registerData[key])
    }
    return request.post('/user/register', parems);
}

// 提供调用登录接口的函数
export const userLoginService = (loginData) => {
    // 借助于UrlSearchParams完成传参
    const parems = new URLSearchParams()
    for(let key in loginData){
        parems.append(key, loginData[key])
    }
    return request.post('/user/login', parems);
}

// 调用用户信息接口
export const userInfoService = () => {
    return request.get('/user/userInfo');
}