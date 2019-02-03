package ksznldr;

import ksznldr.domain.Record;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class XMLFileReader implements AutoCloseable {

    private BufferedInputStream bis;
    private XMLStreamReader xmlStreamReader;
    private Unmarshaller jaxbUnmarshaller;

    public XMLFileReader(String inFileName) throws IOException, XMLStreamException, JAXBException {
//        if(configClass.getFileName() == null || inFileName.isEmpty()) inFileName = "data/RU-NVS.osm.bz2";
        File inFile = new File(inFileName);

        if (!Files.exists(inFile.toPath())) {
            throw new IOException(String.format("File not found: %s", inFileName));
        }

        bis = new BufferedInputStream(new FileInputStream(inFile));
        // подготовка к чтению файла
//        InputStream iStream = new BZip2CompressorInputStream(bis);

        XMLInputFactory factory = XMLInputFactory.newInstance();

        xmlStreamReader = factory.createXMLStreamReader(bis);

        JAXBContext jaxbContext = JAXBContext.newInstance(Record.class);
        jaxbUnmarshaller = jaxbContext.createUnmarshaller();

    }

//    public XMLFileReader(String fileName) throws IOException, XMLStreamException, JAXBException {
//        String inFileName = (fileName == null || fileName.isEmpty()) ? "data/RU-NVS.osm.bz2" : fileName;
//        File inFile = new File(inFileName);
//
//        if (!Files.exists(inFile.toPath())) {
//            throw new IOException(String.format("File not found: %s", inFileName));
//        }
//
//        bis = new BufferedInputStream(new FileInputStream(inFile));
//        // подготовка к чтению файла
//        InputStream iStream = new BZip2CompressorInputStream(bis);
//
//        XMLInputFactory factory = XMLInputFactory.newInstance();
//
//        xmlStreamReader = factory.createXMLStreamReader(iStream);
//
//        JAXBContext jaxbContext = JAXBContext.newInstance(Node.class);
//        jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//
//    }

    public List<Record> readNodesFromStream(int arraySize) throws XMLStreamException, JAXBException {
        if(xmlStreamReader == null || !xmlStreamReader.hasNext()){
            return null;
        }
        //XsiTypeReader xtr = new XsiTypeReader(xmlStreamReader);
        // лист для результата
        List<Record> nodeList = new ArrayList<>();
        // указатель на текущую ноду
        Record record = null;
        // счетчик чтений
        int count = 0;

        // если в ридере еще есть что читать
        while (xmlStreamReader.hasNext()) {
            try {
                // читаем следующий
                xmlStreamReader.next();
                // если элемент стартовый и его имя record
                if (xmlStreamReader.isStartElement() &&
                        xmlStreamReader.getLocalName().equalsIgnoreCase("record")) {
                    // создаем объект тип LocalNode
                    record = (Record) jaxbUnmarshaller.unmarshal(xmlStreamReader);
                    // добавляем в лист ноду
                    nodeList.add(record);
                    // инкрементируем счетчик
                    count++;
                    if(count%arraySize == 0){
                        return nodeList;
                    }
                }
                // если достигли установленного размера - возвращаем лист
            } catch (XMLStreamException e1) {
                e1.printStackTrace();
            }
        }
        // закрываем ридер, если больше нечего читать
        xmlStreamReader.close();
        // возвращаем лист с остатком
        return nodeList;
    }

    public boolean hasNext() throws XMLStreamException {
        return xmlStreamReader.hasNext();
    }
    @Override
    public void close() throws Exception {
        bis.close();
        xmlStreamReader.close();
    }
}
