// import Mock from "mockjs";
export default {
  getProductInfo: () => {
    return [{ id: 2012, name: 2012 }];
  },
  getDUInfo: () => {
    return [{ id: "中央软件院", name: "中央软件院" }];
  },
  getPDUctInfo: () => {
    return [{ id: "GENEX", name: "GENEX产品部" }];
  },
  getProjectGroupInfo: () => {
    return [
      {
        id: 1,
        product: "2012",
        du: "中央软件院",
        pdu: "GENEX产品部",
        teamName: "后台开发一组",
        pm: "黄青海"
      }
    ];
  }
};
