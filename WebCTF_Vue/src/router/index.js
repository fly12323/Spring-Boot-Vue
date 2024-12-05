import { createRouter,createWebHistory } from "vue-router";

// 导入组建
import LoginVue from "@/views/Login.vue";
import LayoutVue from "@/views/Layout.vue";

import IndexVue from "@/views/Index.vue";
import Base64DecodeVue from "@/views/tools/Base64Decode.vue";
import Base64EncodeVue from "@/views/tools/Base64Encode.vue";


// 定义路由关系
const routes = [
        { path : '/login',component: LoginVue},
        // 子路由，redirect重定向
        { path : '/',component: LayoutVue, redirect:'/index' , children: [
            { path : '/index',component: IndexVue},
            { path : '/tools/base64Decode',component: Base64DecodeVue}, // base64解码
            { path : '/tools/base64Encode',component: Base64EncodeVue}, // base64编码
            
        ]}
]

// 创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

// 导出路由
export default router