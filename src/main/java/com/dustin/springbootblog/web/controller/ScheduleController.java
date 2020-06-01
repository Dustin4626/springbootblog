package com.dustin.springbootblog.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dustin.springbootblog.model.ApiResponse;
import com.dustin.springbootblog.model.JobForm;
import com.dustin.springbootblog.model.QuartzCron;
import com.dustin.springbootblog.model.SysScheduler;
import com.dustin.springbootblog.web.service.JobService;

@Controller
@RequestMapping("/admin")
public class ScheduleController {

	@Autowired
	private JobService jobService;
	
	@GetMapping("/job")
	public String jobList(
			@PageableDefault(size = 5, direction = Direction.ASC) Pageable pageable,
			Model model) throws Exception {
//		model.addAttribute("job",jobService.list(pageable));
		model.addAttribute("job",jobService.jobIndexListAll(pageable));
		return "admin/job";
	}
	
	 /**
     * 編輯頁面顯示
     */
    @PostMapping("/job/editWin")
    public ResponseEntity<ApiResponse> editWin(String cron) {
    	QuartzCron quartzCronContent = null;
        try {
        	quartzCronContent = jobService.getQuartzCronContent(cron);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.msg(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(ApiResponse.ok(quartzCronContent));
    }
	
	 /**
     * 保存定時任務
     */
    @PostMapping("/job")
    @ResponseBody
	public ResponseEntity<ApiResponse> addJob(@Valid SysScheduler job) {
//    public ResponseEntity<ApiResponse> addJob(@Valid SysScheduler job, Errors errors) {
//		if (errors.hasErrors()) {
//			return new ResponseEntity<>(ApiResponse.msg(errors.toString()), HttpStatus.BAD_REQUEST);
//		}
		try {
			jobService.addJob(job);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(ApiResponse.msg(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(ApiResponse.msg("操作成功"), HttpStatus.CREATED);
	}
    
    /**
     * 保存定時任務
     */
    @PostMapping("/startJob")
    @ResponseBody
	public ResponseEntity<ApiResponse> startJob(String jobId) {
//    public ResponseEntity<ApiResponse> addJob(@Valid SysScheduler job, Errors errors) {
//		if (errors.hasErrors()) {
//			return new ResponseEntity<>(ApiResponse.msg(errors.toString()), HttpStatus.BAD_REQUEST);
//		}
		try {
			jobService.startJob(jobId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(ApiResponse.msg(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(ApiResponse.msg("操作成功"), HttpStatus.CREATED);
	}
    
    
//    @PostMapping("/delete/job")
//    public ResponseEntity<ApiResponse> deleteJob(JobForm form){
//    	if(StringUtils.isEmpty(form.getJobGroupName())||StringUtils.isEmpty(form.getJobClassName())) {
//    		return new ResponseEntity<>(ApiResponse.msg("參數不能為空"), HttpStatus.BAD_REQUEST);
//    	}
//    	jobService.deleteJob(form);
//        return new ResponseEntity<>(ApiResponse.msg("删除成功"), HttpStatus.OK);
//    }
	
	
//	@GetMapping("job")
//	public ResponseEntity<ApiResponse> jobList(
////			@DefaultValue("1") Integer currentPage, 
////			@DefaultValue(value = { "10" }) Integer pageSize
//			@PageableDefault(size = 5, direction = Direction.ASC) Pageable pageable) {
//		
////		Page<SysSchedulerFiredList> listAll = jobService.list(pageable);
////		Page<Map> listAll = jobService.list(pageable);
//		Page<SysScheduler> listAll = jobService.listAll(pageable);
////		System.out.println(listAll.toList());
//		return ResponseEntity.ok(ApiResponse.ok(listAll));
//	}

}
