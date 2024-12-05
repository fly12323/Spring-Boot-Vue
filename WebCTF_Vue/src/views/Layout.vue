<script setup>
import {
    Management,
    Promotion,
    UserFilled,
    User,
    Crop,
    EditPen,
    SwitchButton,
    CaretBottom,
    Grid,
    House
} from '@element-plus/icons-vue'
import avatar from '@/assets/default.png'

// 调用用户信息
import { userInfoService } from '@/api/user.js';
import {useUserInfoStore} from "@/stores/userInfo.js";
const userInfoStore = useUserInfoStore();
const getUserInfo = async () => {
  const result = await userInfoService();
  userInfoStore.setUserInfo(result.data)
}
getUserInfo()

import { useRouter } from "vue-router";
import { useTokenStore } from "@/stores/token.js";
import {ElMessage, ElMessageBox} from "element-plus";
const router = useRouter();
const tokenStore = useTokenStore();
const handleCommand = (command) => {
  if (command === 'logout') {
    ElMessageBox.confirm(
        '你确认要退出吗？',
        '温馨提示',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning'
        }
    ).then(
        async () => {
          // clear data in pinia
          userInfoStore.removeUserInfo()
          tokenStore.removeToken()
          ElMessage.success("退出成功")
          await router.push('/login')
        }
    )
  }
}


</script>

<template>
    <!-- element-plus中的容器 -->
    <el-container class="layout-container">
        <!-- 左侧菜单 -->
        <el-aside width="200px">
            <div class="el-aside__logo"></div>
            <!-- element-plus的菜单标签 -->
            <el-menu active-text-color="#ffd04b" background-color="#232323"  text-color="#fff"router>

                <el-menu-item index="/index">
                    <el-icon>
                        <!-- tui-icon图标 -->
                        <House />
                    </el-icon>
                    <span>主页</span>
                </el-menu-item>

                <el-sub-menu popper-class="no-nest-menu">
                    <template #title>
                        <el-icon>
                            <Grid />
                        </el-icon>
                        <span>编码工具</span>
                    </template>
                    <el-menu-item index="/tools/base64Decode">
                        <span>Base64解密</span>
                    </el-menu-item>
                    <el-menu-item index="/tools/base64Encode">
                        <span>Base64加密</span>
                    </el-menu-item>
                </el-sub-menu>

                <el-menu-item>
                    <el-icon>
                        <Promotion />
                    </el-icon>
                    <span>test</span>
                </el-menu-item>

                <el-sub-menu popper-class="no-nest">
                    <template #title>
                        <el-icon>
                            <UserFilled />
                        </el-icon>
                        <span>个人中心</span>
                    </template>
                    <el-menu-item>
                        <el-icon>
                            <User />
                        </el-icon>
                        <span>test</span>
                    </el-menu-item>
                    <el-menu-item>
                        <el-icon>
                            <Crop />
                        </el-icon>
                        <span>更换头像</span>
                    </el-menu-item>
                    <el-menu-item>
                        <el-icon>
                            <EditPen />
                        </el-icon>
                        <span>重置密码</span>
                    </el-menu-item>
                </el-sub-menu>

            </el-menu>
        </el-aside>
        <!-- 右侧主区域 -->
        <el-container>
            <!-- 头部区域 -->
            <el-header>
                <div>使用者：<strong>{{ userInfoStore.userInfo.username }}</strong></div>
                <!-- 下拉菜单 -->
                <!-- command: 条目被点击后会触发,在事件函数上可以声明一个参数,接收条目对应的指令 -->
                <el-dropdown placement="bottom-end"  @command="handleCommand">
                    <span class="el-dropdown__box">
                        <el-avatar :src="avatar" />
                        <el-icon>
                            <CaretBottom />
                        </el-icon>
                    </span>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <!-- <el-dropdown-item command="info" :icon="User">基本资料</el-dropdown-item>
                            <el-dropdown-item command="avatar" :icon="Crop">更换头像</el-dropdown-item>
                            <el-dropdown-item command="resetPassword" :icon="EditPen">重置密码</el-dropdown-item> -->
                            <el-dropdown-item command="logout" :icon="SwitchButton">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </el-header>
            <!-- 中间区域 -->
            <el-main>
                <!-- <div style="width: 1290px; height: 570px;border: 1px solid red;">
                    内容展示区
                </div> -->
                <router-view></router-view>
            </el-main>
            <!-- 底部区域 -->
            <el-footer>CTF ©2024 Created by rrrfly</el-footer>
        </el-container>
    </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
    height: 100vh;

    .el-aside {
        background-color: #232323;

        &__logo {
            height: 120px;
            background: url('@/assets/logo2.png') no-repeat center / 120px auto;
        }

        .el-menu {
            border-right: none;
        }
    }

    .el-header {
        background-color: #fff;
        display: flex;
        align-items: center;
        justify-content: space-between;

        .el-dropdown__box {
            display: flex;
            align-items: center;

            .el-icon {
                color: #999;
                margin-left: 10px;
            }

            &:active,
            &:focus {
                outline: none;
            }
        }
    }

    .el-footer {
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
        color: #666;
    }
}
</style>