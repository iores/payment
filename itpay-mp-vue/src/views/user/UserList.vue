<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="queryParam">
				<el-form-item>
					<el-input v-model="queryParam.name" placeholder="姓名"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getUsers">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd">新增</el-button>
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
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
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
					name: ''
				},
				users: [],
                listPage: {
                    pageNum :1,
                    pageSize: 10,
                    total: 0,
				},
				listLoading: false,
				sels: [],//列表选中列
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
            handleSizeChange(val){
				console.info(val);
			},
			handleCurrentChange(val) {
				this.listPage.pageNum = val;
				this.getUsers();
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
			//显示编辑界面
			handleEdit: function (index, row) {
				this.editFormVisible = true;
				this.editForm = Object.assign({}, row);
			},
			//显示新增界面
			handleAdd: function () {
				this.addFormVisible = true;
				this.addForm = {
					name: '',
					sex: -1,
					age: 0,
					birth: '',
					addr: ''
				};
			},
			//编辑
			editSubmit: function () {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.editForm);
							para.birth = (!para.birth || para.birth == '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
							editUser(para).then((res) => {
								this.editLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['editForm'].resetFields();
								this.editFormVisible = false;
								this.getUsers();
							});
						});
					}
				});
			},
			//新增
			addSubmit: function () {
				this.$refs.addForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.addLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.addForm);
							para.birth = (!para.birth || para.birth == '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
							addUser(para).then((res) => {
								this.addLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['addForm'].resetFields();
								this.addFormVisible = false;
								this.getUsers();
							});
						});
					}
				});
			},
			selsChange: function (sels) {
				this.sels = sels;
			},
			//批量删除
			batchRemove: function () {
				var ids = this.sels.map(item => item.id).toString();
				this.$confirm('确认删除选中记录吗？', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { ids: ids };
					batchRemoveUser(para).then((res) => {
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
			}
		},
		mounted() {
			this.getUsers();
		}
	}

</script>

<style scoped>

</style>