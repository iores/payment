<template>
    <section>
        <el-form :model="queryParam"   ref="queryParam" label-width="100px">
            <el-form-item label="姓名" prop="name">
               11111111111111
            </el-form-item>
            <el-form-item label="手机号" prop="phone">
                11111111111111
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="queryParam.email" placeholder="邮箱"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
                <el-select v-model="queryParam.sex" placeholder="请选择">
                    <el-option v-for="item in sexs" :key="item.value" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="状态" prop="status">
                <el-select v-model="queryParam.status" placeholder="请选择">
                    <el-option v-for="item in status" :key="item.value" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="  ">
                <el-button type="primary" v-on:click="getUsers">查询</el-button>
                <el-button @click="restFrom('queryParam')">重置</el-button>
            </el-form-item>
        </el-form>
    </section>
</template>
<script>
    export default {
        data() {
            return {
                queryParam: {
                    name: '',
                    phone: '',
                    email: '',
                    sex: '',
                    status: '',
                },
                users: [],
                listPage: {
                    pageNum: 1,
                    pageSize: 10,
                    total: 0,
                },
                listLoading: false,
                sels: [],//列表选中列
                sexs: [{
                    value: '1',
                    label: '男'
                }, {
                    value: '2',
                    label: '女'
                }],
                status: [{
                    value: '01',
                    label: '正常'
                }, {
                    value: '02',
                    label: '冻结'
                }, {
                    value: '03',
                    label: '注销'
                }],
            }
        },
    }
</script>