<template>
  <div :class="classObj" class="app-wrapper">
    <div
      v-if="device === 'mobile' && sidebar.opened"
      class="drawer-bg"
      @click="handleClickOutside"
    />
    <sidebar class="sidebar-container" :style="sidebarWidth" />
    <div class="main-container" :style="mainMarginLeft">
      <navbar />
      <tags-view />
      <app-main />
    </div>
  </div>
</template>

<script>
import { Navbar, Sidebar, AppMain, TagsView } from "./components";
import ResizeMixin from "./mixin/ResizeHandler";

export default {
  name: "Layout",
  data() {
    return {
      sidebarWidth: "",
      mainMarginLeft: ""
    };
  },
  components: {
    Navbar,
    Sidebar,
    AppMain,
    TagsView
  },
  mixins: [ResizeMixin],
  computed: {
    sidebar() {
      return this.$store.getters.sidebar;
    },
    device() {
      return this.$store.getters.device;
    },
    classObj() {
      if (this.sidebar.opened) {
        (this.sidebarWidth = {
          width: this.$store.getters.language === "en" ? "215px" : "180px"
        }),
          (this.mainMarginLeft = {
            "margin-left":
              this.$store.getters.language === "en" ? "215px" : "180px"
          });
      } else {
        this.sidebarWidth = { width: "36px" };
        this.mainMarginLeft = { "margin-left": "36px" };
      }
      return {
        hideSidebar: !this.sidebar.opened,
        openSidebar: this.sidebar.opened,
        withoutAnimation: this.sidebar.withoutAnimation,
        mobile: this.device === "mobile"
      };
    }
  },
  methods: {
    handleClickOutside() {
      this.$store.dispatch("closeSideBar", { withoutAnimation: false });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
@import "src/styles/mixin.scss";
.app-wrapper {
  @include clearfix;
  position: relative;
  height: 100%;
  width: 100%;
  &.mobile.openSidebar {
    position: fixed;
    top: 0;
  }
}
.drawer-bg {
  background: #000;
  opacity: 0.3;
  width: 100%;
  top: 0;
  height: 100%;
  position: absolute;
  z-index: 999;
}
</style>
