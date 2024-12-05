<!-- // base64解密 -->
<script setup>
import { ref } from 'vue'
import { toolsBase64DecodeService } from '@/api/tools.js'

// 定义响应式数据
const encodedText = ref('');
const decodedText = ref('');

const base64Decode = async () => {
    let result = await toolsBase64DecodeService(encodedText.value);
    if (result.code === 0) {
        decodedText.value = result.data; // 假设后端返回的Base64解码结果是result.data
    } else {
        decodedText.value = result.msg; // 错误信息
    }
}
</script>

<template>
    <div>
      <input type="text" v-model="encodedText" placeholder="请输入Base64编码的文本" />
      <button @click="base64Decode">解密</button>
      <p>解密后的文本：{{ decodedText }}</p>
    </div>
</template>