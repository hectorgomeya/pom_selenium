package ChromeManager;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilsManager {
    private WebDriver driver;
    private WebDriverWait wait;

    public UtilsManager(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public String takeScreenShot(String fileName){
        /** Va a crear un Archivo llamdo screenshotFile
          * va a llamar a TakeScreenshot con el driver y va obtener una screenshot en un output file
          */
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String ruta = "src/test/resources/";

        //Creo el local date y lo parseo para obtener la fecha en el formato que deseo
        LocalDateTime ltime = LocalDateTime.now();
        String fec = "_"  + ltime.format(DateTimeFormatter.ofPattern("yyyy-mm-dd_HH-MM-SS"));

        String rutaFinal = ruta + fileName + fec + ".png";
        /**
         * Intenta crear un FileUtils copy file (copia el archivo el archivo que le digo
         * En la direccion que le informo con el nombre incluido en la ruta
         * ruta = src/test/resources/
         * */
        try {
            FileUtils.copyFile(screenshotFile, new File(rutaFinal));
            System.out.println("Screenshot "+ rutaFinal + " saved successfully.");
        } catch (Exception e) {
            System.out.println("Failed to save the screenshot: " + e.getMessage());
        }

        return rutaFinal;
    }

    public void createFileWithEvidenceScreenShot(String nameScreenshot, String nameDocx, String titulo) throws IOException, InvalidFormatException {

        //Declarando variables de precondicion de rutas de archivos png y docx
        String rutaDocumentoDocx = "src/test/resources/" + nameDocx + ".docx";

        //Invoco a takeScreenshot asi toma la captura
        String rutaImagen = takeScreenShot(nameScreenshot);

        //Va a crear un file con la ruta del .docx
        File fichero = new File(rutaDocumentoDocx);

        //Crea el tipo de archivo o dato XWPFDocument que maneja el docx
        XWPFDocument docx;

        //Niega el fichero para preguntar si no existe
        //Si no existe crea el documento docx
        //Si existe lo abre
        if (!fichero.exists()) {
            docx = new XWPFDocument();
        } else {
            FileInputStream ficheroStream = new FileInputStream(fichero);
            docx = new XWPFDocument(ficheroStream);
        }

        // Crea un parrafo dentro del docx y necesita utilizar tipo dato parrafo
        // Ademas setea titulo y font size
        XWPFParagraph paragraph = docx.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText(titulo);
        run.setBold(true);
        run.setFontSize(13);

        //Sube la captura de pantalla de la ruta solicitada al archivo solicitado
        InputStream pic = new FileInputStream(rutaImagen);
        run.addPicture(pic, Document.PICTURE_TYPE_PNG, rutaImagen,
                Units.toEMU(500), Units.toEMU(400));
        pic.close();

        File file = new File(rutaImagen);

        try {
            if (file.exists()) {
                file.delete();
            }
        }catch (Exception e){
            System.out.println("No se pudo eliminar el archivo");
        }

        FileOutputStream out = new FileOutputStream(rutaDocumentoDocx);
        docx.write(out);
        out.flush();
        out.close();
        docx.close();
    }
}
