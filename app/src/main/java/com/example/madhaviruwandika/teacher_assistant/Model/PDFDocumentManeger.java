package com.example.madhaviruwandika.teacher_assistant.Model;

import android.app.Activity;
import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Madhavi Ruwandika on 5/2/2016.
 */
public class PDFDocumentManeger {

    Context context;
    public PDFDocumentManeger(Context context){

    }


    public void generatePDF(Activity myActivityReferenc,EditText content,StudentPerformanceReport studentPerformanceReport ){

        PdfDocument pdfDocument = new PdfDocument();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595,842,1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        content.draw(page.getCanvas());
        pdfDocument.finishPage(page);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String pdfName = studentPerformanceReport.getClassName()+"_"+studentPerformanceReport.getStudentName()+"_"+ sdf.format(Calendar.getInstance().getTime())+".pdf";

        File outputFile = new File(myActivityReferenc.getExternalFilesDir("/My app"), pdfName);
        try {
            outputFile.createNewFile();
            OutputStream out =  new FileOutputStream(outputFile);
            pdfDocument.writeTo(out);
            pdfDocument.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}
