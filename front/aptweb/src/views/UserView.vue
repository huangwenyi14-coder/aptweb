<template>
  <div class="user">
    <div style="margin-bottom: 10px">
      <el-input v-model="userName" placeholder="请输入内容" style="width: 200px;margin-right: 10px"></el-input>
      <el-button type="warning" @click="load">查询</el-button>
      <el-button type="primary" @click="reset">重置</el-button>
      <el-button type="success" @click="add">新增</el-button>
    </div>
      <el-table :data="tableData" style="width: 100%;margin-bottom: 10px">
      <el-table-column prop="id" label="ID" width="180"></el-table-column>
      <el-table-column prop="userName" label="姓名" width="180"></el-table-column>
        <el-table-column prop="role" label="权限" width="180"></el-table-column>
      <el-table-column  label="操作">
        <template slot-scope="scope">
          <el-button type="primary" @click="update(scope.row)">编辑</el-button>
          <el-button type="danger" @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
      </el-table>
    <div>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 4, 10, 20]"
          :page-size="2"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="用户信息" :visible.sync="dialogVisible" width="30%">
      <el-form :model="form" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="form.userName" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="权限">
          <el-radio v-model="form.role" label="管理员"></el-radio>
          <el-radio v-model="form.role" label="用户"></el-radio>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit">创建</el-button>
          <el-button @click="dialogVisible=false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import request from "@/utils/request";
export default {
  data(){
    return{
      form:{
        userName:'',
        password:'',
        role:''
      },
      tableData:[],
      userName: '',
      pageNum:1,
      pageSize:2,
      total:0,
      dialogVisible:false
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get('/user/selectPage',{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          userName:this.userName
        }
      }).then(res=>{
        if (res.code == '200') {
          this.total=res.data.total
          this.tableData = res.data.records
        }
      })
    },
    reset(){
      this.userName =''
      this.load()
    },
    handleSizeChange(pageSize){
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum=pageNum
      this.load()
    },
    add(){
      this.form = {}
      this.dialogVisible= true
    },
    submit(){
      request.post('/user',this.form).then(res=>{
        if (res.code == '200') {
          this.$message.success("操作成功")
          this.dialogVisible = false
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    update(data){
      this.form=data
      this.dialogVisible=true
    },
    del(id){
      request.delete('/user/delete?id='+id).then(res=>{
        if (res.code == '200') {
          this.$message.success("操作成功")
          this.pageNum=1
          this.load()
        } else {
          this.$message.error("操作失败")
        }
      })
    }
  }
}
</script>