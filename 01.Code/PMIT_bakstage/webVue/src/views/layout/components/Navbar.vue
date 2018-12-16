<template>
  <div class="navbar">
    <hamburger
      :toggle-click="toggleSideBar"
      :is-active="sidebar.opened"
      class="hamburger-container"
    />

    <breadcrumb class="breadcrumb-container"></breadcrumb>

    <div class="right-menu">
      <template v-if="device !== 'mobile'">
        <el-tooltip :content="$t('navbar.screenfull')" effect="dark" placement="bottom">
          <screenfull class="screenfull right-menu-item"></screenfull>
        </el-tooltip>
        <lang-select class="international right-menu-item"></lang-select>
      </template>

      <el-dropdown class="avatar-container right-menu-item" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar + '?imageView2/1/w/80/h/80'" class="user-avatar">
          <i class="el-icon-caret-bottom"></i>
        </div>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item class="clearfix">
            <router-link to="/personal" style="display:inline-block;">
            {{employeeName}}
            </router-link>
            <el-badge class="mark" :value="12" />
          </el-dropdown-item>
          <el-dropdown-item divided>
          <router-link to="/">
            {{ $t("navbar.dashboard") }}
          </router-link>
          </el-dropdown-item>
          <el-dropdown-item divided>
            <span style="display:block;" @click="logout">
              {{
              $t("navbar.logOut")
              }}
            </span>
          </el-dropdown-item>
          <el-dropdown-item divided>
            <span style="display:block;" @click="updatePassword">
              {{
              $t("navbar.password")
              }}
            </span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="30%">
      <el-row style="line-height:25px">
        <i class="el-icon-info"></i>建议密码采用字母、字母和特殊字符混合，并且不短于6位。
      </el-row>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
        <el-form-item label="原密码" prop="oldPass" size="mini">
          <el-input v-model="ruleForm.oldPass" placeholder="请输入原密码" type="password"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPass">
          <el-input v-model="ruleForm.newPass" placeholder="请输入新密码" id="newkey" type="password"></el-input>
        </el-form-item>
        <el-form-item label="重复新密码" prop="checkNewPass">
          <el-input
            v-model="ruleForm.checkNewPass"
            placeholder="请再次输入新密码"
            id="newkey1"
            type="password"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false;" size="mini">取 消</el-button>
        <el-button type="primary" @click="handleSubmit();" size="mini">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import Breadcrumb from "@/components/Breadcrumb";
import Hamburger from "@/components/Hamburger";
import Screenfull from "@/components/Screenfull";
import LangSelect from "@/components/LangSelect";
import Cookies from "js-cookie";

export default {
  data() {
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.newPass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      dialogTitle: "修改密码",
      dialogVisible: false,
      ruleForm: {
        oldPass: "",
        newPass: "",
        checkNewPass: ""
      },
      rules: {
        oldPass: [{ required: true, message: "请输入密码" }],
        newPass: [{ required: true, message: "请输入新密码" }],
        checkNewPass: [
          { required: true, validator: validatePass2, trigger: "blur" }
        ]
      }
    };
  },
  components: {
    Breadcrumb,
    Hamburger,
    Screenfull,
    LangSelect
  },
  computed: {
    ...mapGetters(["sidebar", "employeeId", "employeeName", "avatar", "device"])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch("toggleSideBar");
    },
    logout() {
      this.$confirm("确认要退出吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$store.dispatch("logOut").then(() => {
          Cookies.remove("userName");
          Cookies.remove("password");
          Cookies.set("status", "");
          this.$router.replace("/login");
        });
      });
    },
    updatePassword() {
      this.ruleForm.oldPass = "";
      this.ruleForm.newPass = "";
      this.ruleForm.checkNewPass = "";
      this.dialogVisible = true;
    },
    handleSubmit() {
      this.$refs.ruleForm.validate(valid => {
        if (valid) {
          let param = {
            employeeID: this.employeeId,
            password: this.ruleForm.oldPass,
            changePwd: this.ruleForm.newPass
          };
          this.$store.dispatch("updatePassword", param).then(res => {
            if (res.success) {
              this.$message.success("密码修改成功");
              this.dialogVisible = false;
            } else {
              if (res.data === "0") {
                this.$message.error("用户不存在");
                this.dialogVisible = false;
              }
              if (res.data === "1") {
                this.$message.error("原密码输入错误");
              }
            }
          });
        } else {
          return false;
        }
      });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.navbar {
  height: 50px;
  line-height: 50px;
  border-radius: 0px !important;
  .hamburger-container {
    line-height: 58px;
    height: 50px;
    float: left;
    padding: 0 10px;
  }
  .breadcrumb-container {
    float: left;
  }
  .errLog-container {
    display: inline-block;
    vertical-align: top;
  }
  .right-menu {
    float: right;
    height: 100%;
    &:focus {
      outline: none;
    }
    .right-menu-item {
      display: inline-block;
      margin: 0 8px;
    }
    .screenfull {
      height: 20px;
    }
    .international {
      vertical-align: top;
    }
    .theme-switch {
      vertical-align: 15px;
    }
    .avatar-container {
      height: 50px;
      margin-right: 30px;
      .avatar-wrapper {
        cursor: pointer;
        margin-top: 5px;
        position: relative;
        .user-avatar {
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }
        .el-icon-caret-bottom {
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
