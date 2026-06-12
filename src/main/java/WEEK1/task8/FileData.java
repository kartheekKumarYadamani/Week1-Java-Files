package WEEK1.task8;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FileData {

    private String fileName;
    private String fileType;
    private Double fileSizeInMb;
    private String content;
    private String uploadedBy;

    public FileData(String fileName,String fileType,Double fileSizeInMb,String content,String uploadedBy){
        this.fileName=fileName;
        this.fileType=fileType;
        this.fileSizeInMb=fileSizeInMb;
        this.content=content;
        this.uploadedBy=uploadedBy;
    }

    public String getFileName(){return fileName;}
    public String getFileType(){return fileType;}
    public Double getFileSizeInMb(){return fileSizeInMb;}
    public String getContent(){return content;}
    public String getUploadedBy(){return uploadedBy;}
}

@FunctionalInterface
interface FileProcessor {
    void process(FileData fileData);
}

class FileService {

    Predicate<FileData> sizeRule=f->f.getFileSizeInMb()<5;

    Predicate<FileData> typeRule=f->
            f.getFileType().equalsIgnoreCase("CSV")
                    ||f.getFileType().equalsIgnoreCase("JSON")
                    ||f.getFileType().equalsIgnoreCase("XML")
                    ||f.getFileType().equalsIgnoreCase("TXT");

    Predicate<FileData> contentRule=f->
            f.getContent()!=null&&!f.getContent().isEmpty();

    Predicate<FileData> userRule=f->
            f.getUploadedBy()!=null&&!f.getUploadedBy().isEmpty();

    Consumer<FileData> printValidation=
            f->System.out.println("Validation : SUCCESS");

    BiConsumer<String,String> log=
            (type,name)->System.out.println("Processor Selected : "+type+" Processor");

    FileProcessor csv=f->System.out.println("CSV Records Processed Successfully");

    FileProcessor json=f->System.out.println("JSON Parsed Successfully");

    FileProcessor xml=f->System.out.println("XML Processed Successfully");

    FileProcessor txt=f->System.out.println("TXT File Processed Successfully");

    Map<String,FileProcessor> processors=
            new HashMap<>();

    public FileService(){

        processors.put("CSV",csv);
        processors.put("JSON",json);
        processors.put("XML",xml);
        processors.put("TXT",txt);
    }

    public void process(FileData file){

        if(!sizeRule.test(file))
            throw new RuntimeException("File size exceeded");

        if(!typeRule.test(file))
            throw new RuntimeException("Invalid File Type");

        if(!contentRule.test(file))
            throw new RuntimeException("Content Empty");

        if(!userRule.test(file))
            throw new RuntimeException("Uploaded User Missing");

        System.out.println("File Name : "+file.getFileName());

        printValidation.accept(file);

        FileProcessor processor=
                processors.get(
                        file.getFileType()
                                .toUpperCase()
                );

        log.accept(
                file.getFileType(),
                file.getFileName()
        );

        processor.process(file);
    }
}

class Test {

    public static void main(String[] args) {

//        FileData file=new FileData(
//                "students.csv",
//                "CSV",
//                2.5,
//                "id,name",
//                "Sai"
//        );
        FileData file=new FileData(
                "student.csv",
                "CSV",
                7.0,
                "",
                null
        );

        FileService service=
                new FileService();

        service.process(file);
    }
}
