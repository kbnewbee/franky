function searchLogs() {
    var level = document.getElementById('level').value;
    var message = document.getElementById('message').value;
    var traceId = document.getElementById('traceId').value;
    var resourceId = document.getElementById('resourceId').value;
    var spanId = document.getElementById('spanId').value;
    var commit = document.getElementById('commit').value;

    var startDate = document.getElementById('startDate').value;
    var endDate = document.getElementById('endDate').value;

    // Create search criteria object
    const searchCriteria = {};
    if (level.trim() !== '') {
        searchCriteria.level = level;
    }

    if (message.trim() !== '') {
        searchCriteria.message = message;
    }

    if (traceId.trim() !== '') {
        searchCriteria.traceId = traceId;
    }

    if (resourceId.trim() !== '') {
        searchCriteria.resourceId = resourceId;
    }

    if (spanId.trim() !== '') {
        searchCriteria.spanId = spanId;
    }

    if (commit.trim() !== '') {
        searchCriteria.commit = commit;
    }

    if (startDate.trim() !== '' && endDate.trim() != '') {
        searchCriteria.startDate = startDate;
        searchCriteria.endDate = endDate;
    }


    // Make API request
    fetch('/api/search', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(searchCriteria),
    })
    .then(response => response.json())
    .then(data => {
        // Display search results
        displayResults(data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function displayResults(data) {
    console.log(data);
    var resultsContainer = document.getElementById('results');
    resultsContainer.innerHTML = '';

    if (data.length === 0) {
        resultsContainer.innerHTML = '<p>No results found.</p>';
        return;
    }

    // Create a table to display results
    var table = document.createElement('table');
    table.innerHTML =
    '<tr><th>Level</th><th>Message</th><th>Timestamp</th><th>traceId</th><th>resourceId</th><th>spanId</th><th>commit</th><th>metadata</th></tr>';

    // Populate the table with data
    data.forEach(log => {
        var row = table.insertRow();
        row.insertCell(0).textContent = log.level;
        row.insertCell(1).textContent = log.message;
        row.insertCell(2).textContent = log.timestamp;
        row.insertCell(3).textContent = log.traceId;
        row.insertCell(4).textContent = log.resourceId;
        row.insertCell(5).textContent = log.spanId;
        row.insertCell(6).textContent = log.commit;
        row.insertCell(7).textContent = log.metadata.parentResourceId;
    });

    resultsContainer.appendChild(table);
}

