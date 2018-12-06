<template></template>

<script>
export default {
  created() {
    this.tempRoute = Object.assign({}, this.$route);
    this.setTagsViewTitle();
  },
  methods: {
    setTagsViewTitle() {
      const title = this.lang === "zh" ? "编辑文章" : "Edit Article";
      const route = Object.assign({}, this.tempRoute, {
        title: `${title}`
      });
      this.$store.dispatch("updateVisitedView", route);
    }
  }
};
</script>

<style>
</style>
