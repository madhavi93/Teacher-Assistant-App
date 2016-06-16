package com.example.madhaviruwandika.teacher_assistant.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.pdf.PdfDocument;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.MyProfileDA;
import com.example.madhaviruwandika.teacher_assistant.Controller.ReportController;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.MyProfileDAO;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by Madhavi Ruwandika on 6/14/2016.
 */
public class Report_Generator {


    private Context context;
    ReportController reportController;
    MyProfileDAO myProfileDAO;
    public Report_Generator(Context context){
        this.context = context;
        reportController = new ReportController(context);
        myProfileDAO = new MyProfileDA(context);
    }


    /*
    this method return the Chart that contain Student perfomance of the student which is compared to overall class
     */
    public PdfPTable perfpmanceCompredToOverallClass(int classID,int sID){

        Log.d("Reproter",">>>>>>>>>>>>>>>>>>>"+classID+">>>>>>>>>>>>>>>>>"+sID+">>>>>>>>>>>>>>>>>>>>");
        // collect data to create chart
        List<List<Integer>> groupList = reportController.getDataToStudentPerfomanceReport(classID,sID);
        // assign data according to the chart format
        List<Integer> groupmin = groupList.get(0);
        List<Integer> groupStdMark = groupList.get(1);
        List<Integer> groupMax = groupList.get(2);

        PdfPTable pdfPTable = new PdfPTable(4);
        pdfPTable.setWidthPercentage(100);
        pdfPTable.setSpacingBefore(10f);
        pdfPTable.setSpacingAfter(10f);

        // add table hedding
        PdfPCell cell1 = new PdfPCell(new Paragraph("Exam"));
        cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        PdfPCell cell2 = new PdfPCell(new Paragraph("Minimum Mark"));
        cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        PdfPCell cell3 = new PdfPCell(new Paragraph("Student Mark"));
        cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
        PdfPCell cell4 = new PdfPCell(new Paragraph("Maximum Mark"));
        cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);

        pdfPTable.addCell(cell1);
        pdfPTable.addCell(cell2);
        pdfPTable.addCell(cell3);
        pdfPTable.addCell(cell4);

        for (int i=0;i<groupmin.size();i++){
            // add data to table
            int eNO = i+1;
            PdfPCell cellExam = new PdfPCell(new Paragraph("Exam "+eNO));
            PdfPCell cellA = new PdfPCell(new Paragraph(""+groupmin.get(i)));
            cellA.setBackgroundColor(BaseColor.PINK);
            PdfPCell cellB = new PdfPCell(new Paragraph(""+groupStdMark.get(i)));
            cellB.setBackgroundColor(BaseColor.CYAN);
            PdfPCell cellC = new PdfPCell(new Paragraph(""+groupMax.get(i)));
            cellC.setBackgroundColor(BaseColor.YELLOW);

            pdfPTable.addCell(cellExam);
            pdfPTable.addCell(cellA);
            pdfPTable.addCell(cellB);
            pdfPTable.addCell(cellC);

        }
        return pdfPTable;

    }
    /*
    this method return the Chart that contain Student perfomance of the student which is compared to his past perfomance
     */
    public LineChart getPerfomanceReportComparedToHistory(int sID){
        // creating list of entry of the chart
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Integer> markList = reportController.getExamMarkListOfStudent(sID);
        entries.add(new Entry(0f,0));
        for(int i=1;i<=markList.size();i++){
            entries.add(new Entry(markList.get(i - 1), i));
        }
        ArrayList<String> labels = new ArrayList<>();
        for (int i=0;i<entries.size();i++)
        {
            if(i==0){
                labels.add("");
            }
            else {
                labels.add("Exam_"+(i));
            }
        }
        // set data to the chart and modify chart appearance
        LineDataSet dataset = new LineDataSet(entries, "Marks");
        dataset.setColor(Color.RED);
        LineData data = new LineData(labels, dataset);

        LineChart lineChart = new LineChart(context);
        lineChart.setData(data);
        lineChart.setDescription("Exams"); // set the description

        return lineChart;
    }
    /*
    this method return the table layout that contain payment details of the student
     */

    public PdfPTable getPamentReport(int sID,int classID){
        // add pament data to view in table
        List<Map<String,String>> payments = reportController.getPayments(sID,classID);
        PdfPTable pdfPTable = new PdfPTable(3);
        pdfPTable.setWidthPercentage(100);
        pdfPTable.setSpacingBefore(10f);
        pdfPTable.setSpacingAfter(10f);

        // add table hedding
        PdfPCell cell1 = new PdfPCell(new Paragraph("NO"));
        cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        PdfPCell cell2 = new PdfPCell(new Paragraph("Date of payment"));
        cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
        PdfPCell cell3 = new PdfPCell(new Paragraph("For Month"));
        cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);

        pdfPTable.addCell(cell1);
        pdfPTable.addCell(cell2);
        pdfPTable.addCell(cell3);

        for (int i=0;i<payments.size();i++){
            // add data to table
            PdfPCell cellA = new PdfPCell(new Paragraph(payments.get(i).get("No")));
            PdfPCell cellB = new PdfPCell(new Paragraph(payments.get(i).get("date")));
            PdfPCell cellC = new PdfPCell(new Paragraph(payments.get(i).get("month")));

            pdfPTable.addCell(cellA);
            pdfPTable.addCell(cellB);
            pdfPTable.addCell(cellC);

        }
        return pdfPTable;

    }

    /*
    *this method generate the pdf document and add content to it
     */
    public File createStudentReport(Context context,int SID, int classID, String ClassName,RelativeLayout barChart,RelativeLayout lineChart)  {

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
        String StudentName = reportController.getStudentName(SID);
        String pdfName = StudentName
                + sdf.format(Calendar.getInstance().getTime()) + ".pdf";

        try {

            File outputFile = new File(context.getExternalFilesDir("/My app"), pdfName);
            OutputStream out =  new FileOutputStream(outputFile);

            Document pdfDocument = new Document(PageSize.A4,72,36,36,36);
            PdfWriter.getInstance(pdfDocument, new FileOutputStream(outputFile));

            pdfDocument.open();

            Paragraph paragraph1 = new Paragraph( ClassName+"\n****"+myProfileDAO.getProfileData().getName()+"****"+ "\nTuition Class - For Best Choice"+"\nPerformance Report" ,FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD, new CMYKColor(100, 100, 0, 80)) );
            paragraph1.setSpacingBefore(50);
            paragraph1.setAlignment(Element.ALIGN_CENTER);

            Paragraph paragraphSName = new Paragraph("Student Name : "+ reportController.getStudentName(SID),FontFactory.getFont(FontFactory.COURIER, 16,Font.NORMAL , new CMYKColor(100, 100, 0, 80)));
            paragraph1.setSpacingBefore(10);

            Paragraph paragraph2 = new Paragraph("1.  Attendance " ,FontFactory.getFont(FontFactory.COURIER, 16,Font.BOLD , new CMYKColor(100, 100, 0, 80)) );
            paragraph2.setSpacingBefore(20);
            paragraph2.setAlignment(Element.ALIGN_LEFT);

            Paragraph paragraph3 = new Paragraph(reportController.getAttendenceState(SID,classID) ,FontFactory.getFont(FontFactory.COURIER, 16,Font.NORMAL, new CMYKColor(100, 100, 0, 80)) );
            paragraph3.setSpacingBefore(20);
            paragraph3.setAlignment(Element.ALIGN_LEFT);

            Paragraph paragraph4= new Paragraph("2. Payments " ,FontFactory.getFont(FontFactory.COURIER, 16,Font.BOLD , new CMYKColor(100, 100, 0, 80)) );
            paragraph4.setSpacingBefore(20);
            paragraph4.setAlignment(Element.ALIGN_LEFT);

            String extr = context.getExternalFilesDir("/My app").toString();
            File mFolder = new File(extr);

            Paragraph paragraphPerfomance= new Paragraph("3. Perfomance compared with overall class " ,FontFactory.getFont(FontFactory.COURIER, 16,Font.BOLD , new CMYKColor(100, 100, 0, 80)) );
            paragraphPerfomance.setSpacingBefore(10);
            paragraphPerfomance.setAlignment(Element.ALIGN_LEFT);

            Paragraph paragraph5= new Paragraph("3.1. Perfomance compared with overall class " ,FontFactory.getFont(FontFactory.COURIER, 16,Font.BOLD , new CMYKColor(100, 100, 0, 80)) );
            paragraph5.setSpacingBefore(50);
            paragraph5.setAlignment(Element.ALIGN_LEFT);

            //add chart of students perfomance compared to overall class
            RelativeLayout chart = barChart;
            chart.setDrawingCacheEnabled(true);
            chart.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            Bitmap bitmap = chart.getDrawingCache();
            if (!mFolder.exists()) {
                mFolder.mkdir();
            }
            String s = "barChart.png";
            File f = new File(mFolder.getAbsolutePath(), s);
            FileOutputStream fos = null;
            fos = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
            fos.flush();
            fos.close();
            bitmap.recycle();

            String path =String.valueOf(extr)+"/"+s;
            Image image1 = Image.getInstance(path);
            image1.scaleAbsolute(300.0f, 300.0f);
            image1.setAlignment(Element.ALIGN_CENTER);

            Paragraph paragraph6= new Paragraph("3.2. perfomance compared with past perfomance " ,FontFactory.getFont(FontFactory.COURIER, 16,Font.BOLD , new CMYKColor(100, 100, 0, 80)) );
            paragraph6.setSpacingBefore(70);
            paragraph6.setAlignment(Element.ALIGN_LEFT);

            // add chart of student perfomance report compared to history
            RelativeLayout chart1 = lineChart;
            chart1.setDrawingCacheEnabled(true);
            chart1.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            Bitmap bitmap1 = chart1.getDrawingCache();
            String extr1 = context.getExternalFilesDir("/My app").toString();
            File mFolder1 = new File(extr1);
            if (!mFolder1.exists()) {
                mFolder1.mkdir();
            }
            String s1 = "LineChart.png";
            File f1 = new File(mFolder1.getAbsolutePath(), s1);
            FileOutputStream fos1 = null;
            fos1 = new FileOutputStream(f1);
            bitmap1.compress(Bitmap.CompressFormat.JPEG, 50, fos1);
            fos1.flush();
            fos1.close();
            bitmap1.recycle();

            String path1 =String.valueOf(extr)+"/"+s1;
            Image image2 = Image.getInstance(path1);
            image2.scaleAbsolute(300.0f,300.0f);
            image2.setAlignment(Element.ALIGN_CENTER);


            pdfDocument.add(paragraph1);
            pdfDocument.add(paragraphSName);
            pdfDocument.add(paragraph2);
            pdfDocument.add(paragraph3);
            pdfDocument.add(paragraph4);
            pdfDocument.add(getPamentReport(SID,classID));
            pdfDocument.add(paragraphPerfomance);
            pdfDocument.add(perfpmanceCompredToOverallClass(classID,SID));
            pdfDocument.newPage();
            pdfDocument.add(paragraph5);
            pdfDocument.add(image1);
            pdfDocument.add(paragraph6);
            pdfDocument.add(image2);

            pdfDocument.close();
            out.close();
            return outputFile;

        } catch (BadElementException e) {
            e.printStackTrace();
            return null;
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }








}
