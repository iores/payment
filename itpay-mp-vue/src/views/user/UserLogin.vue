<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="searchbar">
            <el-form :model="queryParam" size="small" label-width="100px" ref="queryParam">
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="登录名称" prop="name">
                            <el-col :span="16">
                                <el-input v-model="queryParam.loginName" :maxlength="20"  placeholder="请选择"></el-input>
                            </el-col>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="状态" prop="status">
                            <el-col :span="16">
                                <el-select v-model="queryParam.status" :clearable="true" style="width: 100%" placeholder="请选择">
                                    <el-option v-for="item in status" :key="item.value" :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-col>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label=" ">
                            <el-button type="primary" v-on:click="getData">查询</el-button>
                            <el-button @click="restFrom('queryParam')">重置</el-button>
                        </el-form-item>
                    </el-col>
                </el-row>

            </el-form>
        </el-col>
        <!--列表-->
        <el-table :data="users" :highlight-current-row="true" v-loading="listLoading"
                  :stripe="true"    style="width: 100%;border: 1px solid #eaeaea;">
            <el-table-column align="center" type="index"  label="序号" width="70">
            </el-table-column>
            <el-table-column align="center" prop="loginName" label="登录名称" min-width="100" >
            </el-table-column>
            <el-table-column align="center" prop="registerTime" label="注册时间" :formatter="formatDate" min-width="100">
            </el-table-column>
            <el-table-column align="center" prop="status" label="状态" min-width="60" >
                <template slot-scope="scope">
                    <el-tag size="medium" :disable-transitions="false" :type="scope.row.status === '01' ? 'primary' : scope.row.status === '02' ? 'warning' : 'danger'">
                        {{formatStatus(scope.row)}}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column align="center" label="操作" min-width="150">
                <template scope="scope">
                    <el-button type="info" size="small" @click="handleDetail(scope.row.id)">详情</el-button>
                    <el-button type="warning" size="small" @click="handleDel(scope.$index, scope.row)">冻结</el-button>
                </template>
            </el-table-column>
        </el-table>


        <pagination :listPage="listPage" @getDate="getData" ></pagination>
    </section>
</template>

<script>
    import util from '../../common/js/util'
    //import NProgress from 'nprogress'
    import {postLoginListPage} from '../../api/api';
    import pagination from '../../components/my-pagination.vue'

    export default {
        components: {
            props:['listPage'],
            pagination,
        },
        data() {
            return {
                queryParam: {
                    loginName: '',
                    status: '',
                },
                users: [],
                listPage: {
                    pageNum: 1,
                    pageSize: 10,
                    total: 0,
                },
                listLoading: false,
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
        methods: {
            formatStatus: function (row, column) {
                return row.status == '01' ? '正常' : row.status == '02' ? '冻结' : row.status == '03' ? '注销' : '';
            },
            formatDate: function (row, column) {
                return util.formatDate.format(new Date(row.createTime), 'yyyy-MM-dd hh:mm:ss');
            },

            //重置表单
            restFrom(queryFrom) {
                this.$refs[queryFrom].resetFields();
            },
            //查看详情
            handleDetail(detailId) {
                this.$router.push({path: `/user/detail/${detailId}`});
            },
            //获取用户列表
            getData() {
                let para = {
                    listPage: this.listPage,
                    queryParam: this.queryParam
                };
                this.listLoading = true;
                postLoginListPage(para).then((res) => {
                    let {meta, data} = res;
                    if (meta.code != 200) {
                        this.$message({
                            message: meta.message,
                            type: 'error'
                        });
                    } else {
                        this.listPage.total = data.page.total;
                        this.listPage.pageSize = data.page.pageSize;
                        this.users = data.page.list;
                    }
                    this.listLoading = false;
                }).catch((err) => {
                    console.log(err);
                });
            }
        },
        mounted() {
            this.getData();
        }
    }

</script>

<style scoped>
    .searchbar {
        border: 1px solid #eaeaea;
        padding: 20px;
        margin-top: 20px;
        margin-bottom: 20px;
    }

</style>