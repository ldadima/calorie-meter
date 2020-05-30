module.exports = {
    collectCoverageFrom: [
        'src/components/Food.js',
        'src/components/FoodList.js',
        'src/components/Footer.js',
        'src/components/AuthNavBar.js',
        'src/components/NavigationBar.js',
        'src/components/Welcome.js'
    ],
    testResultsProcessor: "jest-sonar-reporter",
    testMatch: [
        '<rootDir>/src/tests/*.js'
    ],
    testEnvironment: 'node',
    testURL: 'http://localhost',
    transform: {
        '^.+\\.(js|jsx|css)$': '<rootDir>/node_modules/babel-jest',
    },
    transformIgnorePatterns: [
        '/node_modules/(?!transpile-me|transpile-me-too).+(js|jsx)$'
    ],
    moduleFileExtensions: [
        'web.js',
        'js',
        'json',
        'web.jsx',
        'jsx',
        'node'
    ],
    modulePaths: [
        'src'
    ]
}