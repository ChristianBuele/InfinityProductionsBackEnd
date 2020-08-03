package com.javasampleapproach.jdbcpostgresql.util;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.javasampleapproach.jdbcpostgresql.model.eventosDao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;


public class ListarEventosPdf {
    public static ByteArrayInputStream eventosReport(List<eventosDao> eventos) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfPTable table = new PdfPTable(8);
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Nombre Usuario", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Apellido Usuario", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Fecha Evento", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Dirección Evento", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Dirección Entrega", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Nombre Producto", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Categoria Producto", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Precio", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            for (eventosDao eve : eventos) {
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(eve.getNombreUsuario()));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(eve.getQpellidoUsuario()));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(eve.getFechaEvento()));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(eve.getDireccionEvento()));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(eve.getDireccionEntrega()));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(eve.getNombreProducto()));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(eve.getCategoriaProducto()));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(String.valueOf(eve.getPrecio_final())));
                table.addCell(cell);
            }
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
