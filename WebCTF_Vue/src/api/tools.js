// 导入request.js请求工具
import request from '@/utils/request.js'
// import { useTokenStorn } from '@/stores/token.js';

// 提供base64加密的接口
export const toolsBase64EncodeService = (base64EncodeData) => {
    console.log('base64EncodeData', base64EncodeData);
    // const tokenStore = useTokenStorn();
    return request.get('/tools/base64Encode', {
        params: { data: base64EncodeData }
    });
};

// 提供base64解密的接口
export const toolsBase64DecodeService = (base64DecodeData) => {
    return request.get('/tools/base64Decode', {
        params: { data: base64DecodeData }
    });
}