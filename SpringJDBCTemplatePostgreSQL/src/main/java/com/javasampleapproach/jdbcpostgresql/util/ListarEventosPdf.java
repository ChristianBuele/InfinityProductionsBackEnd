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
import com.javasampleapproach.jdbcpostgresql.model.Cifrado;
import com.javasampleapproach.jdbcpostgresql.model.carritoDetallado;
import com.javasampleapproach.jdbcpostgresql.model.carritoproducto;
import com.javasampleapproach.jdbcpostgresql.model.eventosDao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
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
    public static String factura(List<carritoDetallado> eventos,List<carritoDetallado> presets) {
    	String presetsNombres="";
    	double total=0.0;
    	System.out.println("Hay "+eventos.size()+" presets "+presets.size());
    	SecureRandom random = new SecureRandom();
    	String nomFactura=  new BigInteger(50, random).toString(16)+".pdf";
    	
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
			PdfWriter.getInstance(document, new FileOutputStream(nomFactura));
			Image header=Image.getInstance("src/main/java/com/javasampleapproach/jdbcpostgresql/img/logo.png");
			header.scaleToFit(650,700);
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
					total+=eventos.get(i).getPrecioProducto();
				}
				
			}
			if(presets!=null && presets.size()!=0) {
				for(int i=0;i<presets.size();i++) {
					presetsNombres+=presets.get(i).getNombreProducto()+",";
					tabla.addCell(presets.get(i).getNombreProducto());
					tabla.addCell(Double.toString(presets.get(i).getPrecioProducto()));
					tabla.addCell(presets.get(i).getDescripcion());
					total+=presets.get(i).getPrecioProducto();
					
				}
				
			}
			tabla.addCell("Total a cancelado");
			
			
			tabla.addCell(Double.toString(total));
			tabla.addCell("");
			document.add(tabla);
			if(presets!=null && presets.size()!=0) {
				Paragraph parrafo1= new Paragraph();
				parrafo1.setAlignment(Paragraph.ALIGN_CENTER);
				parrafo1.add("\nPresets Link\n");
				parrafo1.setFont(FontFactory.getFont("Tahoma",16,Font.BOLD,BaseColor.DARK_GRAY));
				Cifrado x=new Cifrado();
				System.out.println("Los nombres a cifrar son "+presetsNombres);
				String link="https://servidorinfinity.herokuapp.com/api/producto/files/"+x.rotar(presetsNombres, 1).replace(' ','+');
				System.out.println("El link es "+link);
				parrafo1.add(link);
				document.add(parrafo1);
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
        document.close();
        
        return nomFactura;
        
    }
}
