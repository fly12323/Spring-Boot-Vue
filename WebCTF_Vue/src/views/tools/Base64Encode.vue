<!-- // base64加密 -->
<script setup>
import { ref } from 'vue'
import { toolsBase64EncodeService } from '@/api/tools.js'

// 定义响应式数据
const inputText = ref('');
const base64Text = ref('');

const base64Encode = async () => {
    let result = await toolsBase64EncodeService(inputText.value);
    if (result.code === 0) {
        base64Text.value = result.data; // 假设后端返回的Base64编码结果是result.data
    } else {
        base64Text.value = result.msg; // 错误信息
    }
}
</script>

<template>
    <div>
      <input type="text" v-model="inputText" placeholder="请输入文本" />
      <button @click="base64Encode">加密</button>
      <p>加密后的Base64编码：{{ base64Text }}</p>
    </div>
</template>