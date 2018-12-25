package com.example.valdemar.admevent;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class TemplatePDF {
    private Context context;
    private File pdfFile;
    private Document document;
    private PdfWriter pdfWriter;
    private Paragraph paragraph;
    private Font ftitle =  new Font(Font.FontFamily.TIMES_ROMAN,14,Font.BOLD);
    private Font fSubTitle =  new Font(Font.FontFamily.TIMES_ROMAN,13,Font.BOLD);
    private Font fHighText =  new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
    private Font fText =  new Font(Font.FontFamily.TIMES_ROMAN,11);
    private String Titulo;
    public TemplatePDF(Context context) {
        this.context = context;
    }
    private void createFile(String nombre){
        if(nombre.length()>10){
            Titulo = nombre.substring(0,10)+".pdf";
        }else{
            Titulo = nombre+".pdf";
        }
        File folder = new File(Environment.getExternalStorageDirectory().toString(),"Sedes");
        if(!folder.exists())
            folder.mkdirs();
        pdfFile = new File(folder,Titulo);
    }
    public void openDocument(String nombre){
        createFile(nombre);
        try{
            document = new Document(PageSize.A4);
            pdfWriter = PdfWriter.getInstance(document,new FileOutputStream(pdfFile));
            document.open();
        }catch (Exception e){
            Log.e("openDocument",e.toString());
        }
    }
    public void closeDocument(){
        document.close();
    }
    private void addChildP(Paragraph childParagraph){
        childParagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagraph);
    }
    public void addTitles(String title, String subTitle, String date){
        try{
            Titulo =title;
            paragraph = new Paragraph();
            addChildP(new Paragraph(title,ftitle));
            addChildP(new Paragraph(subTitle,fSubTitle));
            addChildP(new Paragraph("Generado: "+date,fHighText));
            paragraph.setSpacingAfter(30);
            document.add(paragraph);
        }catch (Exception e){
            Log.e("addTitles",e.toString());
        }
    }
    public void addParagraph(String text){
        try{
            paragraph = new Paragraph(text,fText);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);
        }catch (Exception e){
            Log.e("addParagraph",e.toString());
        }
    }
    public void createTable(String[]header, ArrayList<String[]> clients){
        try{
            paragraph =  new Paragraph();
            paragraph.setFont(fText);
            PdfPTable pdfPTable =  new PdfPTable(header.length);
            pdfPTable.setWidthPercentage(100);
            pdfPTable.setWidths(new float[] {5,70,20});
            pdfPTable.setSpacingBefore(5);
            PdfPCell pdfPCell;
            int indexC=0;
            while (indexC<header.length){
                pdfPCell = new PdfPCell(new Phrase(header[indexC++],fHighText));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.GRAY);
                pdfPTable.addCell(pdfPCell);
            }
            for(int indexR=0; indexR<clients.size(); indexR++){
                String []row = clients.get(indexR);
                for(indexC=0; indexC<header.length;indexC++){
                    if (indexC == 0 || indexC == 2){
                        pdfPCell = new PdfPCell(new Phrase(row[indexC],fText));
                        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        pdfPTable.addCell(pdfPCell);
                    }else{
                        pdfPCell = new PdfPCell(new Phrase(row[indexC],fText));
                        pdfPCell.setFixedHeight(30);
                        pdfPTable.addCell(pdfPCell);
                    }
                }
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        }catch (Exception e){
            Log.e("createTable",e.toString());
        }
    }
    public void appviewPdF(Activity activity){
        if(pdfFile.exists()){
            Uri uri = Uri.fromFile(pdfFile);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri,"application/pdf");
            try{
                activity.startActivity(intent);
            }catch (ActivityNotFoundException e){
                activity.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.adobe.reader")));
                Toast.makeText(context,"No Cuentas Con Una Aplicacion Para Abrir este Formato",Toast.LENGTH_SHORT).show();
            }
        }else
            Toast.makeText(activity.getApplicationContext(),"no se encontro el archivo",Toast.LENGTH_SHORT).show();
    }
}
