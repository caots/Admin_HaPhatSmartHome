package com.bksoftware.sellingweb.service;

import com.bksoftware.sellingweb.entities.Record;

public interface RecordService {

    Record findByName(String name);

    boolean saveRecord(Record record);
}
