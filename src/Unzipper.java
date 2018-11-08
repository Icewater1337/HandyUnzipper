import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created by Icewater on 25/10/2018.
 */
public class Unzipper {

    private static String OUTPUT_FOLDER = "C:\\Users\\Icewater\\Desktop\\mms";


    public static void main(String[] args) throws IOException {
        String INPUT_ZIP_FILE = args[0];
        unZipIt(INPUT_ZIP_FILE, OUTPUT_FOLDER);
    }


    public static void unZipIt(String zipFile, String outputFolder){

        byte[] buffer = new byte[1024];

        try{

            //create output directory is not exists
            File folder = new File(OUTPUT_FOLDER);
            if(!folder.exists()){
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while(ze!=null){

                String filepathName= ze.getName();
                String[] fileNameArr = filepathName.split("/");
                String fileName = fileNameArr[fileNameArr.length-1];


                if ( fileName.matches(".*\\.....?$")){
                    File newFile = new File(outputFolder + File.separator + fileName);

                    System.out.println("file unzip : "+ newFile.getAbsoluteFile());
                    if (!newFile.exists()) {
                        newFile.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(newFile);

                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }

                    fos.close();
                }
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println("Done");

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
