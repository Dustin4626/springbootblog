package com.dustin.springbootblog.web.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dustin.springbootblog.model.Customer;
import com.dustin.springbootblog.model.Type;
import com.dustin.springbootblog.web.service.TypeService;

@Controller
@RequestMapping("/admin")
public class TypeController {
	
	public static final String REDIRECT_TYPE_INDEX = "redirect:/admin/types";  

	@Autowired
	private TypeService service;
	
	@GetMapping("/types")
	public String adminTypePage(
			@PageableDefault(size = 5, sort = { "id" }, direction = Direction.ASC) Pageable pageable,
			Model model) {
		System.out.println(pageable);
		Page<Type> types = service.listType(pageable);
		model.addAttribute("type",types);
//		model.addAttribute("types", service.findAll());
		return "admin/type";
	}
	
	@GetMapping("/types/input")
	public String adminTypeAddPage(Model model) {
		model.addAttribute("type", new Type());
		return "admin/type-input";
	}
	
	@GetMapping("/types/{id}/input")
	public String adminTypeInputPage(@PathVariable BigDecimal id, Model model) {
		Optional<Type> opt = service.findById(id);
		if(!opt.isPresent()) {
			return REDIRECT_TYPE_INDEX;
		}
		model.addAttribute("type",opt.get());
		return "admin/type-input";
	}
	
//	@PostMapping("/types")
//	public String saveOrUpdateType(
//			@RequestParam BigDecimal id, 
//			@RequestParam String name,
//			RedirectAttributes attributes) {
//		if (null == id) {
//			Optional<Type> type = service.findByName(name);
//			if (type.isPresent()) {
//				attributes.addFlashAttribute("message", "標籤已存在");
//				return REDIRECT_TYPE_INDEX;
//			}
//		}
//		service.saveOrUpdateType(id, name);
//		attributes.addFlashAttribute("message", "操作成功");
//		return REDIRECT_TYPE_INDEX;
//	}
	
	@PostMapping("/types")
	public String post(@Valid Type type,BindingResult result,
			RedirectAttributes attributes) {
		if (type.getId() == null) {
			Optional<Type> type1 = service.findByName(type.getName());
			if (type1.isPresent()) {
				result.rejectValue("name", "nameError", "不能添加重複的分類");
			}
		}

		if (result.hasErrors()) {
			return "admin/type-input";
		}
		Type t = service.save(type);
		if (t == null) {
			attributes.addFlashAttribute("message", "操作失敗");
		} else {
			attributes.addFlashAttribute("message", "新增成功");
		}
		return "redirect:/admin/types";
	}
	
	@GetMapping("/types/{id}/delete")
	public String deleteType(@PathVariable BigDecimal id, RedirectAttributes attributes) {
		service.deleteById(id);
		attributes.addFlashAttribute("message", "操作成功");
		return REDIRECT_TYPE_INDEX;
	}
	
//	/**
//	 * download multiple pdf zip file 
//	 * @param request
//	 * @param response
//	 * @param jsonStr
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/downloadMultipleFile", produces = "application/zip")
//	public void downloadMultipleFile(HttpServletRequest request, HttpServletResponse response, String jsonStr) throws Exception {
//		UserInfo u = userInfo(request);
//
//		logger.info("downloadMultipleFile," + u + ",jsonStr" + jsonStr);
//		String[] msgIndex = jsonStr.split(";");
//		String currentDateTime = DateUtil.getCurrentDateTime();
//		List<AddDeclarationLog> logList = new ArrayList<>();
//
//		// setting headers
//		response.setStatus(HttpServletResponse.SC_OK);
//		response.addHeader("Content-Disposition", "attachment; filename=" + u.getCompanyId() + "_" + DateUtil.getNoDelimeterCurrentDateTime() + ".zip");
//		response.setCharacterEncoding("UTF-8");
//
//		ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
//		try {
//			for (String json : msgIndex) {
//				InputStream targetStream = null;
//				try {
//					Map map = gson.fromJson(json, Map.class);
//					String exportFileName = map.get("TX_CODE") + "_" + map.get("IM_EX_ID").toString() + "_" + map.get("DECLARE_NO").toString() + ".pdf";
//					byte[] pdfByteArray = bso.getPdfByteArrayMainProcess(map);
//
//					AddDeclarationLog log = bso.buildAddDeclarationLog(map, u, currentDateTime, OPERATION_MODE_DOWNLOAD);
//					logList.add(log);
//
//					zipOutputStream.putNextEntry(new ZipEntry(exportFileName));
//					targetStream = new ByteArrayInputStream(pdfByteArray);
//					IOUtils.copy(targetStream, zipOutputStream);
//					targetStream.close();
//					zipOutputStream.closeEntry();
//				} finally {
//					IOUtils.closeQuietly(targetStream);
//				}
//			}
//			bso.writeLog(logList);
//		} catch (Exception e) {
//			logger.error("downloadFile error", e);
//		} finally {
//			IOUtils.closeQuietly(zipOutputStream);
//		}
//	}
	
	
	@PostMapping("/types/uploadExcel")
	public String mapReapExcelDatatoDB(
			@RequestParam("file") MultipartFile reapExcelDataFile,
			RedirectAttributes attributes) throws IOException {
		String message = service.parseExcel(reapExcelDataFile);
		if (StringUtils.isEmpty(message)) {
			message = "操作成功";
		}
		attributes.addFlashAttribute("message", message);
		return REDIRECT_TYPE_INDEX;
	}
	
	@GetMapping("/types/downloadExcel")
	public ResponseEntity<StreamingResponseBody> excel(
			@PageableDefault(size = 5, sort = { "id" }, direction = Direction.ASC) Pageable pageable) {
		Workbook workBook = new XSSFWorkbook();
		Sheet sheet = workBook.createSheet("type sheet");
		// sheet.setColumnWidth(0, 2560);
		// sheet.setColumnWidth(1, 2560);

		System.out.println(pageable);
		Page<Type> types = service.listType(pageable);
		List<Type> list = types.toList();
		int index = 0;
		for (Type type : list) {
			Row row = sheet.createRow(index);
			row.createCell(0).setCellValue(type.getName());
			index++;
		}

		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"myfilename.xlsx\"")
				.body(workBook::write);
	}
	
//	@GetMapping("/types/downloadExcel")
	@ResponseBody
	public String downloadExcel(Pageable pageable, HttpServletResponse response, RedirectAttributes attributes) throws IOException {

//		if (StringUtils.isEmpty(message)) {
//			message = "操作成功";
//		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=customers.xlsx");
		ByteArrayInputStream stream = contactListToExcelFile(createTestData());
		IOUtils.copy(stream, response.getOutputStream());
		return REDIRECT_TYPE_INDEX;
	}
	
	public static ByteArrayInputStream contactListToExcelFile(List<Customer> customers) {
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Customers");
			
			Row row = sheet.createRow(0);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        // Creating header
	        Cell cell = row.createCell(0);
	        cell.setCellValue("First Name");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Last Name");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(2);
	        cell.setCellValue("Mobile");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(3);
	        cell.setCellValue("Email");
	        cell.setCellStyle(headerCellStyle);
	        
	        // Creating data rows for each customer
	        for(int i = 0; i < customers.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(customers.get(i).getFirstName());
	        	dataRow.createCell(1).setCellValue(customers.get(i).getLastName());
	        	dataRow.createCell(2).setCellValue(customers.get(i).getMobileNumber());
	        	dataRow.createCell(3).setCellValue(customers.get(i).getEmail());
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	private List<Customer> createTestData(){
    	List<Customer> customers = new ArrayList<Customer>();
    	customers.add(new Customer("Vernon", "Barlow", "0123456789", "test1@simplesolution.dev"));
    	customers.add(new Customer("Maud", "Brock", "0123456788", "test2@simplesolution.dev"));
    	customers.add(new Customer("Chyna", "Cowan", "0123456787", "test3@simplesolution.dev"));
    	customers.add(new Customer("Krisha", "Tierney", "0123456786", "test4@simplesolution.dev"));
    	customers.add(new Customer("Sherry", "Rosas", "0123456785", "test5@simplesolution.dev"));
    	return customers;
    }
	
//	@GetMapping("/download/customers.xlsx")
//    public void downloadCsv(HttpServletResponse response) throws IOException {
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition", "attachment; filename=customers.xlsx");
//        ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(createTestData());
//        IOUtils.copy(stream, response.getOutputStream());
//    }
}
