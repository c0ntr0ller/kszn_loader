package ksznldr;

import ksznldr.DAO.RecordDAO;
import ksznldr.DAO.RecordDAOBatch;
import ksznldr.dbservice.DBService;
import ksznldr.domain.Record;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class MainClass {
    private static final Logger logger = Logger.getLogger(MainClass.class);

    public static void main(String[] args) throws Exception {
        long totalCnt = 0;
        final int arraySize = 10000;
        LocalDateTime startDateTime = LocalDateTime.now();

        logger.info("Start ..." + startDateTime.toString());

        String fileName = args.length > 0?args[0]:null;

        try(XMLFileReader xmlFileReader = new XMLFileReader(fileName);
            Connection connection = DBService.instance().getConnection()){

            while (xmlFileReader.hasNext()) {
                logger.info("Nodes read started...");

                List<Record> recordList = xmlFileReader.readNodesFromStream(arraySize);

                logger.info("Records batch read complete. Records readed:" + recordList.size());
                totalCnt = totalCnt + recordList.size();

                long start_time3 = System.nanoTime();
                RecordDAO nodeDAO3 = new RecordDAOBatch();

                nodeDAO3.insertRecordArray(recordList, connection);

                long end_time3 = System.nanoTime();
                long diff3 = (end_time3 - start_time3);
                logger.info("diff in ms:" + diff3);
            }
        }

        LocalDateTime finishDateTime = LocalDateTime.now();

        logger.info("Finished..." + finishDateTime.toString());

        long s = Duration.between(startDateTime, finishDateTime).getSeconds();
        logger.info(String.format("Duration: %d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60)));

        logger.info("Records inserted:" + totalCnt);
    }
}
