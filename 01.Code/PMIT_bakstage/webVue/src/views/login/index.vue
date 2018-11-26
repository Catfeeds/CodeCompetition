<template>
  <div class="login-container note" :style="note">
    <div class="login-con">
      <el-card class="box-card">
        <div slot="header" class="clearfix"><span>欢迎登录</span></div>
        <el-form
          ref="loginForm"
          :model="loginForm"
          :rules="loginRules"
          autocomplete="on"
          label-position="left"
        >
          <el-form-item prop="userName">
            <span class="svg-container"> <svg-icon icon-class="user" /> </span>
            <el-input
              v-model="loginForm.userName"
              :placeholder="usernamePlaceholder"
              name="userName"
              type="text"
              autocomplete="on"
            />
          </el-form-item>
          <el-form-item prop="password">
            <span class="svg-container">
              <svg-icon icon-class="password" />
            </span>
            <el-input
              :type="passwordType"
              v-model="loginForm.password"
              :placeholder="passwordPlaceholder"
              name="password"
              autocomplete="on"
              @keyup.enter.native="handleLogin"
            />
            <span class="show-pwd" @click="showPwd">
              <svg-icon icon-class="eye" />
            </span>
          </el-form-item>
          <el-checkbox v-model="checked">十天内免登录</el-checkbox>
          <a href="javascript:void(0)" @click="clearCookie">忘记密码?</a>
          <el-button
            :loading="loading"
            type="primary"
            style="width:100%;margin-top:20px;"
            @click.native.prevent="handleLogin"
            >{{ $t("login.logIn") }}</el-button
          >
        </el-form>
      </el-card>
      <el-dialog
        :title="dialogTitle"
        :visible.sync="showDialog"
        width="20%"
        append-to-body
      >
        {{ $t("login.forgetPasswordTips") }}
      </el-dialog>
    </div>
  </div>
</template>

<script>
import {} from "@/utils/validate";
export default {
  name: "Login",
  components: {},
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!value || value.replace(/\s/g, "").length <= 0) {
        callback(new Error("请输入正确的账号"));
      } else {
        callback();
      }
    };
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error("密码不能少于6位数"));
      } else {
        callback();
      }
    };
    return {
      loginForm: {
        userName: "",
        password: ""
      },
      loginRules: {
        userName: [
          {
            required: true,
            trigger: "blur",
            validator: validateUsername
          }
        ],
        password: [
          {
            required: true,
            trigger: "blur",
            validator: validatePassword
          }
        ]
      },
      passwordType: "password",
      loading: false,
      showDialog: false,
      checked: false,
      redirect: undefined,
      passwordPlaceholder: this.$i18n.t("login.password"),
      usernamePlaceholder: this.$i18n.t("login.userName"),
      dialogTitle: this.$i18n.t("login.forgetPassword"),
      note: {
        backgroundImage:
          "url(" + require("../../assets/images/login-bg.jpg") + ")",
        backgroundRepeat: "no-repeat",
        backgroundPosition: "center",
        backgroundSize: "100%"
      }
    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "";
      } else {
        this.passwordType = "password";
      }
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          //判断复选框是否被勾选 勾选则调用配置cookie方法
          if (this.checked) {
            //传入账号名，密码，和保存天数3个参数
            this.setCookie(
              this.loginForm.userName,
              this.loginForm.password,
              10
            );
          }
          this.$store
            .dispatch("LoginByUserName", this.loginForm)
            .then(() => {
              this.loading = false;
              this.$router.push({ path: this.redirect || "/" });
            })
            .catch(() => {
              this.loading = false;
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    //设置cookie
    setCookie(c_name, c_pwd, exdays) {
      var exdate = new Date(); //获取时间
      exdate.setTime(exdate.getTime() + 24 * 60 * 60 * 1000 * exdays); //保存的天数
      //字符串拼接cookie
      window.document.cookie =
        "userName" + "=" + c_name + ";path=/;expires=" + exdate.toGMTString();
      window.document.cookie =
        "userPwd" + "=" + c_pwd + ";path=/;expires=" + exdate.toGMTString();
    },
    //读取cookie
    getCookie: function() {
      if (document.cookie.length > 0) {
        var arr = document.cookie.split("; "); //这里显示的格式需要切割一下自己可输出看下
        for (var i = 0; i < arr.length; i++) {
          var arr2 = arr[i].split("="); //再次切割
          //判断查找相对应的值
          if (arr2[0] == "userName") {
            this.loginForm.userName = arr2[1]; //保存到保存数据的地方
          } else if (arr2[0] == "userPwd") {
            this.loginForm.password = arr2[1];
          }
        }
        if (this.loginForm.userName && this.loginForm.password) {
          this.checked = true;
          this.handleLogin();
        }
      }
    },
    //清除cookie
    clearCookie: function() {
      this.setCookie("", "", -1); //修改2值都为空，天数为负1天就好了
    }
  },
  //页面加载调用获取cookie值
  mounted() {
    this.getCookie();
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
$bg: #283443;
$light_gray: #eee;
$cursor: #fff;

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    width: 88%;
    input {
      -webkit-appearance: none;
      padding: 12px 5px 12px 15px;
    }
  }
  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
    line-height: 40px;
    height: 42px;
  }
}
</style>

<style rel="stylesheet/scss" lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  position: fixed;
  height: 100%;
  width: 100%;
  background-color: #103f69;
  .login-form {
    position: absolute;
    left: 0;
    right: 0;
    width: 520px;
    max-width: 100%;
    padding: 35px 35px 15px 35px;
    margin: 120px auto;
  }
  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;
    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }
  .svg-container {
    padding-left: 10px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }
  .title-container {
    position: relative;
    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
    .set-language {
      color: #fff;
      position: absolute;
      top: 5px;
      right: 0px;
    }
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 5px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
  .thirdparty-button {
    position: absolute;
    right: 35px;
    bottom: 28px;
  }
  .login-con {
    position: absolute;
    right: 160px;
    top: 50%;
    transform: translateY(-60%);
    width: 300px;
  }
  a {
    font-size: 14px;
    color: #606266;
    float: right;
  }
}
</style>
