const MONTHS = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]

fetch('/data')
    .then((response) => response.json())
    .then((data) => {
        console.log(data)
        const languageRankCtx = document.getElementById('language-rank').getContext('2d');
        const languageRankChart = new Chart(languageRankCtx, {
            type: 'bar',
            data: {
                labels: data.languageRank.names,
                datasets: [{
                    axis: 'y',
                    label: 'languages',
                    data: data.languageRank.values,
                    fill: false,
                    backgroundColor: [
                        'rgba(255, 99, 132, 1)'
                    ]
                }]
            },
            options: {
               maintainAspectRatio: false,
                responsive: true,
                indexAxis: 'y'
            }
        });

        let languageLabels = [];
        if(data.languageFlow.isMonth) {
            for(let i = 0; i < data.languageFlow.labels.size; i++) {
                languageLabels += MONTHS[data.languageFlow.labels[i] - 1]
            }
        } else {
            languageLabels = data.languageFlow.labels
        }
        const languageFlowCtx = document.getElementById("language-flow").getContext('2d');
        const languageFlowChart = new Chart(languageFlowCtx, {
            type: 'line',
            data: {
                labels: languageLabels,
                datasets: data.languageFlow.flowDataList
            },
            options: {
                maintainAspectRatio: false,
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    }
                }
            },
        });

        const frameworkRankCtx = document.getElementById('framework-rank').getContext('2d');
        const frameworkRankChart = new Chart(frameworkRankCtx, {
            type: 'bar',
            data: {
                labels: data.frameworkRank.names,
                datasets: [{
                    axis: 'y',
                    label: 'frameworks',
                    data: data.frameworkRank.values,
                    fill: false,
                    backgroundColor: [
                        'rgba(102, 178, 255, 1)'
                    ]
                }]
            },
            options: {
                maintainAspectRatio: false,
                responsive: true,
                indexAxis: 'y'
            }
        });

        let frameworkLabels = [];
        if(data.languageFlow.isMonth) {
            for(let i = 0; i < data.frameworkFlow.labels.size; i++) {
                frameworkLabels += MONTHS[data.frameworkFlow.labels[i] - 1]
            }
        } else {
            frameworkLabels = data.frameworkFlow.labels
        }
        const frameworkFlowCtx = document.getElementById("framework-flow").getContext('2d');
        const frameworkFlowChart = new Chart(frameworkFlowCtx, {
            type: 'line',
            data: {
                labels: frameworkLabels,
                datasets: data.frameworkFlow.flowDataList
            },
            options: {
                maintainAspectRatio: false,
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    }
                }
            },
        });

        const databaseRankCtx = document.getElementById('database-rank').getContext('2d');
        const databaseRankChart = new Chart(databaseRankCtx, {
            type: 'bar',
            data: {
                labels: data.databaseRank.names,
                datasets: [{
                    axis: 'y',
                    label: 'databases',
                    data: data.databaseRank.values,
                    fill: false,
                    backgroundColor: [
                        'rgba(102, 255, 102, 1)'
                    ]
                }]
            },
            options: {
                maintainAspectRatio: false,
                responsive: true,
                indexAxis: 'y',
            }
        });

        let databaseLabels = [];
        if(data.databaseFlow.isMonth) {
            for(let i = 0; i < data.databaseFlow.labels.size; i++) {
                databaseLabels += MONTHS[data.databaseFlow.labels[i] - 1]
            }
        } else {
            databaseLabels = data.databaseFlow.labels
        }
        const databaseFlowCtx = document.getElementById("database-flow").getContext('2d');
        const databaseFlowChart = new Chart(databaseFlowCtx, {
            type: 'line',
            data: {
                labels: databaseLabels,
                datasets: data.databaseFlow.flowDataList
            },
            options: {
                maintainAspectRatio: false,
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    }
                }
            },
        });

        const lowLanguageRankCtx = document.getElementById('low-language-rank').getContext('2d');
        const lowLanguageRankChart = new Chart(lowLanguageRankCtx, {
            type: 'bar',
            data: {
                labels: data.lowLanguageRank.names,
                datasets: [{
                    axis: 'y',
                    label: 'languages',
                    data: data.lowLanguageRank.values,
                    fill: false,
                    backgroundColor: [
                        'rgba(51, 0, 102, 1)'
                    ]
                }]
            },
            options: {
                maintainAspectRatio: false,
                responsive: true,
                indexAxis: 'y',
            }
        });

        const lowFrameworkRankCtx = document.getElementById('low-framework-rank').getContext('2d');
        const lowFrameworkRankChart = new Chart(lowFrameworkRankCtx, {
            type: 'bar',
            data: {
                labels: data.lowFrameworkRank.names,
                datasets: [{
                    axis: 'y',
                    label: 'frameworks',
                    data: data.lowFrameworkRank.values,
                    fill: false,
                    backgroundColor: [
                        'rgba(51, 102, 0, 1)'
                    ]
                }]
            },
            options: {
                maintainAspectRatio: false,
                responsive: true,
                indexAxis: 'y',
            }
        });
    })
