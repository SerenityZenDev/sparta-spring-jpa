package org.example.my;

import java.util.HashMap;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Setter
public class MyRepository {

    private HashMap<Long, String> dataTable;

    public String find(Long id){
        return dataTable.getOrDefault(id, "");
    }

    public Long save(String data){
        var newId = Long.valueOf(dataTable.size());
        this.dataTable.put(newId, data);
        return newId;
    }
}
