package com.dustin.springbootblog.web.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dustin.springbootblog.model.Type;
import com.dustin.springbootblog.web.dao.TypeRepository;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	TypeRepository dao;
	
	@Override
	public List<Type> findAll() {
		return dao.findAll();
	}
	
	@Override
	public Page<Type> listType(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	@Override
	public Page<Type> listTypeTop(int i) {
		Pageable pageable = PageRequest.of(0, i, Sort.by("id").descending());
		return dao.findAll(pageable);
	}
	
	@Override
	public Optional<Type> findById(BigDecimal id) {
		return dao.findById(id);
	}
	
	@Transactional
	@Override
	public Type save(Type type) {
		return dao.save(type);
	}
	
	@Transactional
	@Override
	public Type saveOrUpdateType(BigDecimal id, String name) {
		Type type = null;
		if (null == id) {
			type = new Type();
		} else {
			Optional<Type> opt = findById(id);
			type = opt.get();
		}
		type.setName(name);
		return dao.save(type);
	}
	
	@Transactional
	@Override
	public void deleteById(BigDecimal id) {
		dao.deleteById(id);
	}

	@Override
	public Optional<Type> findByName(String name) {
		return dao.findByName(name);
	}

	@Transactional
	@Override
	public String parseExcel(MultipartFile reapExcelDataFile) throws IOException {
		StringBuffer sb = new StringBuffer();
		try (Workbook workbook = WorkbookFactory.create(reapExcelDataFile.getInputStream())) {
			Sheet datatypeSheet = workbook.getSheetAt(0);
			int idxRowNum = 1;
			while (idxRowNum <= datatypeSheet.getLastRowNum()) {

				Row currentRow = datatypeSheet.getRow(idxRowNum);
				String cellValue = currentRow.getCell(0).getStringCellValue();
				if (findByName(cellValue).isPresent()) {
					sb.append(cellValue + ",已存在");
					sb.append("/r/n");
				} else {
					Type type = new Type(cellValue);
					dao.save(type);
				}
				
				//Use Iterator Example
//				Iterator<Cell> cellIterator = currentRow.iterator();
//				while (cellIterator.hasNext()) {
//
//					Cell currentCell = cellIterator.next();
//					// getCellTypeEnum shown as deprecated for version 3.15
//					// getCellTypeEnum ill be renamed to getCellType starting
//					// from version 4.0
//					
//					if (currentCell.getCellType() == CellType.STRING) {
//						System.out.print(currentCell.getStringCellValue() + "--");
//					} else if (currentCell.getCellType() == CellType.NUMERIC) {
//						System.out.print(currentCell.getNumericCellValue() + "--");
//					}
//				}
				
				
				
//				another sample
//				try (InputStream inp = new FileInputStream("test.xlsx")) {
//					// InputStream inp = new FileInputStream("workbook.xlsx");
//					Workbook wb = WorkbookFactory.create(inp);
//					Sheet sheet = wb.getSheetAt(0);
//					Row row = sheet.getRow(sheet.getLastRowNum());
//
////					row.forEach(System.out::println);
//
//					sheet.getLastRowNum();
//					System.out.println("sheet.getLastRowNum();"+sheet.getLastRowNum());
//					
//					DataFormatter dataFormatter = new DataFormatter();
//					FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
//					
//					// Output : C
//					row.forEach(cell -> {
//						System.out.println(cell.getColumnIndex());
//						System.out.println(cell.getCellType());
//						if(cell.getCellType().equals(CellType.NUMERIC)) {
//							String cellValue = dataFormatter.formatCellValue(cell, formulaEvaluator);
//							System.out.println(cellValue);
//						}
//						System.out.println(cell);
//					});
//
//				}
				
				System.out.println();
				idxRowNum++;
			}
		}
		return sb.toString();
	}

}
