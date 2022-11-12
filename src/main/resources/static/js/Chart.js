const languageRankCtx = document.getElementById('language-rank').getContext('2d');
const languageRankChart = new Chart(languageRankCtx, {
    type: 'bar',
    data: {
        labels: ["c++", "c", "java", "11", "test", "test", "test"],
        datasets: [{
            axis: 'y',
            label: 'languages',
            data: [65, 59, 80, 81, 56, 55, 40, 10, 23, 42, 42],
            fill: false,
            backgroundColor: [
                'rgba(255, 99, 132, 1)'
            ]
        }]

    },
    options: {
        indexAxis: 'y'
    }
});

// const languageFlowCtx = document.getElementById("language-flow").getContext('2d');
// const languageFlowChart = new Chart(languageFlowCtx, {
//     type: 'line',
//     data: {
//         labels: Utils.months({count: 7}),
//         datasets: [
//             {
//                 label: 'Dataset 1',
//                 data: Utils.numbers(NUMBER_CFG),
//                 borderColor: Utils.CHART_COLORS.red,
//                 backgroundColor: Utils.transparentize(Utils.CHART_COLORS.red, 0.5),
//             },
//             {
//                 label: 'Dataset 2',
//                 data: Utils.numbers(NUMBER_CFG),
//                 borderColor: Utils.CHART_COLORS.blue,
//                 backgroundColor: Utils.transparentize(Utils.CHART_COLORS.blue, 0.5),
//             }
//         ]
//     },
//     options: {
//         responsive: true,
//         plugins: {
//             legend: {
//                 position: 'top',
//             },
//             title: {
//                 display: true,
//                 text: 'Chart.js Line Chart'
//             }
//         }
//     },
// });


const frameworkRankCtx = document.getElementById('framework-rank').getContext('2d');
const frameworkRankChart = new Chart(frameworkRankCtx, {
    type: 'bar',
    data: {
        labels: ["spring", "nodejs", "test", "data"],
        datasets: [{
            axis: 'y',
            label: 'frameworks',
            data: [65, 59, 80, 81, 56, 55, 40],
            fill: false,
            backgroundColor: [
                'rgba(102, 178, 255, 1)'
            ]
        }]
    },
    options: {
        indexAxis: 'y'
    }
});


const databaseRankCtx = document.getElementById('database-rank').getContext('2d');
const databaseRankChart = new Chart(databaseRankCtx, {
    type: 'bar',
    data: {
        labels: ['oracle', 'mysql', 'mariadb', 'mongodb'],
        datasets: [{
            axis: 'y',
            label: 'databases',
            data: [65, 59, 80, 81, 56, 55, 40],
            fill: false,
            backgroundColor: [
                'rgba(102, 255, 102, 1)'
            ]
        }]
    },
    options: {
        indexAxis: 'y',
    }
});

const lowLanguageRankCtx = document.getElementById('low-language-rank').getContext('2d');
const lowLanguageRankChart = new Chart(lowLanguageRankCtx, {
    type: 'bar',
    data: {
        labels: ["c++", "c", "java", "11"],
        datasets: [{
            axis: 'y',
            label: 'languages',
            data: [65, 59, 80, 81, 56, 55, 40],
            fill: false,
            backgroundColor: [
                'rgba(51, 0, 102, 1)'
            ]
        }]
    },
    options: {
        indexAxis: 'y',
    }
});

const lowFrameworkRankCtx = document.getElementById('low-framework-rank').getContext('2d');
const lowFrameworkRankChart = new Chart(lowFrameworkRankCtx, {
    type: 'bar',
    data: {
        labels: ["spring", "nodejs", "test", "data"],
        datasets: [{
            axis: 'y',
            label: 'frameworks',
            data: [65, 59, 80, 81, 56, 55, 40],
            fill: false,
            backgroundColor: [
                'rgba(51, 102, 0, 1)'
            ]
        }]
    },
    options: {
        indexAxis: 'y',
    }
});