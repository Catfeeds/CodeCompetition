module.exports = {
  root: true,
  env: {
    node: true
  },
  extends: [
    "plugin:vue/essential",
    "@vue/prettier",
    "eslint:recommended",
    "plugin:prettier/recommended"
  ],
  plugins: ["vue", "prettier"],
  parserOptions: {
    parser: "babel-eslint"
  },
  rules: {
    "no-console": process.env.NODE_ENV === "production" ? "error" : "off",
    "no-debugger": process.env.NODE_ENV === "production" ? "error" : "off"
    //字符串必须使用单引号
    // quotes: [2, 'single'],
    // indent: [2, 4],
    // 'linebreak-style': ['error', 'unix'],
    // semi: ['error', 'always'],
    // 'prettier/prettier': [
    //     'error',
    //     {
    //         singleQuote: true,
    //         trailingComma: 'all',
    //         bracketSpacing: true,
    //         jsxBracketSameLine: true,
    //         jsxSingleQuote: true
    //     }
    // ]
  }
};
