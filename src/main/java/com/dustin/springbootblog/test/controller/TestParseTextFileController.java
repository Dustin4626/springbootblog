package com.dustin.springbootblog.test.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dustin.springbootblog.test.model.SmsProcessing;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * 解析txt範例 use third-party lib
 * 
 * use IOUtils
 * <dependency> <groupId>commons-io</groupId>
 * <artifactId>commons-io</artifactId> <version>2.7</version> </dependency>
 * 
 * csv parse
 * <dependency> <groupId>com.fasterxml.jackson.dataformat</groupId>
 * <artifactId>jackson-dataformat-csv</artifactId> <version>2.11.1</version>
 * </dependency>
 * 
 * 
 * @author dustinxie
 *
 */
@Controller
@RequestMapping("/admin")
public class TestParseTextFileController {

	@GetMapping("/parseTextFile")
	@ResponseBody
	public List<String> parseTextFile() throws Exception {

		// String data = IOUtils.toString(fis, "UTF-8");

		List<String> readLines = null;
		try (FileInputStream fis = new FileInputStream("C:\\einv\\SMSREPLY_23997652_20200714000001.txt")) {
			readLines = IOUtils.readLines(fis, "UTF-8");
			for (String line : readLines) {
				// System.out.println(line);
				String[] split = line.split(",");
				System.out.println(Arrays.toString(split));
			}
		} catch (IOException e) {
		}

		return readLines;
	}

	/**
	 * csv file mapping to object
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/parseCSVFile")
	@ResponseBody
	public List<SmsProcessing> parseCSVFile() throws Exception {
		File csvFile = new File("C:\\einv\\SMSREPLY_23997652_20200714000001.txt");
		CsvMapper csvMapper = new CsvMapper();
//		CsvSchema csvSchema = csvMapper
//				.typedSchemaFor(SmsProcessing.class)
//				.withColumnSeparator(',')
//				.withHeader()
//				.withComments()
		CsvSchema csvSchema = CsvSchema.builder()
			    .addColumn("invoiceDate")
			    .addColumn("invoiceNumber")
//			    .addColumn("ignore1")
//			    .addColumn("dob")
//			    .addColumn("ignore2")
//			    .addColumn("postalCode")
			    .build();

		MappingIterator<SmsProcessing> complexUsersIter = csvMapper
				.readerWithTypedSchemaFor(SmsProcessing.class)
				.with(csvSchema)
				.readValues(csvFile);

		List<SmsProcessing> smsProcessing = complexUsersIter.readAll();
		smsProcessing.forEach(System.out::println);
		return smsProcessing;
	}
}
