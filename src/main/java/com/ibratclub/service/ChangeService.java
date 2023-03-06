package com.ibratclub.service;

import com.ibratclub.model.Change;
import com.ibratclub.model.Employee;
import com.ibratclub.repository.ChangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeService {
    private final ChangeRepository changeRepository;

    public void changeSaver (Employee employee, String column, String table, String oldData, String newData){
        Change change = new Change();
        change.setEmployee(employee);
        change.setOldData(oldData);
        change.setNewData(newData);
        change.setColumnName(column);
        change.setTableName(table);
        changeRepository.save(change);
    }
}
