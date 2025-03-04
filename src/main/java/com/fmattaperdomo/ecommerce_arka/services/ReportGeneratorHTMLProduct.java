package com.fmattaperdomo.ecommerce_arka.services;

import com.fmattaperdomo.ecommerce_arka.entities.Product;
import com.fmattaperdomo.ecommerce_arka.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportGeneratorHTMLProduct implements IReportGenerator {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ProductRepository productRepository;
    public String Generate() {
        String data = "<div>";
        for(Product product :  productRepository.findAll()){
            data += "<b>" + product + "<b></br>";
        }
        data += "</div>";
        return data;
    }
}
