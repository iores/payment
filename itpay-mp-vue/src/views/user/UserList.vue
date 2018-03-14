<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar"  style="padding-bottom: 0px;">
			<el-form :inline="true" :model="queryParam"  size="small" label-width="100px"  ref="queryParam">
				<el-form-item  label="姓名" prop="name" >
					<el-input v-model="queryParam.name" placeholder="姓名"></el-input>
				</el-form-item>
                <el-form-item  label="手机号" prop="phone">
                    <el-input v-model="queryParam.phone" placeholder="手机号"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="queryParam.email" placeholder="邮箱"></el-input>
                </el-form-item>
                <el-form-item  label="性别" prop="sex"   >
                    <el-select v-model="queryParam.sex"   placeholder="请选择">
                        <el-option v-for="item in sexs" :key="item.value"  :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态" prop="status">
					<el-select v-model="queryParam.status"   placeholder="请选择">
						<el-option v-for="item in status" :key="item.value"  :label="item.label" :value="item.value">
						</el-option>
					</el-select>
                </el-form-item>
				<el-form-item label="  " >
					<el-button type="primary" v-on:click="getUsers">查询</el-button>
                    <el-button  @click="restFrom('queryParam')">重置</el-button>
				</el-form-item>
			</el-form>
		</el-col>
		<!--列表-->
		<el-table :data="users" highlight-current-row   v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="index" label="序号" width="70">
			</el-table-column>
			<el-table-column prop="name" label="用户名称" min-width="100"  sortable>
			</el-table-column>
			<el-table-column prop="phone" label="手机号" min-width="100" >
			</el-table-column>
			<el-table-column prop="email" label="邮箱" min-width="100" >
			</el-table-column>
			<el-table-column prop="sex" label="性别" min-width="60" :formatter="formatSex" >
			</el-table-column>
			<el-table-column prop="status" label="状态" min-width="60" :formatter="formatStatus" >
			</el-table-column>
			<el-table-column prop="createTime" label="创建时间" min-width="120" :formatter="formatDate" >
			</el-table-column>
			<el-table-column label="操作" min-width="150">
				<template scope="scope">
					<el-button type="info" size="small" @click="handleDetail(scope.row.id)">查看详情</el-button>
					<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">冻结</el-button>
				</template>
			</el-table-column>
		</el-table>

		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
					:page-sizes="[10, 50, 100]"
					:page-size="listPage.pageSize"
					layout="total, sizes, prev, pager, next, jumper"
					:total="listPage.total" style="float:right;">
			</el-pagination>
		</el-col>
	</section>
</template>

<script>
	import util from '../../common/js/util'
	//import NProgress from 'nprogress'
	import { postUserListPage, getUserListPage, removeUser, batchRemoveUser, editUser, addUser } from '../../api/api';


    export default {
		data() {
			return {
                queryParam: {
					name: '',
					phone: '',
                    email: '',
                    sex:'',
                    status: '',
				},
				users: [],
                listPage: {
                    pageNum :1,
                    pageSize: 10,
                    total: 0,
				},
				listLoading: false,
				sels: [],//列表选中列
                sexs:[{
                    value: '1',
                    label: '男'
                },{
                    value: '2',
                    label: '女'
                }],
                status:[{
                    value: '01',
                    label: '正常'
                },{
                    value: '02',
                    label: '冻结'
                },{
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
                return row.status == '01' ? '正常' : row.status == '02' ? '冻结' : row.status == '03' ? '注销' :'';
            },
            formatDate: function (row, column) {
				return util.formatDate.format(new Date(row.createTime),'yyyy-MM-dd hh:mm:ss');
            },
            //修改每页条数
            handleSizeChange(val){
				console.info(val);
			},
			handleCurrentChange(val) {
				this.listPage.pageNum = val;
				this.getUsers();
			},
            //重置表单
            restFrom(queryFrom){
                this.$refs[queryFrom].resetFields();
            },
            //查看详情
            handleDetail(detailId){
                this.$router.push({path: '/user/detail',params:{id:detailId}});
            },
			//获取用户列表
			getUsers() {
				let para = {
                    listPage: this.listPage,
                    queryParam: this.queryParam
				};
				this.listLoading = true;
                postUserListPage(para).then((res) => {
                    let {meta,data} = res;
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
				}).catch((err) =>{
                    console.log(err);
                });
			},
			//删除
			handleDel: function (index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { id: row.id };
					removeUser(para).then((res) => {
						this.listLoading = false;
						//NProgress.done();
						this.$message({
							message: '删除成功',
							type: 'success'
						});
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

</style>