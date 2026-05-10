<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<view class="text-area">
			<text class="title">{{title}}</text>
		</view>
		<view class="api-data">
			<text v-if="loading">加载中...（后端处理需要5秒）</text>
			<text v-else>{{apiData}}</text>
		</view>
		<button @click="fetchData" class="refresh-btn">刷新数据</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello',
				apiData: '',
				loading: false
			}
		},
		onLoad() {
			this.fetchData();
		},
		methods: {
			async fetchData() {
				this.loading = true;
				uni.request({
					url: 'http://localhost:8080/api/test',
					method: 'GET',
					header: {
						'Authorization': 'ok'
					},
					success: (res) => {
						console.log('请求结果:', res);
						if (res.statusCode === 200) {
							this.apiData = res.data;
							this.title = '请求成功';
						} else if (res.statusCode === 401) {
							this.apiData = '未授权访问';
						} else {
							this.apiData = '请求失败: ' + res.statusCode;
						}
					},
					fail: (error) => {
						console.error('请求错误:', error);
						this.apiData = '网络错误';
					},
					complete: () => {
						this.loading = false;
					}
				});
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}

	.api-data {
		margin-top: 40rpx;
		padding: 20rpx;
		background-color: #f5f5f5;
		border-radius: 10rpx;
		min-height: 60rpx;
		text-align: center;
	}

	.refresh-btn {
		margin-top: 40rpx;
		width: 300rpx;
	}
</style>
