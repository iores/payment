<template>
    <!--工具条-->
    <el-col :span="24" class="toolbar">
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :page-sizes="[10, 50, 100]"
                       :page-size="listPage.pageSize"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="listPage.total" style="float:right;">
        </el-pagination>
    </el-col>
</template>

<script>
    export default {
        name: 'pagination',
        props:{
            listPage: {
                pageNum: 1,
                pageSize: 10,
                total: 0,
            },
        },
        methods: {
            //修改每页条数
            handleSizeChange(val) {
                this.listPage.pageSize = val;
                this.$emit('getDate')
            },
            handleCurrentChange(val) {
                this.listPage.pageNum = val;
                this.$emit('getDate')
            }
        }
    }
</script>
