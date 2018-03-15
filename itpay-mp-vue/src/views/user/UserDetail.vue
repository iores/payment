<template>
    <section>
        <el-form label-position="right" :inline="true" class="demo-table-expand">
            <el-form-item label="商品名称">
                <span>{{ user.name }}</span>
            </el-form-item>
            <el-form-item label="所属店铺">
                <span>{{ user.name }}</span>
            </el-form-item>
            <el-form-item label="商品 ID">
                <span>{{ user.name }}</span>
            </el-form-item>
            <el-form-item label="店铺 ID">
                <span>{{ user.name }}</span>
            </el-form-item>
            <el-form-item label="商品分类">
                <span>{{ user.name }}</span>
            </el-form-item>
            <el-form-item label="店铺地址">
                <span>{{ user.name}}</span>
            </el-form-item>
            <el-form-item label="商品描述">
                <span>{{ user.name }}</span>
            </el-form-item>
        </el-form>
    </section>
</template>

<style>
    .demo-table-expand {
        font-size: 0;
    }

    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }

    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }
</style>
<script>
    import util from '../../common/js/util'
    import {userDetail} from '../../api/api';

    export default {
        data() {
            return {
                user: {
                    id: '',
                    email: '',
                    status: '',
                    sex: '',
                    name: '',
                    createTime: '',
                    phone: '',
                },
                listLoading: false,
            }
        },
        methods: {
            //性别显示转换
            formatSex: function (sex) {
                return sex == '1' ? '男' : sex == '2' ? '女' : '未知';
            },
            formatStatus: function (status) {
                return status == '01' ? '正常' : status == '02' ? '冻结' : status == '03' ? '注销' : '';
            },
            formatDate: function (createTime) {
                return util.formatDate.format(new Date(createTime), 'yyyy-MM-dd hh:mm:ss');
            },
            getUserById() {
                this.listLoading = true;
                //使用$route 获取当前路由对象
                userDetail(this.$route.params.detailId).then((res) => {
                    let {meta, data} = res;
                    if (meta.code != 200) {
                        this.$message({
                            message: meta.message,
                            type: 'error'
                        });
                    } else {
                        this.user = data;
                    }
                    this.listLoading = false;
                }).catch((err) => {
                    console.log(err);
                });
            }
        },
        mounted() {
            this.getUserById();
        }
    }
</script>