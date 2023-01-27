package com.jminango.dscatalog.servicies;

import com.jminango.dscatalog.dto.CategoryDTO;
import com.jminango.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;
    @Transactional(readOnly=true)
    public List<CategoryDTO> findAll(){
        return repository.findAll().stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

}
