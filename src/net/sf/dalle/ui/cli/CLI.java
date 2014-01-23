package net.sf.dalle.ui.cli;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CLI {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		if (args == null || args.length != 2){
			System.out.println("Se necesitan 2 parametros: archivo tamaño");
		}
		String source = args[0];
		long sizeOfFragment = toBytes(args[1]);
		split(source, sizeOfFragment);
	}

	public static long toBytes(String filesize) {
		long returnValue = -1;
		Pattern patt = Pattern.compile("([\\d.]+)([GMK]B)",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = patt.matcher(filesize);
		Map<String, Integer> powerMap = new HashMap<>();
		powerMap.put("GB", 3);
		powerMap.put("MB", 2);
		powerMap.put("KB", 1);
		if (matcher.find()) {
			String number = matcher.group(1);
			int pow = powerMap.get(matcher.group(2).toUpperCase());
			BigDecimal bytes = new BigDecimal(number);
			bytes = bytes.multiply(BigDecimal.valueOf(1024).pow(pow));
			returnValue = bytes.longValue();
		}
		return returnValue;
	}
	public static void split(String source, long sizeOfFragment) throws IOException {
		File fileSource = new File(source);
		long total = fileSource.length();
		long transferidos = 0;
		
		int contador = 1;
		
		byte[] buffer = new byte[2048];
		File directorioSalida = fileSource.getParentFile();
		String nombreSalida = fileSource.getName();
		try (InputStream is = new BufferedInputStream(new FileInputStream(fileSource))){
			do {
				File outputFile = new File(directorioSalida, nombreSalida + "." + String.format("%03d", contador++));
				try (OutputStream os = new BufferedOutputStream(new FileOutputStream(outputFile))){
					int parcial = 0;					
					int leidos;
					while ((leidos = is.read(buffer, 0, (int)Math.min(sizeOfFragment - parcial, buffer.length))) > 0){
						os.write(buffer, 0, leidos);
						transferidos += leidos;
						parcial += leidos;
					}
				}
			}while (transferidos < total);
		}
	}
}
