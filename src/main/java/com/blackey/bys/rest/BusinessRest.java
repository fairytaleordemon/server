package com.blackey.bys.rest;

import com.blackey.bys.common.BaseRest;
import com.blackey.bys.common.Result;
import com.blackey.bys.components.service.BusinessService;
import com.blackey.bys.components.service.FileUploadService;
import com.blackey.bys.dto.BusinessForm;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RequestMapping("/server/business")
@RestController
public class BusinessRest extends BaseRest{

    @Resource
    private BusinessService businessService;

    @Resource
    private FileUploadService fileUploadService;


    @RequestMapping("/blackey/save")
    @PostMapping
    public Result save(@ModelAttribute  BusinessForm form,@RequestParam("file") MultipartFile file){
        form.setImagePath(fileUploadService.uploadFile(request,file));
        businessService.save(form);
        return success();
    }


    @RequestMapping("/blackey/page")
    @PostMapping
    public Result page(Pageable pageable){
        return success(businessService.page(pageable));
    }

}
