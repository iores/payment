<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="searchbar">
            <el-form :model="queryParam" :rules="rules" size="small" label-width="100px" ref="queryParam">
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="姓名" prop="name">
                            <el-col :span="16">
                                <el-input v-model.trim="queryParam.name" :maxlength="20"  placeholder="姓名"></el-input>
                            </el-col>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="手机号" prop="phone">
                            <el-col :span="16">
                                <el-input v-model.trim="queryParam.phone" :maxlength="20" placeholder="手机号"></el-input>
                            </el-col>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="邮箱" prop="email">
                            <el-col :span="16">
                                <el-input v-model.trim="queryParam.email" :maxlength="100"  placeholder="邮箱"></el-input>
                            </el-col>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="性别" prop="sex">
                            <el-col :span="16">
                                <el-select :clearable="true" v-model="queryParam.sex" style="width: 100%" placeholder="请选择">
                                    <el-option v-for="item in sexs" :key="item.value" :label="item.label"
                                               :value="item.value">
                                    </el-option>
                                </el-select>
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
                            <el-button type="primary" v-on:click="search('queryParam')">查询</el-button>
                            <el-button @click="restFrom('queryParam')">重置</el-button>
                        </el-form-item>
                    </el-col>
                </el-row>

            </el-form>
        </el-col>
        <!--列表-->
        <el-table :data="users" :highlight-current-row="true" v-loading="listLoading" @selection-change="selsChange"
                  :stripe="true"    style="width: 100%;border: 1px solid #eaeaea;">
            <el-table-column align="center" type="index"  label="序号" width="70">
            </el-table-column>
            <el-table-column align="center" prop="name" label="用户名称" min-width="100" >
            </el-table-column>
            <el-table-column align="center" prop="phone" label="手机号" min-width="100">
            </el-table-column>
            <el-table-column align="center" prop="email" show-overflow-tooltip label="邮箱" min-width="100">
            </el-table-column>
            <el-table-column align="center" prop="sex" label="性别" min-width="60" :formatter="formatSex">
            </el-table-column>
            <el-table-column align="center" prop="status" label="状态" min-width="60" >
                <template slot-scope="scope">
                    <el-tag size="medium" :disable-transitions="false" :type="scope.row.status === '01' ? 'primary' : scope.row.status === '02' ? 'warning' : 'danger'">
                        {{formatStatus(scope.row)}}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column align="center" prop="createTime" label="创建时间" min-width="120" :formatter="formatDate">
            </el-table-column>
            <el-table-column align="center" label="操作" min-width="150">
                <template scope="scope">
                    <el-button type="info" size="small" @click="handleDetail(scope.row.id)">详情</el-button>
                    <el-button type="warning" v-if="scope.row.status === '01'" size="small" @click="handleFrozen('02', scope.row,'冻结')">冻结</el-button>
                    <el-button type="primary"  v-if="scope.row.status === '02'" size="small" @click="handleFrozen('01', scope.row,'解冻')">解冻</el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination :listPage="listPage" @getDate="getUsers" ></pagination>
    </section>
</template>

<script>
    import util from '../../common/js/util'
    import {postUserListPage,postHandleFrozen} from '../../api/api';
    import pagination from '../../components/my-pagination.vue'


    export default {
        components: {
            pagination,
        },
        data() {
            return {
                queryParam: {
                    name: '',
                    phone: '',
                    email: '',
                    sex: '',
                    status: '',
                },
                rules:{
                    email: [
                        { type: 'email', message: '请输入正确的邮箱地址'},
                    ],
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
        methods: {
            //性别显示转换
            formatSex: function (row, column) {
                return row.sex == '1' ? '男' : row.sex == '2' ? '女' : '未知';
            },
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
            search:function (queryFrom) {
                this.$refs[queryFrom].validate((valid) => {
                    if (valid) {
                        this.getUsers();
                    } else {
                        return false;
                    }
                });
            },
            //查看详情
            handleDetail(detailId) {
                this.$router.push({path: `/user/detail/${detailId}`});
            },
            //获取用户列表
            getUsers() {
                let para = {
                    listPage: this.listPage,
                    queryParam: this.queryParam
                };
                this.listLoading = true;
                postUserListPage(para).then((res) => {
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
            },
            //删除
            handleFrozen: function (status, row,msg) {
                this.$confirm('确认'+msg+'该记录吗?', '提示', {type: 'warning'}).then(() => {
                    this.listLoading = true;
                    let para = {id: row.id,userStatus: status};
                    postHandleFrozen(para).then((res) => {
                        let {meta, data} = res;
                        this.listLoading = false;
                        if (meta.code != 200) {
                            this.$message({
                                message: meta.message,
                                type: 'error'
                            });
                        }else {
                            this.$message({
                                message: '处理成功',
                                type: 'success'
                            });
                        }
                        this.getUsers();
                    });
                }).catch(() => {

                });
            },
            selsChange: function (sels) {
                this.sels = sels;
            }
        },
        mounted() {
            this.getUsers();
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