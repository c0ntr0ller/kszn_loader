package ksznldr.DAO;


import ksznldr.domain.Record;

import java.sql.Connection;
import java.util.List;

public interface RecordDAO {
    public void insertRecordArray(List<Record> Records, Connection connection);
    public default long insertRecord(Record Record){ return 0;}
    public default long updateRecord(Record Record){return 0;}
    public default void deleteRecord(Record Record){}
}
