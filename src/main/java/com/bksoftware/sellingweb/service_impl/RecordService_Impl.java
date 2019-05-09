package com.bksoftware.sellingweb.service_impl;


import com.bksoftware.sellingweb.entities.Record;
import com.bksoftware.sellingweb.repository.RecordRepository;
import com.bksoftware.sellingweb.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService_Impl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;


    @Override
    public Record findByName(String name) {
        return recordRepository.findByName(name);
    }

    @Override
    public boolean saveRecord(Record record) {
        recordRepository.save(record);
        return true;
    }
}
