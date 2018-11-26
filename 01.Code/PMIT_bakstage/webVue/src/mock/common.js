import Mock from "mockjs";
const menus = [
  "OverallSituation",
  "HumanModal",
  "RoleReport",
  "TrainingReport",
  "ExaminationReport",
  "ProjectTeam",
  "Course",
  "Training",
  "Examination",
  "Import",
  "Permission",
  "Relationship"
];

export default {
  getMenu: () => {
    // let count = Math.floor(Math.random() * 10 + 2);
    let List = [];
    for (let i = 0; i < 12; i++) {
      List.push(
        Mock.mock({
          id: "@increment",
          name: menus[i]
        })
      );
    }
    return List;
  }
};
