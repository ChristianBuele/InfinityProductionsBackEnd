package com.javasampleapproach.jdbcpostgresql.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


public class compresor extends Thread {
	List<presets> presets;
	
	public compresor(List<com.javasampleapproach.jdbcpostgresql.model.presets> presets) {
		
		this.presets = presets;
	}
	public void run() {
		try {
		for (int i = 0; i < presets.size(); i++) {
			System.out.println("Limpiando servidor "+presets.get(i).getNombrePreset());
			Files.deleteIfExists(Paths.get(presets.get(i).getNombrePreset()));
			
		}
		
		}catch(Exception e) {
			System.out.println("Error al limpiar el servidor "+e.getMessage());
		}
		System.out.println("Servidor limpio");
	}

	public void comprimir() {
		System.out.println("Entra a comprimir");
		for (int i = 0; i < presets.size(); i++) {
			OutputStream out;
			try {
				out=new FileOutputStream(presets.get(i).getNombrePreset());
				out.write(presets.get(i).getBytesPreset());
				out.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		}
		try {
			byte buffer[] = new byte[2048];
 
			FileOutputStream fileOutputStream = new FileOutputStream(
					"presets.zip");
			ZipOutputStream zipOutputStream = new ZipOutputStream(
					new BufferedOutputStream(fileOutputStream));
 
			File inputDir = new File("");//directorio
			String listOfFiles[] = inputDir.list();//lista de archivos
 
			BufferedInputStream bufferedInputStream = null;
 
			for (int j=0;j<presets.size();j++) {
				System.out.println("Adding File to zip: " + presets.get(j).getNombrePreset());
				FileInputStream fileInputStream = new FileInputStream(new File(
						presets.get(j).getNombrePreset()));
				bufferedInputStream = new BufferedInputStream(fileInputStream);
				ZipEntry entry = new ZipEntry(presets.get(j).getNombrePreset());
				zipOutputStream.putNextEntry(entry);
				int count;
				while ((count = bufferedInputStream.read(buffer)) != -1) {
					zipOutputStream.write(buffer, 0, count);
				}
				bufferedInputStream.close();
			}
			zipOutputStream.close();
			
			System.out.println("File Zipped!!!!!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("termina a comprimir");
	}
	}
