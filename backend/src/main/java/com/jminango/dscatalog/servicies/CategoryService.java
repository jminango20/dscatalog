package com.jminango.dscatalog.servicies;

import com.jminango.dscatalog.dto.CategoryDTO;
import com.jminango.dscatalog.entities.Category;
import com.jminango.dscatalog.repositories.CategoryRepository;
import com.jminango.dscatalog.servicies.exceptions.DataBaseExceptions;
import com.jminango.dscatalog.servicies.exceptions.ResourceNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;
    @Transactional(readOnly=true)
    public List<CategoryDTO> findAll(){
        return repository.findAll().stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundExceptions("Entity not Found"));
        return new CategoryDTO(entity);
    }
    @Transactional
    public CategoryDTO insertCategory(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        try{
            Category category = repository.getOne(id); //Pega todos uma única vez no DB ou getReferenceById
            category.setName(dto.getName());
            category = repository.save(category);
            return new CategoryDTO(category);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundExceptions("Id not found for " + id);
        }
    }

    public void deleteCategory(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundExceptions("Id not found " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseExceptions("Integrity Violation");
        }
    }
}
