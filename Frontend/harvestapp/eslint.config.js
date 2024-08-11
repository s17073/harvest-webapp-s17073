// import globals from "globals";
// import pluginJs from "@eslint/js";
// import tseslint from "typescript-eslint";
// import pluginReact from "eslint-plugin-react";

// export default [
//   { files: ["**/*.{js,mjs,cjs,ts,jsx,tsx}"] },
//   { languageOptions: { globals: globals.browser } },
//   pluginJs.configs.recommended,
//   ...tseslint.configs.recommended,
//   pluginReact.configs.flat.recommended,
//   {
//     ignores: [".eslintrc.cjs"],
//   },
// ];

// module.exports = [
//   {
//     parser: "@typescript-eslint/parser",
//     extends: [
//       "react-app",
//       "react-app/jest",
//       "eslint:recommended",
//       "plugin:@typescript-eslint/recommended",
//       "plugin:react-hooks/recommended",
//       "plugin:prettier/recommended",
//     ],
//     ignorePatterns: ["dist", ".eslintrc.cjs"],
//     parser: "@typescript-eslint/parser",
//     plugins: ["react-refresh", "@typescript-eslint", "prettier"],
//     settings: {
//       react: {
//         version: "detect",
//       },
//     },
//     rules: {
//       "react/prop-types": "off",
//       "react-refresh/only-export-components": [
//         "warn",
//         { allowConstantExport: true },
//       ],
//       "react/react-in-jsx-scope": "off",
//       "react/jsx-uses-react": "off",
//       "react/jsx-filename-extension": [
//         1,
//         { extensions: [".js", ".jsx", ".ts"] },
//       ],
//     },
//     overrides: [
//       {
//         files: ["*.ts", "*.tsx"],
//         rules: {},
//       },
//     ],
//   },
// ];
