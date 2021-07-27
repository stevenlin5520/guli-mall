<template>
  <div>
    <el-tree
      :data="menus"
      :props="defaultProps"
      :show-checkbox="true"
      draggable
      :allow-drop="allowDrop"
      node-key="catId"
      :default-expanded-keys="defaultExpendedKey"
      :expand-on-click-node="false"
    >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
            type="text"
            size="mini"
            v-if="node.level <= 2"
            @click="() => append(data)"
          >Append</el-button>
          <el-button type="text" size="mini" @click="() => edit(data)">Edit</el-button>
          <el-button
            type="text"
            size="mini"
            v-if="node.childNodes.length == 0"
            @click="() => remove(node, data)"
          >Delete</el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog
      :title="dialogMsg"
      :visible.sync="dialogVisible"
      width="30%"
      :close-on-click-modal="false"
    >
      <el-form :model="category">
        <el-form-item label="菜单名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="菜单图标">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitCategory">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menus: [],
      //   默认展开的节点的 key 的数组
      defaultExpendedKey: [],
      //   对话框的显示状态
      dialogVisible: false,
      // 添加/修改狀態:add/edit
      dialogType: "",
      dialogMsg: "",
      category: {
        name: "",
        parentCid: 0,
        catLevel: 0,
        sort: 0,
        showStatus: 1,
        icon: "",
        productUnit: "",
        carId: null
      },
      defaultProps: {
        children: "childCategory",
        label: "name"
      },
      // 最大深度
      maxLevel: 0
    };
  },
  methods: {
    /**
     * 初始化数据
     */
    getMenus() {
      this.dataListLoading = true;
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get"
      }).then(({ data }) => {
        // {data结构数据}
        console.log("成功获取到数据", data);
        if (data && data.code === 0) {
          this.menus = data.list;
        }
        this.dataListLoading = false;
      });
    },
    /**
     * 添加子菜单
     */
    append(data) {
      console.log("追加", data);
      this.dialogType = "add";
      this.dialogMsg = "新增菜单";

      //将对话框中的数据重置
      this.category.name = null;
      this.category.icon = "";
      this.category.productUnit = "";

      /**
       * 首先要弹出一个带form表单的弹出框，然后输入菜单名进行保存，保存成功刷新页面，同时恢复到之前的展开水平
       */
      this.dialogVisible = true;
      this.category.catLevel = data.catLevel * 1 + 1;
      this.category.parentCid = data.catId;
    },
    /**
     * 修改子菜單
     */
    edit(data) {
      console.log("修改", data);
      this.dialogType = "edit";
      this.dialogMsg = "修改菜单";
      this.dialogVisible = true;
      this.category.catId = data.catId;
      this.category.parentCid = data.catId;

      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: "get"
      }).then(({ data }) => {
        console.log(data);
        this.category.name = data.data.name;
        this.category.icon = data.data.icon;
        this.category.productUnit = data.data.productUnit;
      });
    },
    submitCategory() {
      if (this.dialogType == "add") {
        this.addCategory();
      } else if (this.dialogType == "edit") {
        this.editCategory();
      }
    },
    /**
     * 保存修改的菜单
     */
    editCategory() {
      let { catId, name, icon, productUnit } = this.category;
      let postData = { catId, name, icon, productUnit };
      this.$http({
        url: this.$http.adornUrl("/product/category/update"),
        method: "post",
        data: this.$http.adornData(postData, false)
      }).then(({ data }) => {
        if (data && data.code === 0) {
          //关闭对话框
          this.dialogVisible = false;
          // 保存成功之后刷新页面数据
          this.$message({
            message: "操作成功",
            type: "success"
          });
          // 刷新数据
          this.getMenus();
          //   恢复到之前的展开水平
          this.defaultExpendedKey = [this.category.parentCid];
        } else {
          // 保存失败
          this.$message.error(data.msg);
        }
      });
    },
    /**
     * 保存子菜单
     */
    addCategory() {
      console.log("点击添加菜单确认按钮", this.category);

      this.$http({
        url: this.$http.adornUrl("/product/category/save"),
        method: "post",
        data: this.$http.adornData(this.category, false)
      }).then(({ data }) => {
        if (data && data.code === 0) {
          //关闭对话框
          this.dialogVisible = false;
          // 保存成功之后刷新页面数据
          this.$message({
            message: "操作成功",
            type: "success"
          });
          // 刷新数据
          this.getMenus();
          // 恢复到之前的展开水平
          this.defaultExpendedKey = [this.category.parentCid];
        } else {
          // 保存失败
          this.$message.error(data.msg);
        }
      });
    },
    /**
     * 删除菜单
     */
    remove(node, data) {
      console.log("删除", node, data);
      /**
       * 首先删除选中菜单，然后发起请求删除，删除成功后刷新页面，同时恢复到之前的展开水平
       */
      this.$confirm(`确定对菜单[${data.name}]进行删除？`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          // 确认删除
          let ids = [data.catId];
          this.$http({
            url: this.$http.adornUrl("/product/category/delete"),
            method: "post",
            data: this.$http.adornData(ids, false)
          }).then(({ data }) => {
            if (data && data.code === 0) {
              // 删除成功之后刷新页面数据
              this.$message({
                message: "操作成功",
                type: "success"
              });
              // 刷新数据
              this.getMenus();
              //   展开到之前的水平
              this.defaultExpendedKey = [node.parent.data.catId];
            } else {
              // 删除失败
              this.$message.error(data.msg);
            }
          });
        })
        // 取消删除
        .catch(() => {});
    },
    /**
     * 判断节点能否被拖拽
     */
    allowDrop(draggingNode, dropNode, type) {
      // 1、被拖动当前节点和所在父节点的总层数不能超过3
      console.log("拖拽节点", draggingNode, dropNode, type);

      // 1.1、计算被拖动的当前节点的总层数
      this.calcNodelevel(draggingNode.data);
      console.log("深度:", this.maxLevel);
      // 1.2、判断节点层数
      let deep = this.maxLevel - draggingNode.data.catLevel + 1;
      console.log("深度:", deep);

      if (type == "inner") {
        return (deep + dropNode.level) <= 3;
      } else {
        return (deep + dropNode.parent.level) <= 3;
      }
    },
    /**
     * 计算节点的深度
     */
    calcNodelevel(node) {
      //找到节点的所有子节点，求出最大深度
      if (node.children != null && node.children.length > 0) {
        for (let i = 0; i < node.children.length; i++) {
          if (node.children[i].catLevel > this.maxLevel) {
            this.maxLevel = node.children[i].catLevel;
          }
          this.calcNodelevel(node.children[i]);
        }
      }
    }
  },
  created() {
    this.getMenus();
  }
};
</script>

<style>
</style>
