package lk.fuel_app.util;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;

public class ReportView {
    public String pdfReportViewInlineSystemOpen(String jasperName, String reportName, Collection<?> rates, Map<String,Object> params) throws Exception {
        InputStream jasperStream = getClass().getResourceAsStream("/report/"+jasperName);
        JasperPrint jasperPrint= new JasperPrint() ;
        if(rates!=null) {
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rates);
            jasperPrint = JasperFillManager.fillReport(jasperStream, params, dataSource);

        }else {
            jasperPrint = JasperFillManager.fillReport(jasperStream, params, new JREmptyDataSource());

        }
        File destFile = new File(reportName + ".pdf");

        JRPdfExporter pdfExporter = new JRPdfExporter();
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
        ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));



        pdfExporter.exportReport();

        String pdfCode= Base64.getEncoder().encodeToString(pdfReportStream.toByteArray());
        pdfReportStream.close();
        // return pdfCode;

        //  JSONObject jsonObject= new JSONObject();
        //  jsonObject.put("value", pdfCode);
        return pdfCode;
    }
}