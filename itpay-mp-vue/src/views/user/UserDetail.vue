<template>
    <section>
        <el-form label-position="right" label-suffix=":" style="width: 100%;border: 1px solid #eaeaea">
            <el-row>
                <el-col :span="12" :push="1">
                    <el-form-item label="用户编号">
                        <span>{{ user.id }}</span>
                    </el-form-item>
                </el-col>
                <el-col :span="12" :push="1" >
                    <el-form-item label="用户名称">
                        <span>{{ user.name }}</span>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-form-item label="用户手机号">
                <span>{{ user.phone }}</span>
            </el-form-item>
        </el-form>
    </section>
</template>

<style>

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