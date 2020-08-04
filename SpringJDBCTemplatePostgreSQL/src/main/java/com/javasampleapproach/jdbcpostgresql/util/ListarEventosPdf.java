package com.javasampleapproach.jdbcpostgresql.util;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.javasampleapproach.jdbcpostgresql.model.carritoDetallado;
import com.javasampleapproach.jdbcpostgresql.model.carritoproducto;
import com.javasampleapproach.jdbcpostgresql.model.eventosDao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
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
    public static ByteArrayInputStream factura(List<carritoDetallado> eventos,List<carritoDetallado> presets) {
    	System.out.println("Hay");
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
			PdfWriter.getInstance(document, new FileOutputStream("factura.pdf"));
			Image header=Image.getInstance("src/main/java/com/javasampleapproach/jdbcpostgresql/img/logo.png");
			header.scaleToFit(650,1000);
			header.setAlignment(Chunk.ALIGN_CENTER);
			
			Paragraph parrafo= new Paragraph();
			parrafo.setAlignment(Paragraph.ALIGN_CENTER);
			parrafo.add("Factura Infinity Productions\n\n");
			parrafo.setFont(FontFactory.getFont("Tahoma",18,Font.BOLD,BaseColor.DARK_GRAY));
			parrafo.add("Detalles Factura\n\n");
			
			document.open();
			document.add(header);
			document.add(parrafo);
			PdfPTable tabla= new PdfPTable(3);
			tabla.addCell("Nombre");
			tabla.addCell("precio");
			tabla.addCell("descripcion");
			if(eventos!=null && eventos.size()!=0) {
				for(int i=0;i<eventos.size();i++) {
					tabla.addCell(eventos.get(i).getNombreProducto());
					tabla.addCell(Double.toString(eventos.get(i).getPrecioProducto()));
					tabla.addCell(eventos.get(i).getDescripcion());
					
				}
				
			}
			if(presets!=null && presets.size()!=0) {
				for(int i=0;i<presets.size();i++) {
					tabla.addCell(presets.get(i).getNombreProducto());
					tabla.addCell(Double.toString(presets.get(i).getPrecioProducto()));
					tabla.addCell(presets.get(i).getDescripcion());
					
				}
				
			}
			document.add(tabla);
		} catch (Exception e) {
			// TODO: handle exception
		}
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
