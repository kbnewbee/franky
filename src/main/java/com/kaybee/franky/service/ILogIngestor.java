package com.kaybee.franky.service;

import com.kaybee.franky.model.LogData;

public interface ILogIngestor {
    void ingestLogs(LogData logData);

    void ingestLogs(LogData[] logDataList);
}
