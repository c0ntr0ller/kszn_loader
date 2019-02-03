package ksznldr.DAO;

import ksznldr.domain.Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RecordDAOBatch implements RecordDAO {
    private static final String Record_INSERT_QUERY = "insert into sh_exchange.records(recordid, publicid, lname, fname, mname, snils, bdate, sex, kladr, district, city, punkt, street, bnum, wing, appnum, wgcode, status, reason, scomment, lschets)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final int BATCH_SIZE = 10000;

    @Override
    public void insertRecordArray(List<Record> recordList, Connection connection) {
        int count = 0;

        try(PreparedStatement statement = connection.prepareStatement(Record_INSERT_QUERY)){
            connection.setAutoCommit(false);

            for (Record Record:recordList) {
                statement.setString(1, Record.getRecordId());
                statement.setString(2, Record.getPublicId());
                statement.setString(3, Record.getlName());
                statement.setString(4, Record.getfName());
                statement.setString(5, Record.getmName());
                statement.setString(6, Record.getSnils());
                statement.setString(7, Record.getBdate());
                statement.setString(8, Record.getSex());
                statement.setString(9, Record.getKladr());
                statement.setString(10, Record.getDistrict());
                statement.setString(11, Record.getCity());
                statement.setString(12, Record.getPunkt());
                statement.setString(13, Record.getStreet());
                statement.setString(14, Record.getBnum());
                statement.setString(15, Record.getWing());
                statement.setString(16, Record.getAppnum());
                statement.setString(17, Record.getWgcode());
                statement.setString(18, Record.getStatus());
                statement.setString(19, Record.getReason());
                statement.setString(20, Record.getScomment());
                statement.setString(21, Record.getLschets());
                statement.addBatch();

                count++;

                if (count%BATCH_SIZE == 0) {
                    statement.executeBatch();
                    connection.commit();
                    count = 0;
                }
            }

            if(count > 0){
                statement.executeBatch();
                connection.commit();
            }
        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }
}
