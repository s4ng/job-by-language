fetch('/data')
    .then((response) => response.json())
    .then((data) => {

        const languageRankCtx = document.getElementById('language-rank').getContext('2d');
        const languageRankChart = new Chart(languageRankCtx, {
            type: 'bar',
            data: {
                labels: data.language_rank.names,
                datasets: [{
                    axis: 'y',
                    label: 'languages',
                    data: data.language_rank.values,
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
                labels: data.framework_rank.names,
                datasets: [{
                    axis: 'y',
                    label: 'frameworks',
                    data: data.framework_rank.values,
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
                labels: data.database_rank.names,
                datasets: [{
                    axis: 'y',
                    label: 'databases',
                    data: data.database_rank.values,
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
                labels: data.low_language_rank.names,
                datasets: [{
                    axis: 'y',
                    label: 'languages',
                    data: data.low_language_rank.values,
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
                labels: data.low_framework_rank.names,
                datasets: [{
                    axis: 'y',
                    label: 'frameworks',
                    data: data.low_framework_rank.values,
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
    })
