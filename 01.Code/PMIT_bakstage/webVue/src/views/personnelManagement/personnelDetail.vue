<template></template>

<script>
export default {
  data() {
    return {
      postForm: {},
      tempRoute: {}
    };
  },
  created() {
    const id = this.$route.params && this.$route.params.id;
    this.fetchData(id);
  },
  methods: {
    fetchData(id) {
      let vm = this;
      vm.$store.dispatch("getEmployeeInfoById", id)
        .then(res => {
          this.postForm = response.data;
          // Just for test
          this.postForm.title += `   Article Id:${this.postForm.id}`;
          this.postForm.content_short += `   Article Id:${this.postForm.id}`;

          // Set tagsview title
          this.setTagsViewTitle();
        })
        .catch(err => {
          console.log(err);
        });
    },
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
